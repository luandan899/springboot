package com.spring.repo.common.exception;

import com.spring.repo.common.enums.ErrorMessage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BusinessLogicException extends RuntimeException {

    private ErrorMessage errorMessage;

    private List<String> params;

    public BusinessLogicException(ErrorMessage errorMessage, List<String> params) {
        super(errorMessage.getMessageFm().formatted(params));
        this.errorMessage = errorMessage;
        this.params = params;
    }


}
