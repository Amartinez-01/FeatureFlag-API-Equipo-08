package com.empresa.featureflag_api.Dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String Username;
    private String email;
    private String password;
}
