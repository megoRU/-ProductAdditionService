package com.mego.product.addition.service.model.repository.impl;

import com.mego.product.addition.service.model.entity.Product;

import java.util.List;

public interface ProductImpl {

    String getTotalAmount();
    List<Product> getProducts();
}
