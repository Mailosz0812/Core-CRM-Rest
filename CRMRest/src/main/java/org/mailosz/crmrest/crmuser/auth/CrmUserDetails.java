package org.mailosz.crmrest.crmuser.auth;

import org.jspecify.annotations.Nullable;
import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


public class CrmUserDetails implements UserDetails {
    private final CrmUserEntity crmUserEntity;

    public CrmUserDetails(CrmUserEntity crmUserEntity) {
        this.crmUserEntity = crmUserEntity;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+crmUserEntity.getRole().getName().toUpperCase()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.crmUserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.crmUserEntity.getMail();
    }
    public UUID getUserId(){
        return this.crmUserEntity.getId();
    }
}
