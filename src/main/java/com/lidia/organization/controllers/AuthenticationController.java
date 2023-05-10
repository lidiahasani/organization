package com.lidia.organization.controllers;

import com.lidia.organization.security.dto.LoginRequest;
import com.lidia.organization.security.dto.UserInfoResponse;
import com.lidia.organization.services.api.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> logIn(@Valid @RequestBody LoginRequest loginRequest) {
        var loginMap = authenticationService.logIn(loginRequest);

        var jwtCookie = (ResponseCookie) loginMap.get("jwtCookie");
        var userInfoResponse = (UserInfoResponse) loginMap.get("userInfo");

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(userInfoResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut() {
        ResponseCookie cookie = authenticationService.logOut();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }
}

