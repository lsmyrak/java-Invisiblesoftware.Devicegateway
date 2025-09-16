package eu.lsmyrak.invisiblesoftware.Devicegateway.services;

import java.util.List;
import java.util.UUID;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;


public interface DeviceService {
    Device getDeviceById(UUID id);
    List<Device> getAllDevices();
    List<Device> getAccessibleDevicesWithRooms(UUID userId);
    void DeleteDevice(UUID id);
    Device createDevice(Device device);
    Device updateDevice(Device device);
}
