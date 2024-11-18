package com.spring.repo.basemongo.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Not found Id: %s"),
    DATA_UN_VALID("DATA_UN_VALID", "Please check request body: %s"),
    INVALID_PUBLISH_FOR("INVALID_PUBLISH_FOR", "Please check publish for: %s"),
    MISSING_ID("MISSING_ID", "Missing Id: %s"),
    RESOURCE_USED("RESOURCE_USED", "Resource: [%s] - [id: %s] have been used already!!"),
    EMAIL_EXISTED("EMAIL_EXISTED", "Email: %s existed!!"),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "User: %s already exists!!"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User: %s not found!!");

    ErrorMessage(String code, String messageFm){
        this.code = code;
        this.messageFm = messageFm;
    }

    private String code;

    private String messageFm;
}
