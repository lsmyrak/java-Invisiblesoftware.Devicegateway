package eu.lsmyrak.invisiblesoftware.Devicegateway.services;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.MqttGlobalPublishFilter;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.MqttPayloadOrder;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.MqttPayloadRepository;

@Service
public class MqttServiceImpl implements MqttService {

    private static final Logger logger = LoggerFactory.getLogger(MqttService.class);
    //private final ObjectMapper objectMapper = new ObjectMapper();
    private final Mqtt5AsyncClient client;

    private final MqttPayloadRepository mqttPayloadRepository;

    private final Map<String, CompletableFuture<String>> pendingRequests = new ConcurrentHashMap<>();
    private String brokerAddress = "192.168.253.50";
    private int port = 1883;
    private String userName = "dev29a";
    private String password = "Immolation@138";
    private String clientId = "DeviceGatewayClient";

    public MqttServiceImpl(MqttPayloadRepository mqttPayloadRepository) {
        this.mqttPayloadRepository = mqttPayloadRepository;

        this.client = MqttClient.builder()
                .useMqttVersion5()
                .identifier(clientId)
                .serverHost(brokerAddress)
                .serverPort(port)
                .automaticReconnectWithDefaultConfig()
                .buildAsync();

        client.toAsync().connectWith()
                .simpleAuth()
                .username(userName)
                .password(StandardCharsets.UTF_8.encode(password))
                .applySimpleAuth()
                .send()
                .thenAccept(connAck -> {
                    logger.info("connected to : MQTT broker {}:{}", brokerAddress, port);
                    subscribe("devices/response");
                })
                .exceptionally(ex -> {
                    logger.error("Failed to connect", ex);
                    return null;
                });
    }

    private void subscribe(String topic) {
        client.subscribeWith()
                .topicFilter(topic)
                .send()
                .thenAccept(subAck -> logger.info("Subscribed to topic {}", topic));
        client.publishes(MqttGlobalPublishFilter.ALL, this::handleIncomingMessage);
    }

    private void handleIncomingMessage(Mqtt5Publish publish) {
        String topic = publish.getTopic().toString();
        String payload = new String(publish.getPayloadAsBytes(), StandardCharsets.UTF_8);
        logger.info("Received message on topic {}: {}", topic, payload);

        CompletableFuture<String> future = pendingRequests.remove(topic);
        if (future != null) {
            future.complete(payload);
        }
    }

    @Override
    public CompletableFuture<Boolean> sendAsync(MqttPayloadOrder mqttPayloadOrder, String eventName) {
        var mqttPayload = mqttPayloadOrder.getMqttPayload();
        return client.publishWith()
                .topic(mqttPayload.getTopic())
                .payload(mqttPayload.getPayload().getBytes(StandardCharsets.UTF_8))
                .send()
                .handle((publishResult, ex) -> {
                    if (ex != null) {
                        logger.error("Failed to send MQTT message", ex);
                        return false;
                    } else {
                        logger.info("Sent MQTT message to {} with payload {}", mqttPayload.getTopic(), mqttPayload.getPayload());
                        return true;
                    }
                });
    }
    
    @Override
    public CompletableFuture<Boolean> sendAsync(UUID mqttPayloadId, String eventName) {
        var mqttPayloadOpt = mqttPayloadRepository.findById(mqttPayloadId);
        if (mqttPayloadOpt.isPresent()) {
            var mqttPayload = mqttPayloadOpt.get();
            var topic = mqttPayload.getTopic();
            var payload = mqttPayload.getPayload();

            return client.publishWith()
                    .topic(topic)
                    .payload(payload.getBytes(StandardCharsets.UTF_8))
                    .send()
                    .handle((publishResult, ex) -> {
                        if (ex != null) {
                            logger.error("Failed to send MQTT message", ex);
                            return false;
                        } else {
                            logger.info("Sent MQTT message to {} with payload {}", topic, payload);
                            return true;
                        }
                    });
        } else {
            logger.warn("MqttPayload not found for id: {}", mqttPayloadId);
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public <T> T sendRequestAsync(String request, String response, Duration timeout, String payload) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendRequestAsync'");
    }

}
