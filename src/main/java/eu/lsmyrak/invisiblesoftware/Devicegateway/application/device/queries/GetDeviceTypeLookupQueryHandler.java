package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceTypeRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupColumn;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameCodeRelatedDto;

@Component
public class GetDeviceTypeLookupQueryHandler {

    private final DeviceTypeRepository deviceTypeRepository;
    @Autowired
    public GetDeviceTypeLookupQueryHandler(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    public LookupResponse<NameCodeRelatedDto> handle(GetDeviceTypeLookupQuery query) {
        
        List<LookupColumn> columns = new ArrayList<>();

        columns.add(new LookupColumn("id", "ID", false));
        columns.add(new LookupColumn("name", "Name", true));
        columns.add(new LookupColumn("code", "Code", true));

        List<NameCodeRelatedDto> data = deviceTypeRepository.findAll().stream()
                .map(dt -> new NameCodeRelatedDto(dt.getId(), dt.getName(), dt.getCode()))
                .toList();
                
        return new LookupResponse<>(columns, data);
    }
}
