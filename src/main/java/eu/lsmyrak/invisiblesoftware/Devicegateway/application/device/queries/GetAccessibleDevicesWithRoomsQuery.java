package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.List;
import java.util.UUID;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Query;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.AccessibleDeviceWithRoomDto;

public class GetAccessibleDevicesWithRoomsQuery implements Query< List<AccessibleDeviceWithRoomDto>> {
    
    private UUID userId;

    public GetAccessibleDevicesWithRoomsQuery(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
