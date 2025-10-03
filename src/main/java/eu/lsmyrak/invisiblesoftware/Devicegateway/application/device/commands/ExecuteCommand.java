package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import java.util.UUID;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Command;

public class ExecuteCommand implements Command<Void> {
    private UUID id;
    public ExecuteCommand(UUID id) {
        this.id=id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
}
