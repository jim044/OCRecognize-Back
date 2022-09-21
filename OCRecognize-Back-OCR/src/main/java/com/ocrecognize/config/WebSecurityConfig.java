package com.ocrecognize.config;

import com.ocrecognize.config.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthorizationFilter authorizationFilterBean(){
        return new JwtAuthorizationFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF (cross site request forgery)
        httpSecurity.csrf().disable();

        // No session will be created or used by spring security
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        httpSecurity.authorizeRequests()//
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        httpSecurity.exceptionHandling().accessDeniedPage("/login");

        httpSecurity.addFilterBefore(authorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

}
