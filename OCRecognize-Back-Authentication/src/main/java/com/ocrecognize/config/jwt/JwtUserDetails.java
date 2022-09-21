package com.ocrecognize.config.jwt;

import com.ocrecognize.model.dto.UserDto;
import com.ocrecognize.service.impl.UserService;
import com.ocrecognize.utils.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetails implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.findByUsername(username);

        if(userDto == null){
            throw new UsernameNotFoundException(String.format("No user found"));
        }else{
            return JwtUserFactory.create(userDto);
        }
    }
}
