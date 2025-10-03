package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandBus;
import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.QueryBus;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.AddDeviceCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.AddDeviceGroupCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.AddDeviceTypeCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.EditDeviceCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.ExecuteCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.DeviceDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.*;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.AccessibleDeviceWithRoomDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameCodeRelatedDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameRelatedDto;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("devices")
public class DeviceController {

        private final CommandBus commandBus;
        private final QueryBus queryBus;

        @Autowired
        public DeviceController(CommandBus commandBus,QueryBus queryBus) {
                this.commandBus = commandBus;
                this.queryBus = queryBus;
        }

        @PostMapping("add-device")
        public void addDevice(@RequestBody AddDeviceCommand command) {
        commandBus.execute(command);
        }

        @GetMapping("{id}")
        public DeviceDto getDevice(@PathVariable UUID id){               
              return queryBus.execute(new GetDeviceQuery(id));
        }

        @PutMapping("edit-device")
        public void editDEvice(@RequestBody EditDeviceCommand command)
        {
           commandBus.execute(command);
        }

        @PostMapping("add-device-type")
        public ResponseEntity<Void> addDeviceType(@RequestBody AddDeviceTypeCommand command) {
                commandBus.execute(command);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @PostMapping("add-device-group")
        public void addDeviceGroup(@RequestBody AddDeviceGroupCommand command) {
                commandBus.execute(command);
        }

        @GetMapping("device-with-rooms")
        public List<AccessibleDeviceWithRoomDto> getDevices() {
                UUID userId = UUID.randomUUID();
                return queryBus.execute(new GetAccessibleDevicesWithRoomsQuery(userId));
        }

        @PostMapping("execute-command/{payloadId}")
        public void executeCommand(@PathVariable UUID payloadId) {
                commandBus.execute(new ExecuteCommand(payloadId));
        }

        @GetMapping("lookup-device-type")
        public LookupResponse<NameCodeRelatedDto> lookupDeviceType() {
                return queryBus.execute(new GetDeviceTypeLookupQuery());
        }

        @GetMapping("lookup-device-group")
        public LookupResponse<NameRelatedDto> lookupDeviceGroup() {
                return queryBus.execute(new GetDeviceGroupLookupQuery());
        }

        @GetMapping("lookup-room")
        public LookupResponse<NameRelatedDto> lookupRoom() {
                return queryBus.execute(new GetRoomsLookupQuery());
        }

        @GetMapping("lookup-payload")
        public LookupResponse<NameCodeRelatedDto> lookupPayload() {
                return queryBus.execute(new GetPayloadLookupQuery());
        }
}
