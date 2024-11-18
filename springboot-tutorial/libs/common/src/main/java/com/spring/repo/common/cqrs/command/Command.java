package com.spring.repo.common.cqrs.command;


import com.spring.repo.common.cqrs.event.DomainEvent;

public abstract class Command<R, E extends DomainEvent> {
  public abstract String getType();
  public abstract Command<R, E> withConfidentialInfoRemoved();

}
