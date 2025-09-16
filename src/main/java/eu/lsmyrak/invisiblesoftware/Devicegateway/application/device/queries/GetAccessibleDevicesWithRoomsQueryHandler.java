package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.AccessibleDeviceWithRoomDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.services.DeviceService;


@Component
public class GetAccessibleDevicesWithRoomsQueryHandler {

    private final DeviceService deviceService;
    
    @Autowired
    public GetAccessibleDevicesWithRoomsQueryHandler(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public List<AccessibleDeviceWithRoomDto> handle(GetAccessibleDevicesWithRoomsQuery query) {
        UUID userId = query.getUserId();
        
        var devices = deviceService.getAccessibleDevicesWithRooms(userId);
        
        List<AccessibleDeviceWithRoomDto> dtos = devices.stream()
        .map(AccessibleDeviceWithRoomDto::convert)
        .collect(Collectors.toList());
        
        return dtos;

    }
}
