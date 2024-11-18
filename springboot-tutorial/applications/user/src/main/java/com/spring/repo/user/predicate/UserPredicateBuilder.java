package com.spring.repo.user.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.spring.repo.common.utils.WordUtils;
import com.spring.repo.user.documents.QUser;

import java.util.Optional;

public class UserPredicateBuilder {
    private static final QUser Q_TABLE = QUser.user;

    public static Predicate getPaged(String search) {
        BooleanExpression[] booleanExpression = {Q_TABLE.id.isNotNull()};
        Optional.ofNullable(search)
                .ifPresent(name -> booleanExpression[0] = booleanExpression[0]
                        .and(Q_TABLE.name.containsIgnoreCase(WordUtils.toNonVietnamese(name))));
        return booleanExpression[0];
    }

    public static Predicate getUserByEmail(String email) {
        return Q_TABLE.email.contains(email);
    }

    public static Predicate existsByEmail(String email) {
        BooleanExpression[] booleanExpression = {Q_TABLE.id.isNotNull()};

        Optional.ofNullable(email).ifPresent(value -> {
            booleanExpression[0] = booleanExpression[0].and(Q_TABLE.email.eq(value));
        });

        return booleanExpression[0];

    }
}
