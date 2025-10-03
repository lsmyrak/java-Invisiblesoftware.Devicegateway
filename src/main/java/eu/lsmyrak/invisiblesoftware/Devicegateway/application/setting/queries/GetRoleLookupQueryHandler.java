package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.QueryHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.RoleRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupColumn;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameRelatedDto;

@Component
public class GetRoleLookupQueryHandler implements QueryHandler<GetRoleLookupQuery,LookupResponse<NameRelatedDto>>{
    private final RoleRepository roleRepository;
    
    public GetRoleLookupQueryHandler(RoleRepository  roleRepository) {
        this.roleRepository = roleRepository;
    }

    public LookupResponse<NameRelatedDto> handle(GetRoleLookupQuery query){
        
        List<LookupColumn> column = new ArrayList<>();

        column.add(new LookupColumn("Id","ID", false));
        column.add(new LookupColumn("name","Name", true));

        var data = roleRepository.findAll().stream()
        .map(r-> new NameRelatedDto(r.getId(),r.getName())).toList();        
        return new LookupResponse<NameRelatedDto>(column,data);
    }
}
