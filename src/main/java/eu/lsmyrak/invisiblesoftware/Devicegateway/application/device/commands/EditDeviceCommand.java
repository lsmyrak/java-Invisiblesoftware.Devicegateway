package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Command;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.dtos.DeviceDto;

public class EditDeviceCommand implements Command<Void>{
    private DeviceDto devicedto;

    public DeviceDto getDevicedto() {
        return devicedto;
    }

    public void setDevicedto(DeviceDto devicedto) {
        this.devicedto = devicedto;
    }

    
}
