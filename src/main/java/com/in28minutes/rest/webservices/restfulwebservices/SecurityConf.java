package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter{
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Disable CSRF
            .csrf().disable()
            // Enable basic authentication
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .httpBasic();
    }
    
}
