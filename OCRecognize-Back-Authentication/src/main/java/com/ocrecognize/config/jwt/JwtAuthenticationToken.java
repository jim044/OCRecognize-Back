package com.ocrecognize.config.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {


    private static final long serialVersionUID = 1624609824160078691L;
    private final transient Object principal;

    public JwtAuthenticationToken(Object principal){
        super(null);
        this.principal=principal;
    }

    public JwtAuthenticationToken(Object principal, Object details, Collection<? extends GrantedAuthority> roles){
        super(roles);
        this.principal=principal;
        super.setDetails(details);
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
