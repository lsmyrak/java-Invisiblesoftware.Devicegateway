package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.services.MqttService;

@Component
public class ExecuteCommandHandler implements CommandHandler<ExecuteCommand,Void>{
    
    private final MqttService mqttService;

    public ExecuteCommandHandler(MqttService mqttService) {
        this.mqttService = mqttService;        
    }
    
    @Override
    public Void handle(ExecuteCommand command){
        //TO DO : 
        //dodać auth. 
        mqttService.sendAsync(command.getId(),"execution" );
        return null;
    }
}
