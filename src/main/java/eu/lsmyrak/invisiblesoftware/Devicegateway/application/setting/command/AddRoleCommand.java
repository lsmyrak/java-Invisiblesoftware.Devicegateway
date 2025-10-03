package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Command;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.dtos.RoleDto;

public class AddRoleCommand implements Command<Boolean>{
    private RoleDto roleDto;

    public AddRoleCommand(RoleDto roleDto) {
        this.roleDto = roleDto;
    }
    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }
    public RoleDto getRoleDto() {
        return roleDto;
    }
}
