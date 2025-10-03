package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.UUID;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Query;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.DeviceDto;

public class GetDeviceQuery implements Query<DeviceDto> {
    public GetDeviceQuery(UUID id) {
        this.id=id;
    }

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }    
}
