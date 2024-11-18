package com.spring.repo.common.cqrs.query;


public class UnauthorizedBaseQuery<R> extends Query<R> {
  @Override
  public String getType() {
    return this.getClass().getName();
  }

  @Override
  public Query<R> withConfidentialInfoRemoved() {
    return this;
  }

}
