package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeviceDto extends BaseDto {
    private UUID id;
    private String ipAddress;
    private List<UUID> mqttPayloadOrdersIds = new ArrayList<>();
    private List<UUID> deviceGroupsIds = new ArrayList<>();
    private UUID roomId;
    private UUID deviceTypeId;
    private String manufacturer;
    private String model;
    private String firmwareVersion;
    private LocalDateTime lastSeen;
    private String serialNumber;

     


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<UUID> getMqttPayloadOrdersIds() {
        return mqttPayloadOrdersIds;
    }

    public void setMqttPayloadOrdersIds(List<UUID> mqttPayloadOrdersIds) {
        this.mqttPayloadOrdersIds = mqttPayloadOrdersIds;
    }

    public List<UUID> getDeviceGroupsIds() {
        return deviceGroupsIds;
    }

    public void setDeviceGroupsIds(List<UUID> deviceGroupsIds) {
        this.deviceGroupsIds = deviceGroupsIds;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public UUID getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(UUID deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
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
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

}
