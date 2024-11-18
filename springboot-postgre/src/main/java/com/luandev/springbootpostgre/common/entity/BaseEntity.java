package com.luandev.springbootpostgre.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;


    @PrePersist
    public void prePersist() {
        //TODO: will be processed later
        this.createdBy = "anonymous";
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        //TODO: will be processed later
        this.updatedBy = "anonymous";
        this.updatedAt = ZonedDateTime.now();
    }
}
