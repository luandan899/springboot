package com.spring.repo.basemongo.mongojpa;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        PersistenceConfig.class
})
public @interface EnableMongoJpa {
}
