package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.commands;

import java.time.LocalDateTime;
import java.util.HashSet;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceGroupRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceTypeRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.MqttPayloadOrderRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.RoomRepository;

public class EditDeviceCommandHandler implements CommandHandler<EditDeviceCommand, Void> {

    private final DeviceRepository deviceRepository;
    private final DeviceTypeRepository deviceTypeRepository;
    private final DeviceGroupRepository deviceGroupRepository;
    private final RoomRepository roomRepository;
    private final MqttPayloadOrderRepository mqttPayloadOrderRepository;

    public EditDeviceCommandHandler(DeviceRepository deviceRepository,
            DeviceTypeRepository deviceTypeRepository,
            DeviceGroupRepository deviceGroupRepository,
            RoomRepository roomRepository,
            MqttPayloadOrderRepository mqttPayloadOrderRepository) {

                this.deviceGroupRepository = deviceGroupRepository;
                this.roomRepository =  roomRepository;
                this.deviceRepository = deviceRepository;
                this.deviceTypeRepository = deviceTypeRepository;
                this.mqttPayloadOrderRepository =mqttPayloadOrderRepository;
    }

    @Override
    public Void handle(EditDeviceCommand command) {
        var deviceDto = command.getDevicedto();
        var room = roomRepository.findById(deviceDto.getRoomId()).get();
        var mqttPayloadOrders = mqttPayloadOrderRepository.findAllById(deviceDto.getMqttPayloadOrdersIds());
        var deviceGroups = deviceGroupRepository.findAllById(deviceDto.getDeviceGroupsIds());
        var deviceType = deviceTypeRepository.findById(deviceDto.getDeviceTypeId()).get();
        
        Device device = deviceRepository.findById(deviceDto.getId()).get();
        
        device.setDeviceType(deviceType);
        device.setManufacturer(deviceDto.getManufacturer());
        device.setModel(deviceDto.getModel());
        device.setSerialNumber(deviceDto.getSerialNumber());
        device.setFirmwareVersion(deviceDto.getFirmwareVersion());
        device.setMqttPayloadOrders(new HashSet<>(mqttPayloadOrders));
        device.setDeviceGroups(new HashSet<>(deviceGroups));
        device.setRoom(room);
        device.setName(deviceDto.getName());
        device.setDescription(deviceDto.getDescription());
        device.setUpdatedAt(LocalDateTime.now());        
        deviceRepository.save(device);
        return null;
    }

}
