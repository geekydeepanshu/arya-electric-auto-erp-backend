package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.InventoryUnitResponse;
import com.arya_electric_auto.erp.dto.InventoryStockResponse;
import com.arya_electric_auto.erp.dto.InventorySummaryResponse;
import com.arya_electric_auto.erp.entity.InventoryStatus;
import com.arya_electric_auto.erp.entity.InventoryStock;
import com.arya_electric_auto.erp.entity.InventoryUnit;
import com.arya_electric_auto.erp.entity.Product;
import com.arya_electric_auto.erp.repository.InventoryStockRepository;
import com.arya_electric_auto.erp.repository.InventoryUnitRepository;
import com.arya_electric_auto.erp.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryUnitRepository inventoryUnitRepository;
    private final InventoryStockRepository inventoryStockRepository;
    private final ProductRepository productRepository;

    public InventoryService(
            InventoryUnitRepository inventoryUnitRepository,
            InventoryStockRepository inventoryStockRepository,
            ProductRepository productRepository
    ) {
        this.inventoryUnitRepository = inventoryUnitRepository;
        this.inventoryStockRepository = inventoryStockRepository;
        this.productRepository = productRepository;
    }

    // =====================================================
    // 🔹 1. GET ALL AVAILABLE SERIALIZED UNITS
    // =====================================================

    public List<InventoryUnitResponse> getAvailableUnits() {

        return inventoryUnitRepository.findByStatus(InventoryStatus.IN_STOCK)
                .stream()
                .map(unit -> new InventoryUnitResponse(
                        unit.getId(),
                        unit.getSerialNumber(),
                        unit.getStatus(),
                        unit.getProduct().getId(),
                        unit.getProduct().getName()
                ))
                .collect(Collectors.toList());
    }

    // =====================================================
    // 🔹 2. GET AVAILABLE UNITS BY PRODUCT
    // =====================================================

    public List<InventoryUnitResponse> getAvailableUnitsByProduct(Long productId) {

        return inventoryUnitRepository
        		.findByProductIdAndStatus(productId, InventoryStatus.IN_STOCK)
                .stream()
                .map(unit -> new InventoryUnitResponse(
                        unit.getId(),
                        unit.getSerialNumber(),
                        unit.getStatus(),
                        unit.getProduct().getId(),
                        unit.getProduct().getName()
                ))
                .collect(Collectors.toList());
    }

    // =====================================================
    // 🔹 3. GET ALL BULK STOCK
    // =====================================================

    public List<InventoryStockResponse> getAllStock() {

        return inventoryStockRepository.findAll()
                .stream()
                .map(stock -> new InventoryStockResponse(
                        stock.getProduct().getId(),
                        stock.getProduct().getName(),
                        stock.getQuantity()
                ))
                .collect(Collectors.toList());
    }

    // =====================================================
    // 🔥 4. INVENTORY SUMMARY (IMPORTANT)
    // =====================================================

    public List<InventorySummaryResponse> getInventorySummary() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> {

            if (Boolean.TRUE.equals(product.getIsSerialized())) {

                Long count = inventoryUnitRepository
                        .countAvailableByProduct(product.getId());

                return new InventorySummaryResponse(
                        product.getId(),
                        product.getName(),
                        true,
                        count,
                        null
                );

            } else {

                Optional<InventoryStock> optionalStock =
                        inventoryStockRepository.findByProductId(product.getId());

                Integer quantity = optionalStock
                        .map(InventoryStock::getQuantity)
                        .orElse(0);

                return new InventorySummaryResponse(
                        product.getId(),
                        product.getName(),
                        false,
                        null,
                        quantity
                );
            }

        }).collect(Collectors.toList());
    }

    // =====================================================
    // 🔥 5. VALIDATE STOCK (FOR BILLING)
    // =====================================================

    public void validateStock(Long productId, Integer qty) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 🔴 SERIALIZED
        if (Boolean.TRUE.equals(product.getIsSerialized())) {

            Long available = inventoryUnitRepository
                    .countAvailableByProduct(productId);

            if (available < qty) {
                throw new RuntimeException("Not enough serialized units available");
            }

        } else {

            // 🔵 BULK
            Integer available = inventoryStockRepository
                    .findByProductId(productId)
                    .map(InventoryStock::getQuantity)
                    .orElse(0);

            if (available < qty) {
                throw new RuntimeException("Not enough stock available");
            }
        }
    }

    // =====================================================
    // 🔥 6. DEDUCT BULK STOCK
    // =====================================================

    @Transactional
    public void deductStock(Long productId, Integer qty) {

        InventoryStock stock = inventoryStockRepository
                .findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        if (stock.getQuantity() < qty) {
            throw new RuntimeException("Stock insufficient");
        }

        stock.setQuantity(stock.getQuantity() - qty);
        inventoryStockRepository.save(stock);
    }

    // =====================================================
    // 🔥 7. DEDUCT SERIALIZED UNITS (SELECTED)
    // =====================================================

    @Transactional
    public void deductSerializedUnits(List<Long> unitIds) {

        List<InventoryUnit> units = inventoryUnitRepository.findAllById(unitIds);

        if (units.size() != unitIds.size()) {
            throw new RuntimeException("Invalid unit selection");
        }

        for (InventoryUnit unit : units) {

            if (InventoryStatus.IN_STOCK != unit.getStatus()) {
                throw new RuntimeException("Unit not available: " + unit.getSerialNumber());
            }

            unit.setStatus(InventoryStatus.SOLD); // or SOLD
            inventoryUnitRepository.save(unit);
        }
    }
}