package eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS;

public interface QueryHandler <Q extends Query<R>, R> {
    R handle(Q query);
}