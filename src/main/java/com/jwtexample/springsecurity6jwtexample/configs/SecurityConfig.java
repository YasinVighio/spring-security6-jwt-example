package com.jwtexample.springsecurity6jwtexample.configs;

import com.jwtexample.springsecurity6jwtexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtEntryPoint securityEntryPoint;

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("auth/login").permitAll() //permit for all (used for logon)
                .requestMatchers("auth/suEp").hasRole("Admin") //permit only for admin
                .requestMatchers("auth/normalEp").hasRole("Normal User") //permit only for normal user
                .anyRequest().authenticated() //permit for both admin and normal
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(securityEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS.STATELESS));

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }

    /**
     * This class is used to set csrf, cors, allowed hosts, authneticated and unauthenticated links, filter and entry point
     */
}
