package com.mego.product.addition.service.model.repository;

import com.mego.product.addition.service.model.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {

    @NonNull
    @Override
    Optional<List> findById(@NonNull Long aLong);
}