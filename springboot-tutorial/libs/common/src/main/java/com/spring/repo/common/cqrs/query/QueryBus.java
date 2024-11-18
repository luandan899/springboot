package com.spring.repo.common.cqrs.query;

public interface QueryBus {
    <R> R dispatch(BaseQuery<R> query) throws Exception;
}
