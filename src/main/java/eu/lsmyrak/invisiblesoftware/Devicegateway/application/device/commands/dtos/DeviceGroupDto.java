package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeviceGroupDto extends BaseDto{

    private List<UUID> deviceIds = new ArrayList<>();
    
    public List<UUID> getDeviceIds() {
        return deviceIds;
    }

    public void setDevices(List<UUID> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public DeviceGroupDto() {
    }
}
