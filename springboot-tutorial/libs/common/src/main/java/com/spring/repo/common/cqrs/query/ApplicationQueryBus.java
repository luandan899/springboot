package com.spring.repo.common.cqrs.query;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ApplicationQueryBus implements QueryBus{
    private Map<String, QueryHandler> queryHandlerMap = new ConcurrentHashMap<>();

    @Autowired
    private ObjectProvider<QueryHandler> queryHandlerObjectProvider;

    public ApplicationQueryBus(ObjectProvider<QueryHandler> queryHandlerObjectProvider) {
        this.queryHandlerObjectProvider = queryHandlerObjectProvider;
    }

    @Override
    public <R> R dispatch(BaseQuery<R> query) throws Exception {
        String queryType = query.getType();
        QueryHandler handler = queryHandlerMap.get(queryType);

        if (handler == null) {
            List<QueryHandler> handlers = this.queryHandlerObjectProvider.stream()
                    .filter(it -> it.matches(query))
                    .collect(Collectors.toList());
            handler = handlers.get(0);
            this.queryHandlerMap.put(queryType, handler);
        }

        return (R) handler.handle(query);
    }
}
