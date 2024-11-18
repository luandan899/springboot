package com.luandev.springbootpostgre.predicate.impl;

import com.luandev.springbootpostgre.model.Address;
import com.luandev.springbootpostgre.model.User;
import com.luandev.springbootpostgre.repository.criteria.QueryCriteria;
import com.luandev.springbootpostgre.repository.criteria.QueryCriteriaConsumer;
import com.luandev.springbootpostgre.predicate.UserPredicateBuilder;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class UserPredicateBuilderImpl implements UserPredicateBuilder {

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<User> root, List<QueryCriteria> criteriaList, String address) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (!criteriaList.isEmpty()) {
            QueryCriteriaConsumer searchConsumer = new QueryCriteriaConsumer(predicate, criteriaBuilder, root);
            criteriaList.forEach(searchConsumer);
            predicate = searchConsumer.getPredicate();
        }

        if (StringUtils.hasText(address)) {
            Join<Address, User> userAddressJoin = root.join("addresses", JoinType.LEFT);
            Predicate addressPredicate = criteriaBuilder.equal(userAddressJoin.get("city"), address);
            predicate = criteriaBuilder.and(predicate, addressPredicate);
        }

        return predicate;
    }
}
