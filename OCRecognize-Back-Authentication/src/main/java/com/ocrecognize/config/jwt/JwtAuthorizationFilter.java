package com.ocrecognize.config.jwt;

import com.ocrecognize.utils.Constants;
import com.ocrecognize.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if(httpServletRequest.getCookies() != null){
            Optional<Cookie> optional = Arrays.stream(httpServletRequest.getCookies()).filter(cookie -> Constants.TOKEN.equals(cookie.getName())).findAny();
            if(optional.isPresent()){
                String authToken = optional.get().getValue();

                try {
                    String username = JwtTokenUtil.getUsernameFromToken(authToken);

                    if(Boolean.FALSE.equals(JwtTokenUtil.isTokenExpired(authToken))){
                        List<GrantedAuthority> userRoles = JwtTokenUtil.getRolesFromToken(authToken)
                                .stream().map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());

                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, userRoles);

                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        log.info("User is validated, setting security context");
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {
                log.warn("Couldn't find a token cookie");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
