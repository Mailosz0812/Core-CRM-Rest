package org.mailosz.crmrest.helpers.mapperImpl;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.response.UserCreateResp;
import org.mailosz.crmrest.helpers.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class UserCreateRespMapper implements Mapper<CrmUserEntity, UserCreateResp> {
    private final ModelMapper mapper;

    public UserCreateRespMapper(ModelMapper mapper) {
        this.mapper = mapper;
        TypeMap<CrmUserEntity,UserCreateResp> crmEntityMap= this.mapper.createTypeMap(CrmUserEntity.class, UserCreateResp.class);
        crmEntityMap.addMapping((entity) -> entity.getId().toString(),UserCreateResp::setUserId);
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
