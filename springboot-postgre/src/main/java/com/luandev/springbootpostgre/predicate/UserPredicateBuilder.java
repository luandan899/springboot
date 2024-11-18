package com.luandev.springbootpostgre.predicate;

import com.luandev.springbootpostgre.model.User;
import com.luandev.springbootpostgre.repository.criteria.QueryCriteria;
import jakarta.persistence.criteria.*;

import java.util.List;

public interface UserPredicateBuilder {

    Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<User> root, List<QueryCriteria> criteriaList, String address);
}
