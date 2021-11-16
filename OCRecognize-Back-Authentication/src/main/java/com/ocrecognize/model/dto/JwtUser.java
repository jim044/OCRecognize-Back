package com.ocrecognize.model.dto;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private static final long serialVersionUID = 8183251485829397188L;
    private final Long id;
    private final String username;
    private final String password;
    private final String firstname;
    private final String lastname;
    private Collection<? extends GrantedAuthority> roles;

    public JwtUser(Long id, String username, String password, String firstname, String lastname, List<GrantedAuthority> roles){
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
