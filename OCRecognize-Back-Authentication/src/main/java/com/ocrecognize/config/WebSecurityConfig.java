package com.ocrecognize.config;

import com.ocrecognize.config.jwt.JwtAccessDeniedHandler;
import com.ocrecognize.config.jwt.JwtAuthenticationEntryPoint;
import com.ocrecognize.config.jwt.JwtAuthenticationFilter;
import com.ocrecognize.config.jwt.JwtAuthorizationFilter;
import com.ocrecognize.service.impl.UserService;
import com.ocrecognize.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthentificationLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private UserService authenticationService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder){
        try {
            authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler unAuthorizedHandlerBean(){
        return new JwtAccessDeniedHandler();
    }

    @Bean
    public JwtAuthorizationFilter authorizationFilterBean(){
        return new JwtAuthorizationFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(unAuthorizedHandlerBean()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/login").permitAll().antMatchers("/**").hasAnyAuthority(Constants.ADMINISTRATEUR)
                .anyRequest().authenticated()
                .and().logout()
                .logoutUrl("/logout")
                .deleteCookies("token")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(logoutSuccessHandler);
        
        httpSecurity.addFilterBefore(authorizationFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthenticationFilter(super.authenticationManager(), authenticationService));

        httpSecurity.headers().cacheControl();

    }

}
