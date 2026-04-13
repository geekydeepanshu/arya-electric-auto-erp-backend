package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.ProductResponse;
import com.arya_electric_auto.erp.entity.Product;
import com.arya_electric_auto.erp.mapper.ProductMapper;
import com.arya_electric_auto.erp.repository.ProductRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 🔥 GET ALL PRODUCTS
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    // 🔥 GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(ProductMapper.toResponse(optionalProduct.get()));
        } else {
            return ResponseEntity.badRequest().body("Product not found");
        }
    }
}
