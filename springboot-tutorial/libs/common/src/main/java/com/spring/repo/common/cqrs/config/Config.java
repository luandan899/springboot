package com.spring.repo.common.cqrs.config;

import com.spring.repo.common.cqrs.command.ApplicationCommandBus;
import com.spring.repo.common.cqrs.command.CommandBus;
import com.spring.repo.common.cqrs.command.CommandHandler;
import com.spring.repo.common.cqrs.query.ApplicationQueryBus;
import com.spring.repo.common.cqrs.query.QueryBus;
import com.spring.repo.common.cqrs.query.QueryHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {


    @Autowired
    private ObjectProvider<QueryHandler> queryHandlerObjectProvider;

    @Bean
    public QueryBus queryBus() {
        return new ApplicationQueryBus(queryHandlerObjectProvider);
    }


    @Autowired
    private ObjectProvider<CommandHandler> commandHandlerObjectProvider;

    @Bean
    public CommandBus commandBus() {
        return new ApplicationCommandBus(commandHandlerObjectProvider);
    }


}
