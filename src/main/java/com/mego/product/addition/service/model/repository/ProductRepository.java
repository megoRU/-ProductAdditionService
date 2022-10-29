package com.mego.product.addition.service.model.repository;

import com.mego.product.addition.service.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @NonNull
    @Override
    Optional<Product> findById(@NonNull Long aLong);
}