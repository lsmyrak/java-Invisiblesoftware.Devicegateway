package eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS;

public interface CommandHandler<C extends Command<R>, R> {
    R handle(C command);
}
