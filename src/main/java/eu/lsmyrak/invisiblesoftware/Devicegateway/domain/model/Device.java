package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Device extends DeviceBase {
    private String ipAddress;
    @OneToMany
    private List<MqttPayloadOrder> mqttPayloadOrders = new ArrayList<>();
    @OneToMany
    private List<DeviceGroup> deviceGroups = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "room_Id")
    private Room room;

    public Device() {

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<MqttPayloadOrder> getMqttPayloadOrders() {
        return mqttPayloadOrders;
    }

    public void setMqttPayloadOrders(List<MqttPayloadOrder> mqttPayloadOrders) {
        this.mqttPayloadOrders = mqttPayloadOrders;
    }

    public List<DeviceGroup> getDeviceGroups() {
        return deviceGroups;
    }

    public void setDeviceGroups(List<DeviceGroup> deviceGroups) {
        this.deviceGroups = deviceGroups;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
