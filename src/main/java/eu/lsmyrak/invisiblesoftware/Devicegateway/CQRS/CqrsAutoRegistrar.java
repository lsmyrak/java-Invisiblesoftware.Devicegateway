package eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Map;
import org.springframework.core.ResolvableType;

@Component
public class CqrsAutoRegistrar {

    private final ApplicationContext context;
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CqrsAutoRegistrar(ApplicationContext context, CommandBus commandBus, QueryBus queryBus) {
        this.context = context;
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostConstruct
    public void registerHandlers() {
        Map<String, CommandHandler> commandHandlers = context.getBeansOfType(CommandHandler.class);
        for (CommandHandler<?, ?> handler : commandHandlers.values()) {
            Class<?> commandType = resolveGenericType(handler, CommandHandler.class);
            commandBus.registerHandler((Class) commandType, handler);
        }

        Map<String, QueryHandler> queryHandlers = context.getBeansOfType(QueryHandler.class);
        for (QueryHandler<?, ?> handler : queryHandlers.values()) {
            Class<?> queryType = resolveGenericType(handler, QueryHandler.class);
            queryBus.registerHandler((Class) queryType, handler);
        }
    }

    private Class<?> resolveGenericType(Object handler, Class<?> expectedInterface) {
        ResolvableType resolvableType = ResolvableType.forClass(handler.getClass())
                .as(expectedInterface);
        Class<?> generic = resolvableType.getGeneric(0).resolve();
        if (generic == null) {
            throw new IllegalStateException("Nie udało się znaleźć generyka dla " + handler.getClass());
        }
        return generic;
    }
}
