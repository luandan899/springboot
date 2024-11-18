package com.luandev.springbootpostgre.common.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Not found Id: %s"),
    DATA_UN_VALID("DATA_UN_VALID", "Please check request body: %s"),
    EMAIL_EXISTED("EMAIL_EXISTED", "Email: %s existed!!"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User: %s not found!!"),
    EMAIL_NOT_FOUND("EMAIL_NOT_FOUND", "User: %s not found!!"),
    PASSWORD_EXCEPTION("PASSWORD_EXCEPTION", "Password:  %s do not match"),
    TOKEN_ALREADY_INVALID("TOKEN_ALREADY_INVALID", "Token is already invalidated!"),
    STATUS_NOT_VALID("STATUS_NOT_VALID", " User status: %s is not valid!");

    ErrorMessage(String code, String messageFm) {
        this.code = code;
        this.messageFm = messageFm;
    }

    private String code;

    private String messageFm;
}
