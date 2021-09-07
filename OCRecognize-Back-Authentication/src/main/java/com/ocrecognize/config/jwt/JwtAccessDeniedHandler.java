package com.ocrecognize.config.jwt;

import com.ocrecognize.utils.ListMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null)
        {
            log.warn("User : " + authentication.getName() + " attempted to access the protected URL : " + httpServletRequest.getRequestURI());
        }else{
            log.warn("Anonyme user attempted to access the protected URL : " + httpServletRequest.getRequestURI());
        }

        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, ListMessages.EXCEPTION_UNAUTHORIZED);
    }
}
