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

import java.util.Optional;

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

    public ResponseEntity<?> get(String id) {
        if (id == null) {
            java.util.List<List> optionalList = listRepository.findAll();
            if (optionalList.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(
                        "Lists not found",
                        "Lists not found"), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(optionalList, HttpStatus.OK);
            }
        } else {
            Optional<List> optionalList = listRepository.findById(Long.valueOf(id));
            if (optionalList.isPresent()) {
                return new ResponseEntity<>(optionalList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ErrorResponse(
                        "List not found",
                        "List not found"), HttpStatus.NOT_FOUND);
            }
        }
    }


}