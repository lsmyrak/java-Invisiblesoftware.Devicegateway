package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command;

import java.util.HashSet;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.ApplicationUserRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.RoleRepository;

public class EditUserCommandHandler implements CommandHandler<EditUserCommand, Void> {

    private final ApplicationUserRepository applicationUserRepository;
    private final RoleRepository roleRepository;

    public EditUserCommandHandler(ApplicationUserRepository applicationUserRepository, RoleRepository roleRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.roleRepository = roleRepository;
    }

@Override
public Void handle(EditUserCommand command) {
    var roles = roleRepository.findAllById(command.getRoleids());
    var user = applicationUserRepository.findById(command.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

    user.setRoles(new HashSet<>(roles));
    applicationUserRepository.save(user);
    return null;
}

}
