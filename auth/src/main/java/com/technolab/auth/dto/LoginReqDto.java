package com.technolab.auth.dto;

import lombok.Data;

@Data
public class LoginReqDto {
    private String email;
    private String password;
    public LoginReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
