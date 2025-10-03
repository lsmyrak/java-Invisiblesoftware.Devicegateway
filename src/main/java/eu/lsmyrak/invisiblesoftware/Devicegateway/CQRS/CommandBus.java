package eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CommandBus {
    private final Map<Class<?>, CommandHandler<?, ?>> handlers = new HashMap<>();

    public <C extends Command<R>, R> void registerHandler(Class<C> type, CommandHandler<C, R> handler) {
        handlers.put(type, handler);
    }

    @SuppressWarnings("unchecked")
    public <C extends Command<R>, R> R execute(C command) {
        CommandHandler<C, R> handler = (CommandHandler<C, R>) handlers.get(command.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler for " + command.getClass());
        }
        return handler.handle(command);
    }
}
