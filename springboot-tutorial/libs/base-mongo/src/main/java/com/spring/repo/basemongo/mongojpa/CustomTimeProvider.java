package com.spring.repo.basemongo.mongojpa;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Component("dateTimeProvider")
public class CustomTimeProvider implements DateTimeProvider {

  @Override
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(ZonedDateTime.now());
  }
}
