package com.spring.repo.eccormerce.dao.repository;

import com.spring.repo.eccormerce.dao.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {


}
