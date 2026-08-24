package com.example.weekly_planner.Respones;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String token ;

    private long expiresAt;

    public LoginResponse(String token , Long expiresAt){
        this.token = token ;
        this.expiresAt = expiresAt;
    }
}
