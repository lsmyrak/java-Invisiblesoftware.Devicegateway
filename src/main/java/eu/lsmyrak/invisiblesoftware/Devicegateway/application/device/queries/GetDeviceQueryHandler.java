package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.QueryHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos.*;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.DeviceRepository;

public class GetDeviceQueryHandler implements QueryHandler<GetDeviceQuery,DeviceDto> {

    private final DeviceRepository  deviceRepository;
    public GetDeviceQueryHandler(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;        
    }
    @Override
    public DeviceDto handle(GetDeviceQuery query) {
        
        var optionalDevice = deviceRepository.findById(query.getId());
        var device =  optionalDevice.get();
        
        var dto = DeviceDto.Convert(device);
        return dto;
    }

}
