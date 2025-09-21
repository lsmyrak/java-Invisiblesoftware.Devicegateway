package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.dtos;

import java.util.ArrayList;
import java.util.List;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;

public class DeviceGroupDto extends BaseDto{

    private List<Device> devices = new ArrayList<>();
    
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public DeviceGroupDto() {
    }
}
