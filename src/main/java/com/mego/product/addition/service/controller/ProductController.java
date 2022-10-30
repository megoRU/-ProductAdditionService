package com.mego.product.addition.service.controller;

import com.mego.product.addition.service.api.request.ProductRequest;
import com.mego.product.addition.service.api.request.ProductToList;
import com.mego.product.addition.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    private ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @PutMapping("")
    private ResponseEntity<?> productToList(@RequestBody ProductToList productToList) {
        return productService.update(productToList);
    }

    @GetMapping("")
    private ResponseEntity<?> getProduct(@RequestParam(name = "id", required = false) String id) {
        return productService.get(id);
    }

    @GetMapping("/list")
    private ResponseEntity<?> getAllProductByListId(@RequestParam String id) {
        return productService.getAllProducts(id);
    }
}