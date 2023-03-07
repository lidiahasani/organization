package com.lidia.organization.services.api;

import com.lidia.organization.security.dto.LoginRequest;
import org.springframework.http.ResponseCookie;

import java.util.Map;

public interface AuthenticationService {

    Map<String, Object> logIn(LoginRequest loginRequest);

    ResponseCookie logOut();

}
