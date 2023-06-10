package com.forum.microservice.login;


import com.forum.microservice.login.auth.LoginDto;
import com.forum.microservice.login.auth.LoginResponse;
import com.forum.microservice.login.auth.TokenDto;
import com.forum.microservice.login.auth.ValidateTokenResponse;
import com.forum.microservice.login.error.exception.WrappedException;
import com.forum.microservice.login.jwt.JwtTokenUtil;
import com.forum.microservice.login.role.RoleDataService;
import com.forum.microservice.login.user.CustomUserDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;

import static com.forum.microservice.login.error.ErrorConstants.GENERAL_ERROR;
import static com.forum.microservice.login.error.ErrorConstants.USER_ALREADY_EXISTS;


@RestController
@RequestMapping("/user/auth")
public class AuthController {

    @Autowired
    CustomUserDataService customUserDataService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    RoleDataService roleDataService;
    @PostMapping("/register")
    @ResponseBody
    public GeneralSuccessResponse registerCustomUser(@RequestBody @Valid CustomUser customUser){
        if (customUserDataService.getUserByEmail(customUser.getEmail()) != null){
            throw new WrappedException(USER_ALREADY_EXISTS);
        }

        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));

        Role roles = roleDataService.getRoleByName("ROLE_ADMIN");
        customUser.setRoles(Collections.singleton(roles));

        customUserDataService.createOrUpdateUser(customUser);
        return new GeneralSuccessResponse();
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginResponse authenticateUser(@RequestBody LoginDto loginDto){
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtTokenUtil.generateToken(userDetails));
        return loginResponse;
    }

    @PostMapping("/validate")
    public ValidateTokenResponse validateToken(@RequestBody TokenDto tokenDto){
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(tokenDto.getToken()));
        if(jwtTokenUtil.validateToken(tokenDto.getToken(), userDetails)){
            ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse();
            validateTokenResponse.setAuthorities(userDetails.getAuthorities());
            validateTokenResponse.setEmail(userDetails.getUsername());
            return validateTokenResponse;
        }
        else{
            throw new WrappedException(GENERAL_ERROR);
        }
    }



}
