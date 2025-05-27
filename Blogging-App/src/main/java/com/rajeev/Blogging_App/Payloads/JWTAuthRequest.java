package com.rajeev.Blogging_App.Payloads;


import lombok.Data;

@Data
public class JWTAuthRequest {

    private String username;

    private String password;
}
