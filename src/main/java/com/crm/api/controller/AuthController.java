package com.crm.api.controller;

import com.crm.api.infrastructure.security.*;
import com.crm.application.ApplicationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(
            JwtTokenUtil jwtTokenUtil,
            PasswordEncoder passwordEncoder,
            ApplicationUserDetailsService userDetailsService
    ) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/api/v1/auth/token")
    public ApplicationToken auth(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        var userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username());
        if (userDetails == null || !this.passwordEncoder.matches(authenticationRequest.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        var token = jwtTokenUtil.generateToken(userDetails);
        var refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new JwtToken(token, refreshToken);
    }
}
