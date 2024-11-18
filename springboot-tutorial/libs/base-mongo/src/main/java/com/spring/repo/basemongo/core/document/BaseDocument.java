package com.spring.repo.basemongo.core.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseDocument {
    @Id
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    private ZonedDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    private ZonedDateTime lastModifiedAt;

    @LastModifiedBy
    private String lastModifiedBy;

    @Version
    private Long version;
}
