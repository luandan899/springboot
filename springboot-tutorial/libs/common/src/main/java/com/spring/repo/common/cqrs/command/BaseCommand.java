package com.spring.repo.common.cqrs.command;


import com.spring.repo.common.cqrs.event.DomainEvent;

public abstract class BaseCommand<R, E extends DomainEvent> extends UnauthorizedBaseCommand<R, E> {

}
