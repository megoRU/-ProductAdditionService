package com.mego.product.addition.service.service;


import com.mego.product.addition.service.api.request.ProductRequest;
import com.mego.product.addition.service.api.request.ProductToList;
import com.mego.product.addition.service.api.response.ErrorResponse;
import com.mego.product.addition.service.model.entity.List;
import com.mego.product.addition.service.model.entity.Product;
import com.mego.product.addition.service.model.repository.ListRepository;
import com.mego.product.addition.service.model.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Product productSaved = productRepository.save(product);
        return new ResponseEntity<>(productSaved.getId(), HttpStatus.OK);
    }


    public ResponseEntity<?> update(ProductToList productToList) {
        Optional<List> optionalList = listRepository.findById(productToList.getListId());
        Optional<Product> optionalProduct = productRepository.findById(productToList.getProductId());


        return null;
    }
}
