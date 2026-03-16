package org.mailosz.crmrest.crmuser.auth;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CrmUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    public CrmUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public CrmUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CrmUserEntity user = this.userRepo.findCrmUserEntityByMail(username).orElseThrow( () -> new UsernameNotFoundException(username));
        return new CrmUserDetails(user);
    }
}
