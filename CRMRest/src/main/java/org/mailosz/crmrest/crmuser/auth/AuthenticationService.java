package org.mailosz.crmrest.crmuser.auth;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.response.UserResponse;
import org.mailosz.crmrest.helpers.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class AuthenticationService {
    private final AuthenticationManager manager;
    private final CrmUserDetailsService detailsService;
    private final JWTUtils utils;
    private final Mapper<CrmUserEntity, UserResponse> userMapper;

    @Value("${jwt.expiresIn}")
    private Duration expiresIn;

    public AuthenticationService(AuthenticationManager manager, CrmUserDetailsService detailsService,
                                 JWTUtils utils, Mapper<CrmUserEntity, UserResponse> userMapper) {
        this.manager = manager;
        this.detailsService = detailsService;
        this.utils = utils;
        this.userMapper = userMapper;
    }

    public LoginResponse login(LoginRequest req){
        manager.authenticate(new UsernamePasswordAuthenticationToken(req.getMail(),req.getPassword()));
        CrmUserDetails userDetails = detailsService.loadUserByUsername(req.getMail());
        CrmUserEntity userEntity = userDetails.getCrmUserEntity();
        String token = this.utils.generateToken(userDetails);
        return new LoginResponse(token,expiresIn.toMillis(),this.userMapper.mapFrom(userEntity));
    }

}
