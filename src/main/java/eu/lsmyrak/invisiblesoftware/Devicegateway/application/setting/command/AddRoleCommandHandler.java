package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command;

import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Role;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.RoleRepository;

@Component
public class AddRoleCommandHandler implements CommandHandler<AddRoleCommand,Boolean>{
    private final RoleRepository roleRepository;
    
    public AddRoleCommandHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Boolean handle(AddRoleCommand command) {
        var role = new Role();
        var roleDto = command.getRoleDto();        
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        roleRepository.save(role);
        return true;
    }

}
