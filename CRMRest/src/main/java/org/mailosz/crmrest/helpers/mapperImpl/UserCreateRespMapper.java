package org.mailosz.crmrest.helpers.mapperImpl;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.response.UserCreateResp;
import org.mailosz.crmrest.helpers.Mapper;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserCreateRespMapper implements Mapper<CrmUserEntity, UserCreateResp> {
    private final ModelMapper mapper;

    public UserCreateRespMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserCreateResp mapFrom(CrmUserEntity crmUserEntity) {
        return this.mapper.map(crmUserEntity, UserCreateResp.class);
    }

    @Override
    public CrmUserEntity mapTo(UserCreateResp userCreateResp) {
        return this.mapper.map(userCreateResp, CrmUserEntity.class);
    }
}
