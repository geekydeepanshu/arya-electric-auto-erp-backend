package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.ProductResponse;
import com.arya_electric_auto.erp.entity.Product;
import com.arya_electric_auto.erp.entity.ProductRequest;
import com.arya_electric_auto.erp.entity.ProductType;
import com.arya_electric_auto.erp.mapper.ProductMapper;
import com.arya_electric_auto.erp.repository.JobCardItemRepository;
import com.arya_electric_auto.erp.repository.InventoryStockRepository;
import com.arya_electric_auto.erp.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final JobCardItemRepository jobCardItemRepository;
    private final InventoryStockRepository inventoryStockRepository;

    public ProductService(ProductRepository productRepository,
                          JobCardItemRepository jobCardItemRepository,
                          InventoryStockRepository inventoryStockRepository) {
        this.productRepository = productRepository;
        this.jobCardItemRepository = jobCardItemRepository;
        this.inventoryStockRepository = inventoryStockRepository;
    }
    
    
    public ProductResponse createProduct(ProductRequest request) {

        if (request.isSerialized == null) {
            throw new RuntimeException("isSerialized is required");
        }

        Product product = new Product();

        product.setName(request.name);
        product.setType(request.type);
        product.setBrand(request.brand);
        product.setPrice(request.price);
        product.setIsSerialized(request.isSerialized);

        product.setCreatedAt(java.time.LocalDateTime.now());

        return ProductMapper.toResponse(productRepository.save(product));
    }

    // =====================================================
    // 🔹 GET ALL ACTIVE PRODUCTS
    // =====================================================

    public List<ProductResponse> getAllProducts() {

        return productRepository.findByDeletedAtIsNull()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    // =====================================================
    // 🔹 GET PRODUCT BY ID
    // =====================================================

    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getDeletedAt() != null) {
            throw new RuntimeException("Product is deleted");
        }

        return ProductMapper.toResponse(product);
    }

    // =====================================================
    // 🔹 DELETE PRODUCT (SOFT DELETE)
    // =====================================================

    @Transactional
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        

        // 🔥 Already deleted
        if (product.getDeletedAt() != null) {
            throw new RuntimeException("Product already deleted");
        }

        // 🔥 CHECK: used in billing
        boolean usedInBilling = jobCardItemRepository.existsByProductId(id);

        // 🔥 CHECK: has stock
        boolean hasStock = inventoryStockRepository.existsByProductId(id);

        if (hasStock) {
            throw new RuntimeException("Cannot delete product with available stock");
        }

        // 👉 Always soft delete (safe approach)
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    // =====================================================
    // 🔹 RESTORE PRODUCT (OPTIONAL BUT USEFUL)
    // =====================================================

    @Transactional
    public void restoreProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getDeletedAt() == null) {
            throw new RuntimeException("Product is already active");
        }

        product.setDeletedAt(null);
        productRepository.save(product);
    }

    // =====================================================
    // 🔹 GET ALL (INCLUDING DELETED) [ADMIN USE]
    // =====================================================

    public List<ProductResponse> getAllIncludingDeleted() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }
    
    public List<ProductResponse> getProductsByType(ProductType type) {

        return productRepository.findByTypeAndDeletedAtIsNull(type)
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }
    
    
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getDeletedAt() != null) {
            throw new RuntimeException("Cannot update deleted product");
        }

        // 🔥 VALIDATION: NAME
        if (request.name == null || request.name.trim().isEmpty()) {
            throw new RuntimeException("Product name is required");
        }

        // 🔥 VALIDATION: PRICE
        

        if (request.price == null || request.price.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price must be >= 0");
        }

        // 🔥 DUPLICATE NAME CHECK (excluding self)
        boolean duplicate = productRepository.existsByNameIgnoreCaseAndIdNot(
                request.name, id
        );

        if (duplicate) {
            throw new RuntimeException("Product with same name already exists");
        }

        // 🔥 UPDATE FIELDS
        product.setName(request.name);
        product.setType(request.type);
        product.setBrand(request.brand);
        product.setPrice(request.price);

        if (request.isSerialized != null) {
            product.setIsSerialized(request.isSerialized);
        }

        product.setUpdatedAt(java.time.LocalDateTime.now());

        return ProductMapper.toResponse(productRepository.save(product));
    }
}