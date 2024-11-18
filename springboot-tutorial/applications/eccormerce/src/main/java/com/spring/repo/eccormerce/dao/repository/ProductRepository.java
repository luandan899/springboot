package com.spring.repo.eccormerce.dao.repository;

import com.spring.repo.eccormerce.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {
}
