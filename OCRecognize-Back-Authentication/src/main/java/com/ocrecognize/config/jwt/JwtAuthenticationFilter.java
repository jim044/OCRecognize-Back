package com.ocrecognize.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocrecognize.model.dto.RoleDto;
import com.ocrecognize.service.impl.UserService;
import com.ocrecognize.utils.Constants;
import com.ocrecognize.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@AllArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager auth;

    private UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        JwtAuthenticationRequest aut ;

        try {
            aut = new ObjectMapper().readValue(request.getInputStream(), JwtAuthenticationRequest.class);
        } catch (IOException e) {
            log.error("Can't retrieve data from request");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return auth.authenticate(new UsernamePasswordAuthenticationToken(aut.getUsername(), aut.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        String username;

        if(authResult != null && authResult.getName() != null){
            username = authResult.getName();
        }else{
            username = null;
        }

        RoleDto roleDto = userService.findByUsername(username).getRoleDto();

        final String token = JwtTokenUtil.generateToken(username, roleDto.getRoleName());

        Cookie cookie = new Cookie(Constants.TOKEN, token);

        Long cookieExpiration = (JwtTokenUtil.getExpirationDateFromToken(token).getTime() - new Date().getTime())/1000;
        cookie.setMaxAge(cookieExpiration.intValue());

        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}
