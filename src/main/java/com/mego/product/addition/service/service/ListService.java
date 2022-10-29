package com.mego.product.addition.service.service;

import com.mego.product.addition.service.api.request.ListRequest;
import com.mego.product.addition.service.api.response.ErrorResponse;
import com.mego.product.addition.service.model.entity.List;
import com.mego.product.addition.service.model.repository.ListRepository;
import com.mego.product.addition.service.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ListService {

    private final ListRepository listRepository;
    public final ProductRepository productRepository;

    @Autowired
    public ListService(ListRepository listRepository, ProductRepository productRepository) {
        this.listRepository = listRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<?> create(ListRequest listRequest) {
        if (listRequest.getName() == null) {
            return new ResponseEntity<>(new ErrorResponse(
                    "name is null",
                    "name can`t be null"), HttpStatus.BAD_REQUEST);
        }
        List list = new List();
        list.setName(listRequest.getName());
        List listSaved = listRepository.save(list);
        return new ResponseEntity<>(listSaved.getId(), HttpStatus.OK);
    }
}