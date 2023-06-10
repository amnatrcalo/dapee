package com.forum.microservice.login;


import com.forum.microservice.login.error.exception.WrappedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.forum.microservice.login.error.ErrorConstants.INVALID_LOGIN_DETAILS;


public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication){
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        UserDetails userDetails;
        try {
            userDetails = customUserDetailsService.loadUserByUsername(authenticationToken.getPrincipal().toString());
        } catch (UsernameNotFoundException exception) {
            throw new WrappedException(INVALID_LOGIN_DETAILS);
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
