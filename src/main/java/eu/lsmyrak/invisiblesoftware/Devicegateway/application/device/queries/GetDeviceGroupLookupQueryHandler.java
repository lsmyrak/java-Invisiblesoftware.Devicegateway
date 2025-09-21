package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceGroupRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupColumn;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameRelatedDto;

public class GetDeviceGroupLookupQueryHandler {
    private final DeviceGroupRepository deviceGroupRepository;

    @Autowired
    public GetDeviceGroupLookupQueryHandler(DeviceGroupRepository deviceGroupRepository) {
        this.deviceGroupRepository = deviceGroupRepository;
    }

    public LookupResponse<NameRelatedDto> handle(GetDeviceGroupLookupQuery query) {
        List<LookupColumn> columns = new ArrayList<>();
        columns.add(new LookupColumn("id", "ID", false));
        columns.add(new LookupColumn("name", "Name", true));

        List<NameRelatedDto> data = deviceGroupRepository.findAll().stream()
                .map(dt -> new NameRelatedDto(dt.getId(), dt.getName()))
                .toList();

        return new LookupResponse<>(columns, data);
    }
}
