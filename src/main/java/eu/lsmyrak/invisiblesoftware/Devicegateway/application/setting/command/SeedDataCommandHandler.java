package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.*;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.*;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.RegisterDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class SeedDataCommandHandler {

    private final DeviceTypeRepository deviceTypeRepository;
    private final PlaceRepository placeRepository;
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;
    private final MqttPayloadRepository mqttPayloadRepository;
    private final MqttPayloadOrderRepository mqttPayloadOrderRepository;
    private final AuthService authService;
    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public SeedDataCommandHandler(
            DeviceTypeRepository deviceTypeRepository,
            PlaceRepository placeRepository,
            RoomRepository roomRepository,
            DeviceRepository deviceRepository,
            MqttPayloadRepository mqttPayloadRepository,
            MqttPayloadOrderRepository mqttPayloadOrderRepository,
            AuthService authService,
            ApplicationUserRepository applicationUserRepository
    ) {
        this.deviceTypeRepository = deviceTypeRepository;
        this.placeRepository = placeRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
        this.mqttPayloadRepository = mqttPayloadRepository;
        this.mqttPayloadOrderRepository = mqttPayloadOrderRepository;
        this.authService = authService;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Transactional
    public void handle(SeedDataCommand command) {
        if (deviceTypeRepository.count() == 0) {
        
            RegisterDto registerUser = new RegisterDto();
            registerUser.setUsername("admin");
            registerUser.setPassword("AdmiN@123456");
            registerUser.setEmail("admin@admin");
            authService.register(registerUser);

            ApplicationUser user = applicationUserRepository.findByEmail("admin@admin").orElseThrow();

        
            DeviceType blindsType = new DeviceType();
            blindsType.setName("Blinds");
            blindsType.setCategory("ESP");

            DeviceType lightsType = new DeviceType();
            lightsType.setName("Lights");
            lightsType.setCategory("ESP");

            deviceTypeRepository.saveAll(List.of(blindsType, lightsType));

        
            Place place = new Place();
            place.setName("Control room");
            placeRepository.save(place);

            Room room = new Room();
            room.setName("Control room");
            room.setPlace(place);

            room.setApplicationUsers(Set.of(user));
            roomRepository.save(room);

            Device smallBlindsDevice = new Device();
            smallBlindsDevice.setName("Small Blinds");
            smallBlindsDevice.setDeviceType(blindsType);
            smallBlindsDevice.setRoom(room);
            smallBlindsDevice.setModel("ESP");
            smallBlindsDevice.setManufacturer("ESP");

            Device largeBlindsDevice = new Device();
            largeBlindsDevice.setName("Large Blinds");
            largeBlindsDevice.setDeviceType(blindsType);
            largeBlindsDevice.setRoom(room);
            largeBlindsDevice.setModel("ESP");
            largeBlindsDevice.setManufacturer("ESP");

            Device lightDevice = new Device();
            lightDevice.setName("Lights");
            lightDevice.setDeviceType(lightsType);
            lightDevice.setRoom(room);
            lightDevice.setModel("ESP");
            lightDevice.setManufacturer("ESP");

            deviceRepository.saveAll(List.of(smallBlindsDevice, largeBlindsDevice, lightDevice));

            List<MqttPayload> smallBlindsPayloads = List.of(
                    createPayload("SmallManualUp", "small_manual_up", "node00/blinds/manual/small_blind/command", "100", smallBlindsDevice),
                    createPayload("SmallManualDown", "small_manual_down", "node00/blinds/manual/small_blind/command", "-100", smallBlindsDevice),
                    createPayload("SmallOpen", "small_open", "node00/blinds/cover/small_blind/command", "open", smallBlindsDevice),
                    createPayload("SmallClose", "small_close", "node00/blinds/cover/small_blind/command", "close", smallBlindsDevice),
                    createPayload("SmallStop", "small_stop", "node00/blinds/cover/small_blind/command", "stop", smallBlindsDevice)
            );
            List<MqttPayload> largeBlindsPayloads = List.of(
                    createPayload("LargeManualUp", "large_manual_up", "blinds/manual/roleta_duza/command", "100", largeBlindsDevice),
                    createPayload("LargeManualDown", "large_manual_down", "blinds/manual/roleta_duza/command", "-100", largeBlindsDevice),
                    createPayload("LargeOpen", "large_open", "blinds/cover/roleta_duza/command", "OPEN", largeBlindsDevice),
                    createPayload("LargeClose", "large_close", "blinds/cover/roleta_duza/command", "CLOSE", largeBlindsDevice),
                    createPayload("LargeStop", "large_stop", "blinds/cover/roleta_duza/command", "STOP", largeBlindsDevice)
            );
            List<MqttPayload> lightPayloads = List.of(
                    createPayload("FirstOn", "light_1_on", "lights/first/on", "1", lightDevice),
                    createPayload("FirstOff", "light_1_off", "lights/first/off", "0", lightDevice),
                    createPayload("SecondOn", "light_2_on", "lights/second/on", "1", lightDevice),
                    createPayload("SecondOff", "light_2_off", "lights/second/off", "0", lightDevice),
                    createPayload("ThirdOn", "light_3_on", "lights/third/on", "1", lightDevice),
                    createPayload("ThirdOff", "light_3_off", "lights/third/off", "0", lightDevice)
            );

            mqttPayloadRepository.saveAll(smallBlindsPayloads);
            mqttPayloadRepository.saveAll(largeBlindsPayloads);
            mqttPayloadRepository.saveAll(lightPayloads);

            // 7. Dodaj payload orders
            addPayloadOrders(smallBlindsPayloads, smallBlindsDevice);
            addPayloadOrders(largeBlindsPayloads, largeBlindsDevice);
            addPayloadOrders(lightPayloads, lightDevice);
        }
    }

    private MqttPayload createPayload(String commandName, String displayName, String topic, String payload, Device device) {
        MqttPayload mqttPayload = new MqttPayload();
        mqttPayload.setCommandName(commandName);
        mqttPayload.setDisplayCommandName(displayName);
        mqttPayload.setTopic(topic);
        mqttPayload.setPayload(payload);
        mqttPayload.setDevice(device);
        return mqttPayload;
    }

    private void addPayloadOrders(List<MqttPayload> payloads, Device device) {
        int order = 0;
        for (MqttPayload payload : payloads) {
            MqttPayloadOrder orderEntity = new MqttPayloadOrder();
            orderEntity.setMqttPayload(payload);
            orderEntity.setDevice(device);
            orderEntity.setDisplayOrder(order++);
            mqttPayloadOrderRepository.save(orderEntity);
        }
    }
}