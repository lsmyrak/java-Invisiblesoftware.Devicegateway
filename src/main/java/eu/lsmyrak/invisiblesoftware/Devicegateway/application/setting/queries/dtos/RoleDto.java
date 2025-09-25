package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.dtos;

import java.util.UUID;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.BaseDto;

public class RoleDto extends BaseDto{
    public RoleDto(UUID id, String name, String description ) {       
        super.setId(id);
        super.setName(name);
        super.setDescription(description);
    }
    public RoleDto() {
    }
}
