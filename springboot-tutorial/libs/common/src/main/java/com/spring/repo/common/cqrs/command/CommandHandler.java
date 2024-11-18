package com.spring.repo.common.cqrs.command;


import com.spring.repo.common.cqrs.event.DomainEvent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface CommandHandler<R, E extends DomainEvent, C extends Command<R, E>> {
  CommandResult<R, E> handle(C command) throws Exception;

  default boolean matches(C command) {
    Class<?> handlerType = getClass();
    Class<?> commandType = command.getClass();

    Type[] interfaces = handlerType.getGenericInterfaces();
    Type genericSuperclass = handlerType.getGenericSuperclass();

    ParameterizedType type;
    if (interfaces.length > 0) {
      type = (ParameterizedType) interfaces[0];
    } else {
      type = (ParameterizedType) genericSuperclass;
    }

    Type handlerCommand =
      type.getActualTypeArguments().length > 2
        ? type.getActualTypeArguments()[2]
        : type.getActualTypeArguments()[1];
    Class<?> handlerCommandClass;

    if (handlerCommand instanceof ParameterizedType) {
      ParameterizedType parameterized = (ParameterizedType) handlerCommand;
      handlerCommandClass = (Class<?>) parameterized.getRawType();
    } else {
      handlerCommandClass = (Class<?>) handlerCommand;
    }

    return handlerCommandClass.isAssignableFrom(commandType);
  }
}
