package com.spring.repo.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BaseResponse<T> {

    private int status;

    private T data;
}
