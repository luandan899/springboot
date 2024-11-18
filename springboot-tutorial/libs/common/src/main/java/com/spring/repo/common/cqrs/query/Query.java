package com.spring.repo.common.cqrs.query;

public abstract  class Query<R> {
    public abstract String getType();
    public abstract Query<R> withConfidentialInfoRemoved();
}
