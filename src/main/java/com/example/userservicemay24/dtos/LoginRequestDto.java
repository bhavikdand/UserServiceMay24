package com.example.userservicemay24.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;
}