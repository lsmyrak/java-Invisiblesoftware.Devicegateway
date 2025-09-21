package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries;

import java.util.UUID;

public class GetRolebyIdQuery {
private UUID id;

    public GetRolebyIdQuery(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
