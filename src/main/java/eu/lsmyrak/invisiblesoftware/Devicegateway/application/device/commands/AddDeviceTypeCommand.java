package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Command;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.dtos.DeviceTypeDto;

public class AddDeviceTypeCommand implements Command<Boolean>{
    
    private DeviceTypeDto deviceType;

    public AddDeviceTypeCommand() {    
    }
    public DeviceTypeDto getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(DeviceTypeDto deviceType) {
        this.deviceType = deviceType;
    }
}
