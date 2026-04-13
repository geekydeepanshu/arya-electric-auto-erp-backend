package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.PurchaseRequest;
import com.arya_electric_auto.erp.dto.PurchaseResponse;
import com.arya_electric_auto.erp.dto.PurchaseItemRequest;
import com.arya_electric_auto.erp.entity.*;

import com.arya_electric_auto.erp.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    private final VendorRepository vendorRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final ProductRepository productRepository;
    private final InventoryUnitRepository inventoryUnitRepository;
    private final InventoryStockRepository inventoryStockRepository;

    public PurchaseService(
            VendorRepository vendorRepository,
            PurchaseRepository purchaseRepository,
            PurchaseItemRepository purchaseItemRepository,
            ProductRepository productRepository,
            InventoryUnitRepository inventoryUnitRepository,
            InventoryStockRepository inventoryStockRepository
    ) {
        this.vendorRepository = vendorRepository;
        this.purchaseRepository = purchaseRepository;
        this.purchaseItemRepository = purchaseItemRepository;
        this.productRepository = productRepository;
        this.inventoryUnitRepository = inventoryUnitRepository;
        this.inventoryStockRepository = inventoryStockRepository;
    }

    // 🔥 MAIN METHOD
    @Transactional
    public Long createPurchase(PurchaseRequest request) {

        // 1️⃣ Validate Vendor
        Vendor vendor = vendorRepository.findById(request.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        // 2️⃣ Create Purchase
        Purchase purchase = new Purchase();
        purchase.setVendor(vendor);
        purchase.setPurchaseDate(
                request.getPurchaseDate() != null ? request.getPurchaseDate() : LocalDateTime.now()
        );

        purchase = purchaseRepository.save(purchase);

        BigDecimal totalAmount = BigDecimal.ZERO;

        // 3️⃣ Process Items
        for (PurchaseItemRequest itemReq : request.getItems()) {

            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found: " + itemReq.getProductId()
                    ));

            // 🔴 Quantity Validation
            if (itemReq.getQuantity() == null || itemReq.getQuantity() <= 0) {
                throw new RuntimeException("Invalid quantity for product: " + product.getName());
            }

            // 🔴 Price Validation
            if (itemReq.getPrice() == null ||
                    itemReq.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Invalid price for product: " + product.getName());
            }

            // 🔴 Serialized Validation
            if (product.getIsSerialized()) {

                if (itemReq.getSerialNumbers() == null ||
                        itemReq.getSerialNumbers().size() != itemReq.getQuantity()) {

                    throw new RuntimeException(
                            "Serial numbers required and must match quantity for product: "
                                    + product.getName()
                    );
                }

            } else {
                if (itemReq.getSerialNumbers() != null &&
                        !itemReq.getSerialNumbers().isEmpty()) {

                    throw new RuntimeException(
                            "Serial numbers not allowed for non-serialized product: "
                                    + product.getName()
                    );
                }
            }

            // 4️⃣ Save Purchase Item
            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setPurchase(purchase);
            purchaseItem.setProduct(product);
            purchaseItem.setQuantity(itemReq.getQuantity());
            purchaseItem.setPrice(itemReq.getPrice());

            purchaseItemRepository.save(purchaseItem);

            // 5️⃣ Inventory Update
            if (product.getIsSerialized()) {

                for (String serial : itemReq.getSerialNumbers()) {

                    // Duplicate check
                    if (inventoryUnitRepository.existsBySerialNumber(serial)) {
                        throw new RuntimeException("Duplicate serial number: " + serial);
                    }

                    InventoryUnit unit = new InventoryUnit();
                    unit.setProduct(product);
                    unit.setSerialNumber(serial);
                    unit.setStatus(InventoryStatus.IN_STOCK);

                    inventoryUnitRepository.save(unit);
                }

            } else {

                InventoryStock stock = inventoryStockRepository
                        .findByProductId(product.getId())
                        .orElse(null);

                if (stock == null) {
                    stock = new InventoryStock();
                    stock.setProduct(product);
                    stock.setQuantity(itemReq.getQuantity());
                } else {
                    stock.setQuantity(stock.getQuantity() + itemReq.getQuantity());
                }

                inventoryStockRepository.save(stock);
            }

            // 6️⃣ Total Calculation
            BigDecimal itemTotal = itemReq.getPrice()
                    .multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);
        }

        // 7️⃣ Save Total
        purchase.setTotalAmount(totalAmount);
        purchaseRepository.save(purchase);

        return purchase.getId();
    }
    
    public List<PurchaseResponse> getAllPurchases() {

        return purchaseRepository.findAll()
                .stream()
                .map(purchase -> new PurchaseResponse(
                        purchase.getId(),
                        purchase.getVendor().getName(),
                        purchase.getPurchaseDate(),
                        purchase.getTotalAmount()
                ))
                .collect(Collectors.toList());
    }
}