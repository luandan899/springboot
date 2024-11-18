package com.spring.repo.common.cqrs.command;


import com.spring.repo.common.cqrs.event.DomainEvent;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ApplicationCommandBus implements CommandBus {

    private Map<String, CommandHandler> commandHandlerMap = new ConcurrentHashMap<>();
    private final ObjectProvider<CommandHandler> commandHandlerObjectProvider;

    public ApplicationCommandBus(ObjectProvider<CommandHandler> commandHandlerObjectProvider) {
        this.commandHandlerObjectProvider = commandHandlerObjectProvider;
    }

    @Override
    public <R, E extends DomainEvent> R dispatch(Command<R, E> command) throws Exception {
        String commandType = command.getType();
        CommandHandler<R, E, Command<R, E>> handler = this.commandHandlerMap.get(commandType);
        if (handler == null) {
            List<CommandHandler> handlers = this.commandHandlerObjectProvider.stream()
                    .filter(it -> it.matches(command))
                    .collect(Collectors.toList());
            handler = handlers.get(0);
            this.commandHandlerMap.put(commandType, handler);
        }
        CommandResult<R, E> commandResult = handler.handle(command);
        return commandResult.getResult();
    }

}
