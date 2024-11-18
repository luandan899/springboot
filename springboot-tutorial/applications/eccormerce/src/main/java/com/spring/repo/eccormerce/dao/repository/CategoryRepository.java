package com.spring.repo.eccormerce.dao.repository;

import com.spring.repo.eccormerce.dao.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
