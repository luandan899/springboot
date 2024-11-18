package com.spring.repo.basemongo.exception.bad_request;

public class DuplicatedDataException extends BadRequestException {

  public static final String PARAM_RESOURCE = "resource";

  public DuplicatedDataException(String resourceName, String... args) {
    super("DUPLICATED_DATA");
    this.getParams().put(PARAM_RESOURCE, resourceName);
    this.setArgs(args);
  }
}
