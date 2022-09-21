package com.ocrecognize.utils;

import com.ocrecognize.model.dto.JwtUser;
import com.ocrecognize.model.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    private JwtUserFactory(){

    }

    public static JwtUser create(UserDto userDto){
        List<GrantedAuthority> roles = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userDto.getRoleDto().getRoleName());
        roles.add(simpleGrantedAuthority);
        return new JwtUser(userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getFirstname(),
                userDto.getLastname(),
                roles);
    }
}
