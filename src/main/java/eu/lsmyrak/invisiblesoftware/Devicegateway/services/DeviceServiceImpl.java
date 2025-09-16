package eu.lsmyrak.invisiblesoftware.Devicegateway.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device getDeviceById(UUID id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public void DeleteDevice(UUID id) {
        Device existing = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found with ID: " + id));
        existing.setDeleted(true);
        deviceRepository.save(existing);
    }

    @Override
    public Device createDevice(Device device) {
        device.setCreatedAt(LocalDateTime.now());
        device.setUpdatedAt(LocalDateTime.now());
        device.setVersion(1);
        device.setDeleted(false);
        device.setEnabled(true);

        return deviceRepository.save(device);
    }

    
    @Override
    public Device updateDevice(Device device) {
        UUID id = device.getId();
        if (id == null) {
            throw new IllegalArgumentException("Device ID must not be null for update");
        }

        Device existing = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found with ID: " + id));

        existing.setIpAddress(device.getIpAddress());
        existing.setManufacturer(device.getManufacturer());
        existing.setModel(device.getModel());
        existing.setSerialNumber(device.getSerialNumber());
        existing.setFirmwareVersion(device.getFirmwareVersion());
        existing.setLastSeen(device.getLastSeen());
        existing.setRoom(device.getRoom());
        existing.setDeviceType(device.getDeviceType());
        existing.setName(device.getName());
        existing.setDescription(device.getDescription());
        existing.setUpdatedAt(LocalDateTime.now());

        return deviceRepository.save(existing);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Device> getAccessibleDevicesWithRooms(UUID userId) {
        List<Device> devices = deviceRepository.findAccessibleDevicesWithRooms(userId);
        return devices;
    }

}
