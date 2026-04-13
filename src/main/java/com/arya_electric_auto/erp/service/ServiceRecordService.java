package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.service.ServiceItemDto;
import com.arya_electric_auto.erp.dto.service.ServiceRecordRequest;
import com.arya_electric_auto.erp.entity.*;
import com.arya_electric_auto.erp.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository recordRepo;
    private final ServiceItemRepository itemRepo;
    private final JobCardRepository jobCardRepo;
    private final ProductRepository productRepo;
    private final InventoryUnitRepository unitRepo;
    private final InventoryStockRepository stockRepo;

    public ServiceRecordService(ServiceRecordRepository recordRepo,
                                ServiceItemRepository itemRepo,
                                JobCardRepository jobCardRepo,
                                ProductRepository productRepo,
                                InventoryUnitRepository unitRepo,
                                InventoryStockRepository stockRepo) {
        this.recordRepo = recordRepo;
        this.itemRepo = itemRepo;
        this.jobCardRepo = jobCardRepo;
        this.productRepo = productRepo;
        this.unitRepo = unitRepo;
        this.stockRepo = stockRepo;
    }

    @Transactional
    public ServiceRecord create(ServiceRecordRequest req) {

        JobCard jobCard = jobCardRepo.findById(req.getJobCardId())
                .orElseThrow(() -> new RuntimeException("Job card not found"));

        ServiceRecord record = new ServiceRecord();
        record.setJobCard(jobCard);
        record.setDiscount(req.getDiscount() == null ? 0 : req.getDiscount());
        record.setPaymentMode(req.getPaymentMode());
        record.setCreatedAt(LocalDateTime.now());

        double total = 0;

        record = recordRepo.save(record);

        for (ServiceItemDto dto : req.getItems()) {

            ServiceItem item = new ServiceItem();
            item.setServiceRecord(record);
            item.setItemName(dto.getItemName());
            item.setQuantity(dto.getQuantity());
            item.setPrice(dto.getPrice());

            double itemTotal = dto.getQuantity() * dto.getPrice();
            total += itemTotal;

            // 🔥 INVENTORY LOGIC
            if (dto.getProductId() != null) {

                Product product = productRepo.findById(dto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                item.setProduct(product);

                if (product.getIsSerialized()) {
                    InventoryUnit unit = unitRepo.findById(dto.getInventoryUnitId())
                            .orElseThrow(() -> new RuntimeException("Inventory unit not found"));

                    if (unit.getStatus() != InventoryStatus.IN_STOCK) {
                        throw new RuntimeException("Unit not available");
                    }


                    unit.setStatus(InventoryStatus.SOLD);
                    unitRepo.save(unit);

                    item.setInventoryUnit(unit);

                } else {
                    InventoryStock stock = stockRepo.findByProduct(product)
                            .orElseThrow(() -> new RuntimeException("Stock not found"));

                    if (stock.getQuantity() < dto.getQuantity()) {
                        throw new RuntimeException("Insufficient stock");
                    }

                    stock.setQuantity(stock.getQuantity() - dto.getQuantity());
                    stockRepo.save(stock);
                }
            }

            itemRepo.save(item);
        }

        double finalAmount = total - record.getDiscount();

        record.setTotalAmount(total);
        record.setFinalAmount(finalAmount);

        // 🔥 Invoice number
        record.setInvoiceNumber("SRV-" + String.format("%06d", record.getId()));

        record = recordRepo.save(record);

        // update job card
        jobCard.setStatus("COMPLETED");
        jobCardRepo.save(jobCard);

        return record;
    }
}