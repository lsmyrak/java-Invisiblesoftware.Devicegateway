package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.Device;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
@Query("""
    SELECT DISTINCT d FROM Device d
    JOIN FETCH d.room r
    JOIN FETCH r.applicationUsers u
    LEFT JOIN FETCH d.deviceType
    LEFT JOIN FETCH r.place
    LEFT JOIN FETCH d.deviceGroups
    LEFT JOIN FETCH d.mqttPayloadOrders mpo
    LEFT JOIN FETCH mpo.mqttPayload
    WHERE d.isDeleted = false AND d.isEnabled = true AND u.id = :userId
    """)
List<Device> findAccessibleDevicesWithRooms(@Param("userId") UUID userId);

}
