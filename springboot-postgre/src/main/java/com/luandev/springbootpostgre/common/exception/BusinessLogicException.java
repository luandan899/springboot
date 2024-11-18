package com.luandev.springbootpostgre.common.exception;

import com.luandev.springbootpostgre.common.enums.ErrorMessage;
import lombok.*;

import java.io.Serial;
import java.util.List;

@Getter
@Builder
public class BusinessLogicException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7389659106153108528L;

    private ErrorMessage errorMessage;

    private List<Object> params;

    public BusinessLogicException(ErrorMessage errorMessage, List<Object> params) {
        super();
        this.errorMessage = errorMessage;
        this.params = params;
    }


}
