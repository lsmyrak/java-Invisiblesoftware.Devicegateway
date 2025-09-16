package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CommandHistory extends BaseAggregate {

    private String eventName;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
    @ManyToOne
    @JoinColumn(name = "mqtt_payload_order_id")
    private MqttPayloadOrder mqttPayloadOrder;

    private LocalDateTime commandTime;

    public CommandHistory() {        
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public MqttPayloadOrder getMqttPayloadOrder() {
        return mqttPayloadOrder;
    }

    public void setMqttPayloadOrder(MqttPayloadOrder mqttPayloadOrder) {
        this.mqttPayloadOrder = mqttPayloadOrder;
    }

    public LocalDateTime getCommandTime() {
        return commandTime;
    }

    public void setCommandTime(LocalDateTime commandTime) {
        commandTime = LocalDateTime.now();
    }

}
