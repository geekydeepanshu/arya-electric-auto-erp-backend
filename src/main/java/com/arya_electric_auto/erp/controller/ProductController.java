package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.ProductResponse;
import com.arya_electric_auto.erp.entity.ProductRequest;
import com.arya_electric_auto.erp.entity.ProductType;
import com.arya_electric_auto.erp.service.ProductService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // =====================================================
    // 🔹 CREATE PRODUCT
    // =====================================================

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {

        if (request.isSerialized == null) {
            return ResponseEntity.badRequest().body("isSerialized is required");
        }

        return ResponseEntity.ok(productService.createProduct(request));
    }

    // =====================================================
    // 🔹 GET ALL PRODUCTS
    // =====================================================

    @GetMapping
    public List<ProductResponse> getAllProducts(
            @RequestParam(required = false) String type
    ) {

        if (type != null) {
            return productService.getProductsByType(
                    Enum.valueOf(ProductType.class, type)
            );
        }

        return productService.getAllProducts();
    }

    // =====================================================
    // 🔹 GET PRODUCT BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // =====================================================
    // 🔹 DELETE PRODUCT (SOFT DELETE)
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request
    ) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}