package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.DeviceType;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceTypeRepository;

@Component
public class AddDeviceTypeCommandHandler implements CommandHandler<AddDeviceTypeCommand,Boolean> {

    private final DeviceTypeRepository deviceTypeRepository;
    
    @Autowired
    public AddDeviceTypeCommandHandler(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;        
    }
    @Override
    public Boolean handle(AddDeviceTypeCommand command) {
        var dto = command.getDeviceType();
        DeviceType newDeviceType = new DeviceType();
        newDeviceType.setCategory(dto.getCategory());
        // do przerobienia na generowanie kodu
        newDeviceType.setCode(dto.getCode());

        newDeviceType.setName(dto.getName());        
        newDeviceType.setDescription(dto.getDescription());
        newDeviceType.setCreateByFunction("AddDeviceTypeCommandHandler");
        newDeviceType.setEnabled(true); 
        newDeviceType.setDeleted(false);
        newDeviceType.setVersion(1);
        deviceTypeRepository.save(newDeviceType);
        return true;
    }
}
