package com.example.userservicemay24.services;

import com.example.userservicemay24.exceptions.ExpiredTokenException;
import com.example.userservicemay24.exceptions.InvalidTokenException;
import com.example.userservicemay24.exceptions.PasswordMissMatchException;
import com.example.userservicemay24.models.Token;
import com.example.userservicemay24.models.User;

public interface UserService {

    public User signup(String name, String email, String password) throws Exception;

    public Token login(String email, String password) throws Exception;

    public Token validateToken(String tokenValue) throws InvalidTokenException, ExpiredTokenException;

    public void logout(String token) throws Exception;

}
