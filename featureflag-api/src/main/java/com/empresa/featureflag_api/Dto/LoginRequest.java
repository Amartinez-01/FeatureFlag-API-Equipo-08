package com.empresa.featureflag_api.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
