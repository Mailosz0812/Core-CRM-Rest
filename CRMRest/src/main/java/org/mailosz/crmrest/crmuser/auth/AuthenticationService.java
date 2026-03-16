package org.mailosz.crmrest.crmuser.auth;

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

    @Value("${jwt.expiresIn}")
    private Duration expiresIn;

    public AuthenticationService(AuthenticationManager manager, CrmUserDetailsService detailsService, JWTUtils utils) {
        this.manager = manager;
        this.detailsService = detailsService;
        this.utils = utils;
    }

    public LoginResponse login(LoginRequest req){
        manager.authenticate(new UsernamePasswordAuthenticationToken(req.getMail(),req.getPassword()));
        CrmUserDetails userDetails = detailsService.loadUserByUsername(req.getMail());
        String token = this.utils.generateToken(userDetails);
        return new LoginResponse(token,expiresIn.toSeconds());
    }

}
