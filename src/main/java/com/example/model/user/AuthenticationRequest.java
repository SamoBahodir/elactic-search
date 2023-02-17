package com.example.model.user;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
