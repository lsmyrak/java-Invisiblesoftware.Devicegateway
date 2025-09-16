package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MqttPayload extends BaseAggregate {
    private String topic;
    private String payload;
    private String commandName;
    private String displayCommandName;
    
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getDisplayCommandName() {
        return displayCommandName;
    }

    public void setDisplayCommandName(String displayCommandName) {
        this.displayCommandName = displayCommandName;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
