package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import java.util.ArrayList;
import java.util.List;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.RoomRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupColumn;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameRelatedDto;

public class GetRoomsLookupQueryHandler {

    private final RoomRepository roomRepository;
    public GetRoomsLookupQueryHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public LookupResponse<NameRelatedDto> handle(GetRoomsLookupQuery query) {
        
        List<LookupColumn> columns = new ArrayList<>();

        columns.add(new LookupColumn("id", "ID", false));
        columns.add(new LookupColumn("name", "Name", true));

        List<NameRelatedDto> data = roomRepository.findAll().stream()
                .map(r -> new NameRelatedDto(r.getId(), r.getName()))
                .toList();
                
        return new LookupResponse<>(columns, data);
    }

}
