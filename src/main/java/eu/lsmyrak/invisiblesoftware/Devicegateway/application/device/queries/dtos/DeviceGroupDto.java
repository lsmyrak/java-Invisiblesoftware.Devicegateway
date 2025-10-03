package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos;

import java.util.ArrayList;
import java.util.List;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.DeviceGroup;

public class DeviceGroupDto extends BaseDto {

    private List<Device> deviceTypes  = new ArrayList<>();
        
    public DeviceGroupDto() {        
    }
    
    public static DeviceGroupDto Convert(DeviceGroup deviceGroup){
        var dto = new DeviceGroupDto();
        dto.setName(deviceGroup.getName());
        dto.setCode(deviceGroup.getCode());
        dto.setDescription(deviceGroup.getDescription());

        return dto;
    }    
    public List<Device> getDeviceTypes() {
        return deviceTypes;
    }
    public void setDeviceTypes(List<Device> deviceTypes) {
        this.deviceTypes = deviceTypes;
    }
}
