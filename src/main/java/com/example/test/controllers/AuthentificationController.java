package com.example.test.controllers;

import com.example.test.models.AppUser;
import com.example.test.request.LoginRequest;
import com.example.test.services.AuthentificationService;
import com.example.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {

    @Autowired
    private AuthentificationService authentificationService;

    @PostMapping("/register")
    public String register(@RequestBody AppUser user) {
        return authentificationService.registerAppUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AppUser> loginHandler(@RequestBody LoginRequest body) {
        return authentificationService.loginAppUser(body);
    }

    @GetMapping("/confirm-account")
    public String confirmToken(String token) {
        return authentificationService.confirmToken(token);
    }
}
