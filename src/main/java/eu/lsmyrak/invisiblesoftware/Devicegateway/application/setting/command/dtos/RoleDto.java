package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.dtos;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.dtos.BaseDto;

public class RoleDto extends BaseDto {
    public RoleDto(String name, String description) {        
        super.setName(name);
        super.setDescription(description);
    }

    public RoleDto() {
    }
}
