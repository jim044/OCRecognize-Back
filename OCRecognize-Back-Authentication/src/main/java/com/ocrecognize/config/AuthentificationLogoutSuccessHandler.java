package com.ocrecognize.config;

import com.ocrecognize.service.IAuthenticationService;
import com.ocrecognize.utils.Constants;
import com.ocrecognize.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;

public class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private IAuthenticationService authenticationService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication){
        Cookie cookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(c -> c.getName().equals(Constants.TOKEN))
                .findAny()
                .orElseThrow(() -> new RuntimeException());

        try {
            String username = JwtTokenUtil.getUsernameFromToken(cookie.getValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

}
