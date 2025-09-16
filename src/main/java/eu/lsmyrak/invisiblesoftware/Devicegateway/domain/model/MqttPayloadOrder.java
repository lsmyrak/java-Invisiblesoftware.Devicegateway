package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class MqttPayloadOrder extends BaseAggregate {
    @ManyToOne
    @JoinColumn(name = "mqtt_payload_id")
    private MqttPayload mqttPayload;
    
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
    private int displayOrder;

    public MqttPayloadOrder() {} 

    public MqttPayload getMqttPayload() {
        return mqttPayload;
    }

    public void setMqttPayload(MqttPayload mqttPayload) {
        this.mqttPayload = mqttPayload;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
