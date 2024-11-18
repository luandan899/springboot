package com.spring.repo.common.cqrs.query;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface QueryHandler <R, Q extends Query<R>> {
    R handle(Q query) throws Exception;

    default boolean matches(Q query) {
        Class<?> handlerType = getClass();
        Class<?> queryType = query.getClass();

        Type[] interfaces = handlerType.getGenericInterfaces();
        Type genericSuperclass = handlerType.getGenericSuperclass();

        ParameterizedType type;
        if (interfaces.length > 0) {
            type = (ParameterizedType) interfaces[0];
        } else {
            type = (ParameterizedType) genericSuperclass;
        }

        Type handlerQuery = type.getActualTypeArguments()[1];
        Class<?> handlerQueryClass;

        if (handlerQuery instanceof ParameterizedType) {
            ParameterizedType parameterized = (ParameterizedType) handlerQuery;
            handlerQueryClass = (Class<?>) parameterized.getRawType();
        } else {
            handlerQueryClass = (Class<?>) handlerQuery;
        }

        return handlerQueryClass.isAssignableFrom(queryType);
    }
}
