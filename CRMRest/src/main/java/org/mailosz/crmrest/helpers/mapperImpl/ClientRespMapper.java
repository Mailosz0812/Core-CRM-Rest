package org.mailosz.crmrest.helpers.mapperImpl;

import org.mailosz.crmrest.crmclient.response.ClientResponse;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.helpers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientRespMapper implements Mapper<CrmClientEntity, ClientResponse> {
    private final ModelMapper mapper;

    public ClientRespMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ClientResponse mapFrom(CrmClientEntity crmClientEntity) {
        return this.mapper.map(crmClientEntity, ClientResponse.class);
    }

    @Override
    public CrmClientEntity mapTo(ClientResponse clientResponse) {
        return this.mapper.map(clientResponse, CrmClientEntity.class);
    }
}
