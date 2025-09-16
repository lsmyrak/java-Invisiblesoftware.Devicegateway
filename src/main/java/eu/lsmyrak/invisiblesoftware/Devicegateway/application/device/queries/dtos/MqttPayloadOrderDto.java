package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos;

public class MqttPayloadOrderDto {

    private MqttPayloadDto mqttPayload;
    private int displayOrder;

    public MqttPayloadDto getMqttPayload() {
        return mqttPayload;
    }

    public void setMqttPayload(MqttPayloadDto mqttPayload) {
        this.mqttPayload = mqttPayload;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

}
