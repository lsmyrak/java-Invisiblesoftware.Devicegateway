package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.ApplicationUserRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupColumn;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameRelatedDto;

@Component
public class GetUserLookupQueryHandler {
    private final ApplicationUserRepository applicationUserRepository;

    public GetUserLookupQueryHandler(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public LookupResponse<NameRelatedDto> handle(GetUserLookupQuery query){
        List<LookupColumn> columns  = new ArrayList<>();
        columns.add(new LookupColumn("id","ID",false));
        columns.add(new LookupColumn("name","Name",true));

        var data = applicationUserRepository.findAll().stream()
        .map(r-> new NameRelatedDto(r.getId(),r.getName()))
        .toList();
        
        return new  LookupResponse<NameRelatedDto>(columns,data);
    }
}
