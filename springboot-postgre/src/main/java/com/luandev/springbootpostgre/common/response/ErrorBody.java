package com.luandev.springbootpostgre.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorBody {
    private String code;
    private String message;
}
