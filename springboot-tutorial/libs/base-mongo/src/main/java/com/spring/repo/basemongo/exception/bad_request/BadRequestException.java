package com.spring.repo.basemongo.exception.bad_request;


import com.spring.repo.basemongo.exception.GeneralException;

public class BadRequestException extends GeneralException {

  public BadRequestException(String errorCode) {
    super(errorCode);
    this.setHttpStatus(400);
  }
}
