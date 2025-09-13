package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @GetMapping("rooms-with-devices")
    public String getRoomsWithDevices() {
        return "rooms-with-devices";
    }

    @GetMapping("room/{id}/devices")
    public String getDevicesByRoom(UUID id) {
        return "room-devices";
    }

}
