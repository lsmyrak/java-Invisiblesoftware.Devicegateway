package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.dtos.RoleDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.RoleRepository;


@Component
public class GetRoleByIdQueryHandler {
@Autowired
private final RoleRepository roleRepository;

    public GetRoleByIdQueryHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto handle(GetRolebyIdQuery query) {
        return roleRepository.findById(query.getId())
                .map(role -> new RoleDto(role.getId(), role.getName(), role.getDescription()))
                .orElse(null);

    }

}
