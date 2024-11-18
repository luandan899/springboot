package com.spring.repo.basemongo.exception.external_error;

public class ExternalCalledFailedException extends ExternalException {

  public ExternalCalledFailedException(Exception debug) {
    super("EXTERNAL_CALLED_FAILED");
    this.setRootCause(debug);
    this.setMsg(debug.getMessage());
  }

  public ExternalCalledFailedException(String msg) {
    super("EXTERNAL_CALLED_FAILED");
    this.setMsg(msg);
  }
}
