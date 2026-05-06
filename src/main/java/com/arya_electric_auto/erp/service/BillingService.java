package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.service.*;
import com.arya_electric_auto.erp.dto.service.InvoiceResponse;
import com.arya_electric_auto.erp.entity.*;
import com.arya_electric_auto.erp.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillingService {

    private final JobCardRepository jobCardRepo;
    private final JobCardItemRepository itemRepo;
    private final InvoiceRepository invoiceRepo;
    private final ProductRepository productRepo;
    private final InventoryService inventoryService;
    private final InventoryUnitRepository inventoryUnitRepo;

    public BillingService(JobCardRepository jobCardRepo,
                          JobCardItemRepository itemRepo,
                          InvoiceRepository invoiceRepo,
                          ProductRepository productRepo,
                          InventoryService inventoryService,
                          InventoryUnitRepository inventoryUnitRepo) {
        this.jobCardRepo = jobCardRepo;
        this.itemRepo = itemRepo;
        this.invoiceRepo = invoiceRepo;
        this.productRepo = productRepo;
        this.inventoryService = inventoryService;
        this.inventoryUnitRepo = inventoryUnitRepo;
    }

    // =====================================================
    // 1️⃣ ADD ITEM
    // =====================================================

    public JobCardItemResponse addItem(Long jobCardId, JobCardItemRequest req) {

        JobCard jobCard = jobCardRepo.findById(jobCardId)
                .orElseThrow(() -> new RuntimeException("Job card not found"));

        validateItem(req);

        JobCardItem item = new JobCardItem();
        item.setJobCard(jobCard);
        item.setType(req.getType());
        item.setDescription(req.getDescription());
        item.setQuantity(req.getQuantity());
        item.setUnitPrice(req.getUnitPrice());
        item.setTotalPrice(req.getQuantity() * req.getUnitPrice());

        // 🔴 PART HANDLING
        if ("PART".equals(req.getType())) {

            Product product = productRepo.findById(req.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            item.setProduct(product);

            // 🔥 SERIALIZED PRODUCT
            if (Boolean.TRUE.equals(product.getIsSerialized())) {

                if (req.getUnitIds() == null || req.getUnitIds().isEmpty()) {
                    throw new RuntimeException("Units must be selected for serialized product");
                }

                if (req.getUnitIds().size() != req.getQuantity()) {
                    throw new RuntimeException("Quantity must match selected units");
                }

                List<InventoryUnit> units = inventoryUnitRepo.findAllById(req.getUnitIds());

                if (units.size() != req.getUnitIds().size()) {
                    throw new RuntimeException("Invalid unit selection");
                }

                for (InventoryUnit unit : units) {
                    if (!"AVAILABLE".equals(unit.getStatus())) {
                        throw new RuntimeException("Unit not available: " + unit.getSerialNumber());
                    }
                }

                item.setUnits(units);
            }
        }

        return toItemResponse(itemRepo.save(item));
    }

    // =====================================================
    // 2️⃣ GET ITEMS
    // =====================================================

    public List<JobCardItemResponse> getItems(Long jobCardId) {
        return itemRepo.findByJobCardId(jobCardId)
                .stream()
                .map(this::toItemResponse)
                .toList();
    }

    // =====================================================
    // 3️⃣ GENERATE INVOICE
    // =====================================================

    public InvoiceResponse generateInvoice(Long jobCardId) {

        JobCard jobCard = jobCardRepo.findById(jobCardId)
                .orElseThrow(() -> new RuntimeException("Job card not found"));

        List<JobCardItem> items = itemRepo.findByJobCardId(jobCardId);

        if (items.isEmpty()) {
            throw new RuntimeException("No items added");
        }

        double total = items.stream()
                .mapToDouble(JobCardItem::getTotalPrice)
                .sum();

        Invoice invoice = invoiceRepo.findByJobCardId(jobCardId)
                .orElse(new Invoice());

        invoice.setJobCard(jobCard);
        invoice.setTotalAmount(total);
        invoice.setDiscount(0.0);
        invoice.setFinalAmount(total);
        invoice.setStatus("DRAFT");

        return toInvoiceResponse(invoiceRepo.save(invoice));
    }

    // =====================================================
    // 4️⃣ APPLY DISCOUNT
    // =====================================================

    public InvoiceResponse applyDiscount(Long invoiceId, InvoiceDiscountRequest req) {

        Invoice invoice = getInvoice(invoiceId);

        if (!"DRAFT".equals(invoice.getStatus())) {
            throw new RuntimeException("Invoice already finalized");
        }

        double total = invoice.getTotalAmount();
        double discount = req.getDiscount();

        if (discount > total) {
            throw new RuntimeException("Discount cannot exceed total");
        }

        invoice.setDiscount(discount);
        invoice.setFinalAmount(total - discount);

        return toInvoiceResponse(invoiceRepo.save(invoice));
    }

    // =====================================================
    // 5️⃣ PAY (FINAL STEP)
    // =====================================================

    @Transactional
    public InvoiceResponse pay(Long invoiceId, InvoicePayRequest req) {

        Invoice invoice = getInvoice(invoiceId);

        if (!"DRAFT".equals(invoice.getStatus())) {
            throw new RuntimeException("Invoice already paid");
        }

        List<JobCardItem> items = itemRepo.findByJobCardId(invoice.getJobCard().getId());

        if (items.isEmpty()) {
            throw new RuntimeException("No items to bill");
        }

        // 🔥 INVENTORY HANDLING
        for (JobCardItem item : items) {

            if ("PART".equals(item.getType())) {

                Product product = item.getProduct();

                if (Boolean.TRUE.equals(product.getIsSerialized())) {

                    // 🔴 deduct selected units
                    List<Long> unitIds = item.getUnits()
                            .stream()
                            .map(InventoryUnit::getId)
                            .toList();

                    inventoryService.deductSerializedUnits(unitIds);

                } else {

                    // 🔵 bulk
                    inventoryService.validateStock(product.getId(), item.getQuantity());
                    inventoryService.deductStock(product.getId(), item.getQuantity());
                }
            }
        }

        invoice.setStatus("PAID");
        invoice.setPaymentMode(req.getPaymentMode());

        return toInvoiceResponse(invoiceRepo.save(invoice));
    }

    // =====================================================
    // 🔧 VALIDATION
    // =====================================================

    private void validateItem(JobCardItemRequest req) {

        if ("PART".equals(req.getType())) {

            if (req.getProductId() == null) {
                throw new RuntimeException("ProductId required for PART");
            }

        } else if ("SERVICE".equals(req.getType())) {

            if (req.getProductId() != null) {
                throw new RuntimeException("ProductId must be null for SERVICE");
            }

            if (req.getUnitIds() != null && !req.getUnitIds().isEmpty()) {
                throw new RuntimeException("Units not allowed for SERVICE");
            }

        } else {
            throw new RuntimeException("Invalid type");
        }
    }

    private Invoice getInvoice(Long id) {
        return invoiceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    // =====================================================
    // 🔧 MAPPERS
    // =====================================================

    private JobCardItemResponse toItemResponse(JobCardItem item) {

        JobCardItemResponse res = new JobCardItemResponse();

        res.setId(item.getId());
        res.setJobCardId(item.getJobCard().getId());
        res.setType(item.getType());
        res.setDescription(item.getDescription());
        res.setQuantity(item.getQuantity());
        res.setUnitPrice(item.getUnitPrice());
        res.setTotalPrice(item.getTotalPrice());

        if (item.getProduct() != null) {
            res.setProductId(item.getProduct().getId());
        }

        if (item.getUnits() != null) {
            res.setUnitIds(
                    item.getUnits().stream()
                            .map(InventoryUnit::getId)
                            .toList()
            );
        }

        return res;
    }

    private InvoiceResponse toInvoiceResponse(Invoice invoice) {

        InvoiceResponse res = new InvoiceResponse();

        res.setId(invoice.getId());
        res.setJobCardId(invoice.getJobCard().getId());
        res.setTotalAmount(invoice.getTotalAmount());
        res.setDiscount(invoice.getDiscount());
        res.setFinalAmount(invoice.getFinalAmount());
        res.setStatus(invoice.getStatus());
        res.setPaymentMode(invoice.getPaymentMode());

        return res;
    }
}