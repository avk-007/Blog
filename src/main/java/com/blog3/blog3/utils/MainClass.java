package com.blog3.blog3.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainClass {
    //timestamp  8:00 min
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.print(encoder.encode("testing"));
    }
}
