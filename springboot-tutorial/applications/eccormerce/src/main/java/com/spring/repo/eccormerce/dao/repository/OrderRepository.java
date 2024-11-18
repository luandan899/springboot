package com.spring.repo.eccormerce.dao.repository;

import com.spring.repo.eccormerce.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
