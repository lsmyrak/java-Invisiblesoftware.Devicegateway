package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Query;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameCodeRelatedDto;

public class GetPayloadLookupQuery implements Query<LookupResponse<NameCodeRelatedDto>>{

}
