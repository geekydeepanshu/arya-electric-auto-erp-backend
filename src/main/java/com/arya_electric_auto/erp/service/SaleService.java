package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.SaleRequest;
import com.arya_electric_auto.erp.dto.SaleItemRequest;
import com.arya_electric_auto.erp.entity.*;

import com.arya_electric_auto.erp.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.arya_electric_auto.erp.dto.InvoiceResponse;
import com.arya_electric_auto.erp.dto.InvoiceItemResponse;

import java.util.List;
import java.util.stream.Collectors;
import com.arya_electric_auto.erp.dto.SaleResponse;


@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ProductRepository productRepository;
    private final InventoryUnitRepository inventoryUnitRepository;
    private final InventoryStockRepository inventoryStockRepository;
    private final PersonRepository personRepository;
    private final EmployeeRepository employeeRepository;
    private final InquiryRepository inquiryRepository;

    public SaleService(
            SaleRepository saleRepository,
            SaleItemRepository saleItemRepository,
            ProductRepository productRepository,
            InventoryUnitRepository inventoryUnitRepository,
            InventoryStockRepository inventoryStockRepository,
            PersonRepository personRepository,
            EmployeeRepository employeeRepository,
            InquiryRepository inquiryRepository
    ) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.productRepository = productRepository;
        this.inventoryUnitRepository = inventoryUnitRepository;
        this.inventoryStockRepository = inventoryStockRepository;
        this.personRepository = personRepository;
        this.employeeRepository = employeeRepository;
        this.inquiryRepository = inquiryRepository;
    }

    @Transactional
