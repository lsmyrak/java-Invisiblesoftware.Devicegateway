package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import java.util.stream.Collectors;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.DeviceGroup;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceGroupRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceRepository;

@Component
public class AddDeviceGroupCommandHandler implements CommandHandler<AddDeviceGroupCommand,Boolean>{

    @Autowired
    private final DeviceGroupRepository deviceGroupRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    public AddDeviceGroupCommandHandler(DeviceGroupRepository deviceGroupRepository,DeviceRepository deviceRepositroy) {
        this.deviceGroupRepository = deviceGroupRepository;
        this.deviceRepository = deviceRepositroy;
    }
    @Override
    public Boolean handle(AddDeviceGroupCommand command) {

        var dto = command.getDeviceGroup();
        DeviceGroup deviceGroup = new DeviceGroup();
        deviceGroup.setName(dto.getName());
        deviceGroup.setCode(dto.getCode());
        deviceGroup.setDescription(dto.getDescription());
        
        if (dto.getDeviceIds() != null) {
            Set<Device> devicesSet = deviceRepository.findAllById(dto.getDeviceIds()).stream()
                    .map(deviceDto -> {
                        Device device = new Device();
                        device.setId(deviceDto.getId());
                        device.setName(deviceDto.getName());
                        device.setCode(deviceDto.getCode());
                        device.setDescription(deviceDto.getDescription());
                        device.setDeviceType(deviceDto.getDeviceType());
                        device.setRoom(deviceDto.getRoom());

                        return device;
                    })
                    .collect(Collectors.toSet());

            deviceGroup.setDevicess(devicesSet);
        }
        deviceGroupRepository.save(deviceGroup);
    return true;
    }
}
