package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.UUID;

public class GetAccessibleDevicesWithRoomsQuery {
    
    private UUID userId;

    public GetAccessibleDevicesWithRoomsQuery(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
