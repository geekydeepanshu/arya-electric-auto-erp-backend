package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.InventoryUnitResponse;
import com.arya_electric_auto.erp.dto.InventoryStockResponse;
import com.arya_electric_auto.erp.dto.InventorySummaryResponse;
import com.arya_electric_auto.erp.entity.InventoryStock;
import com.arya_electric_auto.erp.entity.InventoryUnit;
import com.arya_electric_auto.erp.entity.Product;
import com.arya_electric_auto.erp.repository.InventoryStockRepository;
import com.arya_electric_auto.erp.repository.InventoryUnitRepository;
import com.arya_electric_auto.erp.repository.ProductRepository;

import org.springframework.stereotype.Service;

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

    // 🔹 1. Get all available serialized units
    public List<InventoryUnitResponse> getAvailableUnits() {

        return inventoryUnitRepository.findByStatus("AVAILABLE")
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

    // 🔹 2. Get available units by product
    public List<InventoryUnitResponse> getAvailableUnitsByProduct(Long productId) {

        return inventoryUnitRepository
                .findByProductIdAndStatus(productId, "AVAILABLE")
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

    // 🔹 3. Get all bulk stock
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

    // 🔥 4. INVENTORY SUMMARY (MOST IMPORTANT API)
    public List<InventorySummaryResponse> getInventorySummary() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> {

            // 🔴 SERIALIZED PRODUCTS
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

                // 🔵 BULK PRODUCTS (FIXED OPTIONAL HANDLING)
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
}