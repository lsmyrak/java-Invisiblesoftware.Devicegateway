package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Command;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.dtos.DeviceGroupDto;

public class AddDeviceGroupCommand implements Command<Boolean>{
    
    private DeviceGroupDto deviceGroup;

    public AddDeviceGroupCommand(DeviceGroupDto deviceGroup) {
        this.deviceGroup = deviceGroup;
    }
    public DeviceGroupDto getDeviceGroup() {
        return deviceGroup;
    }
    public void setDeviceGroup(DeviceGroupDto deviceGroup) {
        this.deviceGroup = deviceGroup;
    }
}
