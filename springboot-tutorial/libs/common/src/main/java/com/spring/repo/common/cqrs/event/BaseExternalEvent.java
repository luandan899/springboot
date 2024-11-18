package com.spring.repo.common.cqrs.event;

import lombok.Getter;
import org.slf4j.MDC;

@Getter
public abstract class BaseExternalEvent {
  private final String correlationId = MDC.get("correlationId");

  public void withConfidentialInfoRemoved() {}
}
