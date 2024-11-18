package com.spring.repo.basemongo.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@SuperBuilder
@NoArgsConstructor
@Data
public class BaseDTO {
    protected String id;
    protected ZonedDateTime createdAt;
    protected ZonedDateTime lastModifiedAt;
    protected String createdBy;
    protected String lastModifiedBy;
}
