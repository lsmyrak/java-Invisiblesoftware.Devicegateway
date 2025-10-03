package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;

public class AddDeviceCommandHandler implements CommandHandler<AddDeviceCommand,Void>{

    @Override
    public Void handle(AddDeviceCommand command) {
        var device  = new Device();
        

        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

}
