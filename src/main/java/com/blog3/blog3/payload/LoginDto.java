package com.blog3.blog3.payload;

import lombok.*;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;

////localhost:7070/api/auth/signin
}