public Long createSale(SaleRequest request) {

    // 1️⃣ Validate Person
    	Person person;

    	Inquiry inquiry = null;

    	if (request.getInquiryId() != null) {

    	    inquiry = inquiryRepository.findById(request.getInquiryId())
    	            .orElseThrow(() -> new RuntimeException("Inquiry not found"));

    	    // 🔥 IMPORTANT FIX
    	    person = inquiry.getPerson();

    	} else {

    	    person = personRepository.findById(request.getPersonId())
    	            .orElseThrow(() -> new RuntimeException("Person not found"));
    	}

    // 2️⃣ Validate Employee
    Employee employee = employeeRepository.findById(request.getHandledBy())
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    

    // 4️⃣ Create Sale
    Sale sale = new Sale();
    sale.setPerson(person);
    sale.setEmployee(employee);
    sale.setInquiry(inquiry);

    sale.setSaleDate(
            request.getSaleDate() != null ? request.getSaleDate() : LocalDateTime.now()
    );

    sale.setPaymentMode(PaymentMode.valueOf(request.getPaymentMode()));
    sale.setStatus(SaleStatus.BOOKED);
    sale.setCreatedAt(LocalDateTime.now());

    // ⚠️ Set default values to avoid null issue
    sale.setTotalAmount(BigDecimal.ZERO);
    sale.setDiscount(BigDecimal.ZERO);
    sale.setFinalAmount(BigDecimal.ZERO);

    // 🔥 SAVE EARLY (needed for FK)
    sale = saleRepository.save(sale);

    BigDecimal totalAmount = BigDecimal.ZERO;

    // 5️⃣ Process Items
    for (SaleItemRequest itemReq : request.getItems()) {

        Product product = productRepository.findById(itemReq.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (itemReq.getQuantity() == null || itemReq.getQuantity() <= 0) {
            throw new RuntimeException("Invalid quantity for product: " + product.getName());
        }

        if (itemReq.getPrice() == null ||
                itemReq.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid price for product: " + product.getName());
        }

        // 🔥 SERIALIZED
        if (product.getIsSerialized()) {

            if (itemReq.getInventoryUnitId() == null) {
                throw new RuntimeException("Inventory unit required for serialized product");
            }

            InventoryUnit unit = inventoryUnitRepository
                    .findById(itemReq.getInventoryUnitId())
                    .orElseThrow(() -> new RuntimeException("Inventory unit not found"));

            if (!"AVAILABLE".equals(unit.getStatus())) {
                throw new RuntimeException("Item already sold/reserved: " + unit.getSerialNumber());
            }

            if (itemReq.getQuantity() != 1) {
                throw new RuntimeException("Quantity must be 1 for serialized product");
            }

            // ✅ mark sold
            unit.setStatus(InventoryStatus.SOLD);
            inventoryUnitRepository.save(unit);

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);

            // 🔥 NEW
            saleItem.setItemName(product.getName());

            saleItem.setQuantity(1);
            saleItem.setPrice(itemReq.getPrice());
            saleItem.setInventoryUnitId(unit.getId());

            saleItemRepository.save(saleItem);

        } else {

            // 🔵 BULK
            InventoryStock stock = inventoryStockRepository
                    .findByProductId(product.getId())
                    .orElseThrow(() -> new RuntimeException("Stock not found"));

            if (stock.getQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            stock.setQuantity(stock.getQuantity() - itemReq.getQuantity());
            inventoryStockRepository.save(stock);

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);

            // 🔥 NEW
            saleItem.setItemName(product.getName());

            saleItem.setQuantity(itemReq.getQuantity());
            saleItem.setPrice(itemReq.getPrice());

            saleItemRepository.save(saleItem);
        }

        // 🔢 total
        BigDecimal itemTotal = itemReq.getPrice()
                .multiply(BigDecimal.valueOf(itemReq.getQuantity()));

        totalAmount = totalAmount.add(itemTotal);
    }

    // 6️⃣ Apply discount
    BigDecimal discount = request.getDiscount() != null
            ? request.getDiscount()
            : BigDecimal.ZERO;

    sale.setTotalAmount(totalAmount);
    sale.setDiscount(discount);
    sale.setFinalAmount(totalAmount.subtract(discount));

    // 🔥 INVOICE NUMBER GENERATION
    String invoiceNumber = "INV-" + String.format("%06d", sale.getId());
    sale.setInvoiceNumber(invoiceNumber);

    // FINAL SAVE
    saleRepository.save(sale);

    return sale.getId();
}
    
    

    public InvoiceResponse getInvoiceBySaleId(Long saleId) {

        // 1️⃣ Fetch Sale
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        // 2️⃣ Fetch Sale Items
        List<SaleItem> saleItems = saleItemRepository.findBySaleId(saleId);

        // 3️⃣ Map Items
        List<InvoiceItemResponse> items = saleItems.stream().map(item -> {

            String serialNumber = null;

            // 🔴 If serialized
            if (item.getInventoryUnitId() != null) {
                InventoryUnit unit = inventoryUnitRepository.findById(item.getInventoryUnitId())
                        .orElse(null);

                if (unit != null) {
                    serialNumber = unit.getSerialNumber();
                }
            }

            BigDecimal total = item.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            return new InvoiceItemResponse(
                    item.getItemName(),
                    item.getQuantity(),
                    item.getPrice(),
                    total,
                    serialNumber
            );

        }).collect(Collectors.toList());

        // 4️⃣ Build Response
        return new InvoiceResponse(
                sale.getInvoiceNumber(),
                sale.getSaleDate(),
                sale.getPerson().getFullName(),
                sale.getPerson().getPhone(),
                items,
                sale.getTotalAmount(),
                sale.getDiscount(),
                sale.getFinalAmount(),
                sale.getPaymentMode().name()
        );
    }
    
    
    

    public List<SaleResponse> getAllSales() {

        return saleRepository.findAll()
                .stream()
                .map(sale -> new SaleResponse(
                        sale.getId(),
                        sale.getInvoiceNumber(),
                        sale.getSaleDate(),
                        sale.getPerson().getFullName(),
                        sale.getEmployee().getFullName(),
                        sale.getTotalAmount(),
                        sale.getDiscount(),
                        sale.getFinalAmount(),
                        sale.getPaymentMode().name(),
                        sale.getStatus() != null ? sale.getStatus().name() : null
                ))
                .collect(Collectors.toList());
    }
}