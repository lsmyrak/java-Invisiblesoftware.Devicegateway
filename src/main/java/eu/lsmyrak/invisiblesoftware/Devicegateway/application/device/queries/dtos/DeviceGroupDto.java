package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos;

import java.util.ArrayList;
import java.util.List;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;

public class DeviceGroupDto extends BaseDto {

    private List<Device> deviceTypes  = new ArrayList<>();
        
    public DeviceGroupDto() {        
    }
        
    public List<Device> getDeviceTypes() {
        return deviceTypes;
    }
    public void setDeviceTypes(List<Device> deviceTypes) {
        this.deviceTypes = deviceTypes;
    }
}
