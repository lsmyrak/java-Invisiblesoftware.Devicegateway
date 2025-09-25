package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.MqttPayloadRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupColumn;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameCodeRelatedDto;

@Component
public class GetPayloadLookupQueryHandler {
@Autowired
private final MqttPayloadRepository  mqttPayloadRepository;
    public GetPayloadLookupQueryHandler(MqttPayloadRepository mqttPayloadRepository) {
        this.mqttPayloadRepository = mqttPayloadRepository;
    }

    public LookupResponse<NameCodeRelatedDto> handle(GetPayloadLookupQuery query) {
        
        List<LookupColumn> columns = new ArrayList<>();

        columns.add(new LookupColumn("id", "ID", false));
        columns.add(new LookupColumn("name", "Name", true));
        columns.add(new LookupColumn("code", "Code", true));

        List<NameCodeRelatedDto> data = mqttPayloadRepository.findAll().stream()
                .map(p -> new NameCodeRelatedDto(p.getId(), p.getCommandName(), p.getDisplayCommandName()))
                .toList();
                
        return new LookupResponse<>(columns, data);
    }
}
