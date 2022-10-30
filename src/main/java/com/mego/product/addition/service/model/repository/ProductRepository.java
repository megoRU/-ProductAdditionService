package com.mego.product.addition.service.model.repository;

import com.mego.product.addition.service.model.entity.Product;
import com.mego.product.addition.service.model.repository.impl.ProductImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @NonNull
    @Override
    Optional<Product> findById(@NonNull Long aLong);

    @Query(value = "SELECT SUM(p.kcal) AS totalAmount, p AS products FROM Product p WHERE p.listId.id = :id GROUP BY p.id")
    List<ProductImpl> getProductsByListId(@NonNull Long id);

//    @Query(value = "SELECT p AS products FROM Product p WHERE p.listId.id = :id GROUP BY p.id")
//    List<Product> getProductsByListId(@NonNull Long id);
}