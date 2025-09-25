package eu.lsmyrak.invisiblesoftware.Devicegateway.services;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.MqttPayloadOrder;

public interface MqttService {    
    CompletableFuture<Boolean> sendAsync(MqttPayloadOrder mqttPayloadOrder, String eventName);    
    CompletableFuture<Boolean> sendAsync(UUID payloadId, String eventName);
    <T> T sendRequestAsync(String request, String response, Duration timeout, String payload);

}
