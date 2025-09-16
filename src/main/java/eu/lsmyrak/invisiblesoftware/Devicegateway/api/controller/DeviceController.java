package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.*;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.AccessibleDeviceWithRoomDto;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

        private final GetAccessibleDevicesWithRoomsQueryHandler getAccessibleDevicesWithRoomsQueryHandler;
        @Autowired
        public DeviceController(GetAccessibleDevicesWithRoomsQueryHandler getAccessibleDevicesWithRoomsQueryHandler) {
                this.getAccessibleDevicesWithRoomsQueryHandler = getAccessibleDevicesWithRoomsQueryHandler;
               
        }

        @GetMapping("/device-with-rooms")
        public List<AccessibleDeviceWithRoomDto> getDevices() {
                UUID userId = UUID.randomUUID(); 
                
                return getAccessibleDevicesWithRoomsQueryHandler.handle(new GetAccessibleDevicesWithRoomsQuery(userId));
        }
}
