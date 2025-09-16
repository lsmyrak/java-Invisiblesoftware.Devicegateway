package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos;

public class MqttPayloadDto extends BaseDto {
    private String topic;
    private String payload;
    private String commandName;
    private String displayCommandName;

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

}
