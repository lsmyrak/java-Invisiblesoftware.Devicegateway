package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries;

import java.util.UUID;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Query;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.dtos.RoleDto;

public class GetRolebyIdQuery implements Query<RoleDto>{
private UUID id;

    public GetRolebyIdQuery(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
