package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;

public class DeviceDto  extends BaseDto {
    
    private String ipAddress;
    private List<MqttPayloadOrderDto> mqttPayloadOrders = new ArrayList<>();
    private List<DeviceGroupDto> deviceGroups = new ArrayList<>();
    private RoomDto room;
    private DeviceTypeDto deviceType;
    private String manufacturer;
    private String model;
    private String firmwareVersion;
    private LocalDateTime lastSeen;

    public static DeviceDto Convert(Device device)
    {        
        var dto =  new DeviceDto();
        dto.setName(device.getName());
        dto.setCode(device.getCode());
        dto.setDescription(device.getDescription());
        dto.setDeviceGroups(
        device.getDeviceGroups().stream()
        .map(DeviceGroupDto::Convert) 
        .collect(Collectors.toList()));
        
        return dto;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public List<MqttPayloadOrderDto> getMqttPayloadOrders() {
        return mqttPayloadOrders;
    }
    public void setMqttPayloadOrders(List<MqttPayloadOrderDto> mqttPayloadOrders) {
        this.mqttPayloadOrders = mqttPayloadOrders;
    }
    public List<DeviceGroupDto> getDeviceGroups() {
        return deviceGroups;
    }
    public void setDeviceGroups(List<DeviceGroupDto> deviceGroups) {
        this.deviceGroups = deviceGroups;
    }
    public RoomDto getRoom() {
        return room;
    }
    public void setRoom(RoomDto room) {
        this.room = room;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getFirmwareVersion() {
        return firmwareVersion;
    }
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }
    public LocalDateTime getLastSeen() {
        return lastSeen;
    }
    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public DeviceTypeDto getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(DeviceTypeDto deviceType) {
        this.deviceType = deviceType;
    }


}
