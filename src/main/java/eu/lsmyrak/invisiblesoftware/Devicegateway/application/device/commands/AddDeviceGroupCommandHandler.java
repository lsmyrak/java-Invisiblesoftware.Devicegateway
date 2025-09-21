package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.DeviceGroup;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceGroupRepository;

@Component
public class AddDeviceGroupCommandHandler {

    @Autowired
    private final DeviceGroupRepository deviceGroupRepository;
    
    public AddDeviceGroupCommandHandler(DeviceGroupRepository deviceGroupRepository) {
        this.deviceGroupRepository = deviceGroupRepository;
    }

    public void handle(AddDeviceGroupCommand command) {
     
        var dto = command.getDeviceGroup();
        DeviceGroup deviceGroup = new DeviceGroup();
        deviceGroup.setName(dto.getName());
        deviceGroup.setCode(dto.getCode());
        deviceGroup.setDescription(dto.getDescription());
        
        if(dto.getDevices() != null) {
            deviceGroup.setDevicess(dto.getDevices());
        }
        deviceGroupRepository.save(deviceGroup);

    }
}
