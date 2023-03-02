package com.lidia.organization.services;

import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.RoleRepository;
import com.lidia.organization.security.SecurityUser;
import com.lidia.organization.util.JwtUtils;
import com.lidia.organization.security.repsonse.MessageResponse;
import com.lidia.organization.security.repsonse.UserInfoResponse;
import com.lidia.organization.security.LoginRequest;
import com.lidia.organization.util.Mapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final PunonjesRepository punonjesRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final Mapper mapper;
    private final PasswordEncoder encoder;
    private final DepartamentRepository departamentRepository;


    public AuthenticationService(PunonjesRepository punonjesRepository, RoleRepository roleRepository, JwtUtils jwtUtils, AuthenticationManager authenticationManager, Mapper mapper, PasswordEncoder encoder, DepartamentRepository departamentRepository) {
        this.punonjesRepository = punonjesRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
        this.encoder = encoder;
        this.departamentRepository = departamentRepository;
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        roles));
    }

    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Sign Out i suksesshem!"));
    }
}
