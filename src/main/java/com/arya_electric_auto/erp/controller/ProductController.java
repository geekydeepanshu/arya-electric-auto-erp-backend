package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.entity.Product;
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
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 🔥 GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        } else {
            return ResponseEntity.badRequest().body("Product not found");
        }
    }
}