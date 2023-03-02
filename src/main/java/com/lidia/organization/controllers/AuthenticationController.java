package com.lidia.organization.controllers;

import com.lidia.organization.security.dto.LoginRequest;
import com.lidia.organization.security.dto.MessageResponse;
import com.lidia.organization.security.dto.UserInfoResponse;
import com.lidia.organization.services.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> logIn(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationService.logIn(loginRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logOut() {
        return authenticationService.logOut();
    }
}

