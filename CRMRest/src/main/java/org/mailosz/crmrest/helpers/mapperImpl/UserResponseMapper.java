package org.mailosz.crmrest.helpers.mapperImpl;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.response.UserResponse;
import org.mailosz.crmrest.helpers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements Mapper<CrmUserEntity, UserResponse> {
    private final ModelMapper mapper;

    public UserResponseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserResponse mapFrom(CrmUserEntity entity) {
        return this.mapper.map(entity, UserResponse.class);
    }

    @Override
    public CrmUserEntity mapTo(UserResponse userResponse) {
        return this.mapper.map(userResponse, CrmUserEntity.class);
    }
}
