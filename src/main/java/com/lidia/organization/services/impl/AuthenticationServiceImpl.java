package com.lidia.organization.services.impl;

import com.lidia.organization.security.model.UserPrincipal;
import com.lidia.organization.security.util.JwtUtils;
import com.lidia.organization.security.dto.UserInfoResponse;
import com.lidia.organization.security.dto.LoginRequest;
import com.lidia.organization.services.api.AuthenticationService;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Map<String, Object> logIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var userInfoResponse = new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(),
                roles);

        return Map.of("userInfo", userInfoResponse,
                "jwtCookie", jwtCookie);
    }

    @Override
    public ResponseCookie logOut() {
        return jwtUtils.getCleanJwtCookie();
    }

}
