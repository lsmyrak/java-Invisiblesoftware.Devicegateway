package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.DeviceType;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, UUID> {
}
