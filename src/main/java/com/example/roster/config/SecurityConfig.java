package com.example.roster.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * Handles the security related spring configs
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //TODO: Dummy service. CHange the auth to a suitable one.
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Enables the basic auth
        http.csrf().disable().authorizeRequests()
                .anyRequest()
                .hasAnyRole("ADMIN")
                .and().httpBasic();
    }
}
