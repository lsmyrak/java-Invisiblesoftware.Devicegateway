package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.QueryHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.DeviceGroupDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.DeviceTypeDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.AccessibleDeviceWithRoomDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.DeviceDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.MqttPayloadDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.MqttPayloadOrderDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.RoomDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.MqttPayloadOrder;
import eu.lsmyrak.invisiblesoftware.Devicegateway.services.DeviceService;


@Component
public class GetAccessibleDevicesWithRoomsQueryHandler implements QueryHandler<GetAccessibleDevicesWithRoomsQuery,List<AccessibleDeviceWithRoomDto>> {

    private final DeviceService deviceService;
    
    @Autowired
    public GetAccessibleDevicesWithRoomsQueryHandler(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public List<AccessibleDeviceWithRoomDto> handle(GetAccessibleDevicesWithRoomsQuery query) {
        UUID userId = query.getUserId();        
        var devices = deviceService.getAccessibleDevicesWithRooms(userId);        

         List<AccessibleDeviceWithRoomDto> dtos = devices.stream()
        .map(GetAccessibleDevicesWithRoomsQueryHandler::Convert)
        .collect(Collectors.toList());
        
        
        return dtos;
    }

    private static AccessibleDeviceWithRoomDto Convert(Device d){
        DeviceDto dto = new DeviceDto();
        dto.setId(d.getId());
        dto.setIpAddress(d.getIpAddress());
        dto.setName(d.getName());
        dto.setDescription(d.getDescription());
        dto.setFirmwareVersion(d.getFirmwareVersion());
        dto.setLastSeen(d.getLastSeen());
        dto.setManufacturer(d.getManufacturer());
        dto.setModel(d.getModel());        
        dto.setCreatedAt(d.getCreatedAt());
        dto.setUpdatedAt(d.getUpdatedAt());

        if (d.getRoom() != null) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(d.getRoom().getId());
            roomDto.setName(d.getRoom().getName());
            roomDto.setDescription(d.getRoom().getDescription());
            roomDto.setCreatedAt(d.getRoom().getCreatedAt());
            roomDto.setUpdatedAt(d.getRoom().getUpdatedAt());
            dto.setRoom(roomDto);
        }

        if (d.getDeviceType() != null) {
            DeviceTypeDto typeDto = new DeviceTypeDto();
            typeDto.setId(d.getDeviceType().getId());
            typeDto.setName(d.getDeviceType().getName());
            typeDto.setDescription(d.getDeviceType().getDescription());
            typeDto.setCategory(d.getDeviceType().getCategory());
            typeDto.setCreatedAt(d.getDeviceType().getCreatedAt());
            typeDto.setUpdatedAt(d.getDeviceType().getUpdatedAt());
            dto.setDeviceType(typeDto);
        }

        if (d.getDeviceGroups() != null) {
            List<DeviceGroupDto> groupDtos = d.getDeviceGroups().stream().map(g -> {
                DeviceGroupDto groupDto = new DeviceGroupDto();
                groupDto.setId(g.getId());
                groupDto.setName(g.getName());
                groupDto.setDescription(g.getDescription());
                groupDto.setCreatedAt(g.getCreatedAt());
                groupDto.setUpdatedAt(g.getUpdatedAt());
                return groupDto;
            }).collect(Collectors.toList());
            dto.setDeviceGroups(groupDtos);
        }

        if (d.getMqttPayloadOrders() != null) {
            List<MqttPayloadOrderDto> orderDtos = d.getMqttPayloadOrders().stream()
                .sorted(Comparator.comparingInt(MqttPayloadOrder::getDisplayOrder))
                .map(m -> {
                    MqttPayloadDto payloadDto = new MqttPayloadDto();
                    payloadDto.setId(m.getMqttPayload().getId());
                    payloadDto.setCommandName(m.getMqttPayload().getCommandName());
                    payloadDto.setDisplayCommandName(m.getMqttPayload().getDisplayCommandName());
                    payloadDto.setTopic(m.getMqttPayload().getTopic());
                    payloadDto.setPayload(m.getMqttPayload().getPayload());
                    payloadDto.setName(m.getMqttPayload().getName());
                    payloadDto.setDescription(m.getMqttPayload().getDescription());
                    payloadDto.setCreatedAt(m.getMqttPayload().getCreatedAt());
                    payloadDto.setUpdatedAt(m.getMqttPayload().getUpdatedAt());

                    MqttPayloadOrderDto orderDto = new MqttPayloadOrderDto();
                    orderDto.setMqttPayload(payloadDto);
                    orderDto.setDisplayOrder(m.getDisplayOrder());
                    return orderDto;
                }).collect(Collectors.toList());
            dto.setMqttPayloadOrders(orderDtos);
        }

        AccessibleDeviceWithRoomDto result = new AccessibleDeviceWithRoomDto();
        result.setDevice(dto);
        return result;
        
    }
}
