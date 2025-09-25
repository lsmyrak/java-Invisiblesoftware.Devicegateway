package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import java.util.UUID;

import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.services.MqttService;

@Component
public class ExecuteCommandHandler {
    
    private final MqttService mqttService;

    public ExecuteCommandHandler(MqttService mqttService) {
        this.mqttService = mqttService;        
    }

    public void handle(UUID command){
        mqttService.sendAsync(command,"execution" );
    }
}
