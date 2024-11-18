package com.spring.repo.common.cqrs.command;


import com.spring.repo.common.cqrs.event.DomainEvent;

public abstract class UnauthorizedBaseCommand<R, E extends DomainEvent> extends Command<R, E> {


  @Override
  public String getType() {
    return this.getClass().getName();
  }

  @Override
  public Command<R, E> withConfidentialInfoRemoved() {
    return this;
  }

}
