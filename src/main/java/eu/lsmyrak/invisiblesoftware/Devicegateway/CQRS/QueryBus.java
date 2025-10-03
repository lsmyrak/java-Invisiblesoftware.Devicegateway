package eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class QueryBus {
    private final Map<Class<?>, QueryHandler<?, ?>> handlers = new HashMap<>();

    public <C extends Query<R>, R> void registerHandler(Class<C> type, QueryHandler<C, R> handler) {
        handlers.put(type, handler);
    }

    @SuppressWarnings("unchecked")
    public <C extends Query<R>, R> R execute(C query) {
        QueryHandler<C, R> handler = (QueryHandler<C, R>) handlers.get(query.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler for " + query.getClass());
        }
        return handler.handle(query);
    }
}
