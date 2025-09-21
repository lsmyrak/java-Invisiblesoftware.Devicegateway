package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.dtos.DeviceGroupDto;

public class AddDeviceGroupCommand {
    
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
