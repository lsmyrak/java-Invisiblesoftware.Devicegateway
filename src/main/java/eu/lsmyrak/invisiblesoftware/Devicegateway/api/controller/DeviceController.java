package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.AddDeviceGroupCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.AddDeviceGroupCommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.AddDeviceTypeCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.AddDeviceTypeCommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.ExecuteCommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.*;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.AccessibleDeviceWithRoomDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameCodeRelatedDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameRelatedDto;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

        private final GetAccessibleDevicesWithRoomsQueryHandler getAccessibleDevicesWithRoomsQueryHandler;
        private final AddDeviceTypeCommandHandler addDeviceTypeCommandHandler;
        private final GetDeviceTypeLookupQueryHandler getDeviceTypeQueryHandler;
        private final GetDeviceGroupLookupQueryHandler getDeviceGroupQueryHandler;
        private final GetRoomsLookupQueryHandler getyRoomsLookupQueryHandler;
        private final GetPayloadLookupQueryHandler getPayloadLookupQueryHandler;
        private final AddDeviceGroupCommandHandler addDeviceGroupCommandHandler;
        private final ExecuteCommandHandler executeCommandHandler;

        @Autowired
        public DeviceController(
                        GetAccessibleDevicesWithRoomsQueryHandler getAccessibleDevicesWithRoomsQueryHandler,
                        AddDeviceTypeCommandHandler addDeviceTypeCommandHandler,
                        GetDeviceTypeLookupQueryHandler getDeviceTypeQueryHandler,
                        GetDeviceGroupLookupQueryHandler getDeviceGroupQueryHandler,
                        GetRoomsLookupQueryHandler getyRoomsLookupQueryHandler,
                        GetPayloadLookupQueryHandler getPayloadLookupQueryHandler,
                        AddDeviceGroupCommandHandler addDeviceGroupCommandHandler,
                        ExecuteCommandHandler executeCommandHandler) {
                this.getAccessibleDevicesWithRoomsQueryHandler = getAccessibleDevicesWithRoomsQueryHandler;
                this.addDeviceTypeCommandHandler = addDeviceTypeCommandHandler;
                this.getDeviceTypeQueryHandler = getDeviceTypeQueryHandler;
                this.getDeviceGroupQueryHandler = getDeviceGroupQueryHandler;
                this.getyRoomsLookupQueryHandler = getyRoomsLookupQueryHandler;
                this.getPayloadLookupQueryHandler = getPayloadLookupQueryHandler;
                this.addDeviceGroupCommandHandler = addDeviceGroupCommandHandler;
                this.executeCommandHandler = executeCommandHandler;

        }

        @PostMapping("/add-device")
        public void addDevice() {

        }

        @PostMapping("add-device-type")
        public ResponseEntity<Void> addDeviceType(@RequestBody AddDeviceTypeCommand command) {
                addDeviceTypeCommandHandler.handle(command);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @PostMapping("add-device-group")
        public void addDeviceGroup(@RequestBody AddDeviceGroupCommand command) {
                addDeviceGroupCommandHandler.handle(null);
        }

        @GetMapping("/device-with-rooms")
        public List<AccessibleDeviceWithRoomDto> getDevices() {
                UUID userId = UUID.randomUUID();

                return getAccessibleDevicesWithRoomsQueryHandler.handle(new GetAccessibleDevicesWithRoomsQuery(userId));
        }

        @PostMapping("execute-command/{payloadId}")
        public void executeCommand(@PathVariable UUID payloadId) {
                executeCommandHandler.handle(payloadId);
        }

        @GetMapping("lookup-device-type")
        public LookupResponse<NameCodeRelatedDto> lookupDeviceType() {
                return getDeviceTypeQueryHandler.handle(new GetDeviceTypeLookupQuery());
        }

        @GetMapping("lookup-device-group")
        public LookupResponse<NameRelatedDto> lookupDeviceGroup() {
                return getDeviceGroupQueryHandler.handle(new GetDeviceGroupLookupQuery());
        }

        @GetMapping("lookup-room")
        public LookupResponse<NameRelatedDto> lookupRoom() {
                return getyRoomsLookupQueryHandler.handle(new GetRoomsLookupQuery());
        }

        @GetMapping("lookup-payload")
        public LookupResponse<NameCodeRelatedDto> lookupPayload() {
                return getPayloadLookupQueryHandler.handle(new GetPayloadLookupQuery());
        }
}
