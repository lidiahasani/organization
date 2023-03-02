package com.lidia.organization.services.api;

import com.lidia.organization.security.dto.LoginRequest;
import com.lidia.organization.security.dto.MessageResponse;
import com.lidia.organization.security.dto.UserInfoResponse;

import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<UserInfoResponse> logIn(LoginRequest loginRequest);

    ResponseEntity<MessageResponse> logOut();

}
