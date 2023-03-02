package com.lidia.organization.security.dto;

import java.util.List;

public record UserInfoResponse (Integer id, String email, List<String> roles){

}

