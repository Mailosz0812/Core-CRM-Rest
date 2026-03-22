package org.mailosz.crmrest.helpers.mapperImpl;

import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.crmclient.response.ClientWidgetResponse;
import org.mailosz.crmrest.helpers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientWidgetInfoMapper implements Mapper<CrmClientEntity, ClientWidgetResponse> {
    private final ModelMapper modelMapper;

    public ClientWidgetInfoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientWidgetResponse mapFrom(CrmClientEntity crmClientEntity) {
        return this.modelMapper.map(crmClientEntity,ClientWidgetResponse.class);
    }

    @Override
    public CrmClientEntity mapTo(ClientWidgetResponse clientWidgetResponse) {
        return this.modelMapper.map(clientWidgetResponse,CrmClientEntity.class);
    }
}
