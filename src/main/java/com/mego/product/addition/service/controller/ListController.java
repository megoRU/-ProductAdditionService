package com.mego.product.addition.service.controller;

import com.mego.product.addition.service.api.request.ListRequest;
import com.mego.product.addition.service.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/list")
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @PostMapping("")
    private ResponseEntity<?> createList(@RequestBody ListRequest listRequest) {
        return listService.create(listRequest);
    }

    @GetMapping("")
    private ResponseEntity<?> getList(@RequestParam(name = "id", required = false) String id) {
        return listService.get(id);
    }
}
