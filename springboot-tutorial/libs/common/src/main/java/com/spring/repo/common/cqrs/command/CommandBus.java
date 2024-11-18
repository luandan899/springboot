package com.spring.repo.common.cqrs.command;


import com.spring.repo.common.cqrs.event.DomainEvent;

public interface CommandBus {
  <R, E extends DomainEvent> R dispatch(Command<R, E> command) throws Exception;
}
