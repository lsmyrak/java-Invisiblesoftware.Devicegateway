package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.MqttPayloadOrder;

public interface MqttPayloadOrderRepository  extends JpaRepository<MqttPayloadOrder, UUID> {

}
