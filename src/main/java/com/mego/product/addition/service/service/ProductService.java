package com.mego.product.addition.service.service;


import com.mego.product.addition.service.api.request.ProductRequest;
import com.mego.product.addition.service.api.request.ProductToList;
import com.mego.product.addition.service.api.response.ErrorResponse;
import com.mego.product.addition.service.api.response.ListOfProducts;
import com.mego.product.addition.service.model.entity.List;
import com.mego.product.addition.service.model.entity.Product;
import com.mego.product.addition.service.model.repository.ListRepository;
import com.mego.product.addition.service.model.repository.ProductImpl;
import com.mego.product.addition.service.model.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public final ListRepository listRepository;

    public ProductService(ProductRepository productRepository, ListRepository listRepository) {
        this.productRepository = productRepository;
        this.listRepository = listRepository;
    }

    public ResponseEntity<?> create(ProductRequest productRequest) {
        if (productRequest.getDescription() == null || productRequest.getName() == null) {
            return new ResponseEntity<>(new ErrorResponse(
                    "name or description is null",
                    "name or description can`t be null"), HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        product.setDescription(productRequest.getDescription());
        product.setKcal(productRequest.getKcal());
        product.setName(productRequest.getName());

        if (productRequest.getListId() != null) {
            Optional<List> optionalList = listRepository.findById(Long.parseLong(productRequest.getListId()));

            if (optionalList.isPresent()) {
                product.setListId(optionalList.get());
            } else {
                return new ResponseEntity<>(new ErrorResponse(
                        "List not found",
                        "List not found"), HttpStatus.NOT_FOUND);
            }
        }

        Product productSaved = productRepository.save(product);
        return new ResponseEntity<>(productSaved.getId(), HttpStatus.OK);
    }

    public ResponseEntity<?> update(ProductToList productToList) {
        Optional<List> optionalList = listRepository.findById(productToList.getListId());
        Optional<Product> optionalProduct = productRepository.findById(productToList.getProductId());

        if (optionalList.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(
                    "List not found",
                    "List not found"), HttpStatus.NOT_FOUND);
        } else if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(
                    "Product not found",
                    "Product not found"), HttpStatus.NOT_FOUND);
        }

        List list = optionalList.get();
        Product product = optionalProduct.get();
        product.setListId(list);
        Product productSaved = productRepository.save(product);

        return new ResponseEntity<>(productSaved, HttpStatus.OK);
    }

    public ResponseEntity<?> get(String id) {
        if (id == null) {
            java.util.List<Product> optionalProduct = productRepository.findAll();
            if (optionalProduct.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(
                        "Products not found",
                        "Products not found"), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(optionalProduct, HttpStatus.OK);
            }
        } else {
            Optional<Product> optionalProduct = productRepository.findById(Long.valueOf(id));
            if (optionalProduct.isPresent()) {
                return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ErrorResponse(
                        "Product not found",
                        "Product not found"), HttpStatus.NOT_FOUND);
            }
        }
    }

    public ResponseEntity<?> getAllProducts(String id) {
        java.util.List<ProductImpl> optionalProduct = productRepository.getProductsByListId(Long.valueOf(id));
        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(
                    "Products not found",
                    "Products not found"), HttpStatus.NOT_FOUND);
        } else {
            int sum = optionalProduct.stream()
                    .map(k -> Integer.valueOf(k.getTotalAmount()))
                    .mapToInt(Integer::intValue)
                    .sum();

            java.util.List<Product> productList = new LinkedList<>();

            for (ProductImpl product : optionalProduct) {
                java.util.List<Product> products = product.getProducts();
                productList.addAll(products);
            }

            return new ResponseEntity<>(new ListOfProducts(productList, sum), HttpStatus.OK);
        }
    }
}
