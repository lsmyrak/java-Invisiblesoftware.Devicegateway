package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Device extends DeviceBase {
    private String ipAddress;
    
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MqttPayloadOrder> mqttPayloadOrders = new HashSet<>();

    @ManyToMany(mappedBy = "devices", fetch = FetchType.LAZY)
    private Set<DeviceGroup> deviceGroups = new HashSet<>();

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

    public Set<MqttPayloadOrder> getMqttPayloadOrders() {
        return mqttPayloadOrders;
    }

    public void setMqttPayloadOrders(Set<MqttPayloadOrder> mqttPayloadOrders) {
        this.mqttPayloadOrders = mqttPayloadOrders;
    }

    public Set<DeviceGroup> getDeviceGroups() {
        return deviceGroups;
    }

    public void setDeviceGroups(Set<DeviceGroup> deviceGroups) {
        this.deviceGroups = deviceGroups;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
