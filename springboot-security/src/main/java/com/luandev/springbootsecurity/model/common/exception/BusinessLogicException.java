package com.luandev.springbootsecurity.model.common.exception;

import com.luandev.springbootsecurity.model.user.enums.ErrorMessage;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.util.List;

@Getter
@Builder
public class BusinessLogicException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7389659106153108528L;

    private ErrorMessage errorMessage;

    private List<String> params;

    public BusinessLogicException(ErrorMessage errorMessage, List<String> params) {
        super();
        this.errorMessage = errorMessage;
        this.params = params;
    }


}
