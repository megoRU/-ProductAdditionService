package com.mego.product.addition.service.model.repository;

import com.mego.product.addition.service.model.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {

}