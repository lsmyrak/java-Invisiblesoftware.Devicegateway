package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.MqttPayloadOrder;

public class AccessibleDeviceWithRoomDto {
    private DeviceDto device;    

    public DeviceDto getDevice() {
        return device;
    }
    public void setDevice(DeviceDto device) {
        this.device = device;
    }
  
    public static AccessibleDeviceWithRoomDto convert(Device d) {
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
