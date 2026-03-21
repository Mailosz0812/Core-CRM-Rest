package org.mailosz.crmrest.helpers.mapperImpl;

import org.mailosz.crmrest.crmclient.ClientRequest;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.helpers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements Mapper<CrmClientEntity, ClientRequest> {
    private final ModelMapper mapper;

    public ClientMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ClientRequest mapFrom(CrmClientEntity crmClientEntity) {
        return this.mapper.map(crmClientEntity,ClientRequest.class);
    }

    @Override
    public CrmClientEntity mapTo(ClientRequest clientRequest) {
        return this.mapper.map(clientRequest, CrmClientEntity.class);
    }
}
