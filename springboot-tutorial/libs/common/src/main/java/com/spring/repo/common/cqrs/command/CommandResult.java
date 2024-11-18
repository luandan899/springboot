package com.spring.repo.common.cqrs.command;

import com.spring.repo.common.cqrs.event.DomainEvent;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class CommandResult<R, E extends DomainEvent>{
  private final R result;
  private final List<E> events;

  public CommandResult(R result, List<E> events) {
    this.result = result;
    this.events = events;
  }

  public CommandResult(R result) {
    this.result = result;
    this.events = Collections.emptyList();
  }

  public static <E extends DomainEvent> CommandResult<ResponseEntity, E> empty() {
    return new CommandResult<>(ResponseEntity.ok().build());
  }

  public static <R, E extends DomainEvent> CommandResult<R, E> of(R result) {
    return new CommandResult<>(result);
  }

  @SafeVarargs
  public static <R, E extends DomainEvent> CommandResult<R, E> of(R result, E... events) {
    return new CommandResult<>(result, Arrays.asList(events));
  }

  @SafeVarargs
  public static <R, E extends DomainEvent> CommandResult<R, E> ofEmptyResult(R emptyResult, E... events) {
    return new CommandResult<>(emptyResult, Arrays.asList(events));
  }
}
