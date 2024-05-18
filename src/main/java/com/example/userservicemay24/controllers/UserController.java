package com.example.userservicemay24.controllers;

import com.example.userservicemay24.dtos.LoginRequestDto;
import com.example.userservicemay24.dtos.LogoutRequestDto;
import com.example.userservicemay24.dtos.SignupRequestDto;
import com.example.userservicemay24.dtos.ValidateTokenRequestDto;
import com.example.userservicemay24.exceptions.ExpiredTokenException;
import com.example.userservicemay24.exceptions.InvalidTokenException;
import com.example.userservicemay24.exceptions.PasswordMissMatchException;
import com.example.userservicemay24.exceptions.UserNotFoundException;
import com.example.userservicemay24.models.Token;
import com.example.userservicemay24.models.User;
import com.example.userservicemay24.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignupRequestDto requestDto){
        try{
            //TODO add basic validations
            User user = userService.signup(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
            return new ResponseEntity<>(user, HttpStatusCode.valueOf(201));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDto requestDto){
        try{
            // TODO basic validations
            Token token = this.userService.login(requestDto.getEmail(), requestDto.getPassword());
            return new ResponseEntity<>(token, HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto){
        try{
            //TODO some basic validations
            this.userService.logout(requestDto.getToken());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/validate-token")
    public ResponseEntity<Token> validateToken(@RequestBody ValidateTokenRequestDto requestDto){
        try{
            // TODO basic validations
            Token token = this.userService.validateToken(requestDto.getToken());
            return new ResponseEntity<>(token, HttpStatusCode.valueOf(200));
        } catch (ExpiredTokenException ete){
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        } catch (InvalidTokenException ite){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }

    }
}
