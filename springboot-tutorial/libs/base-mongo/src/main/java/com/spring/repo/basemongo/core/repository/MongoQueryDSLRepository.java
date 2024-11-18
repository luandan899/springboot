package com.spring.repo.basemongo.core.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MongoQueryDSLRepository<T>
        extends MongoRepository<T, String>, QuerydslPredicateExecutor<T> {
    List<T> findAll();

    List<T> findAll(Predicate predicate);
}
