package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @GetMapping("/list")
        public ResponseEntity<String> getDevices() {
                return ResponseEntity.ok("List of devices");
        }
}
