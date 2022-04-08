package com.example.test.controllers;

import com.example.test.models.AppUser;
import com.example.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() { }

    @GetMapping("/info/{email}")
    public AppUser getUserDetails(@PathVariable String email){
        //String e = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findAppUser(email).get();
    }
    @GetMapping("/info/user/{id}")
    public AppUser getUserDetailsById(@PathVariable int id){
        //String e = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findAppUserById(id).get();
    }
    @PostMapping("/update")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user) {
        return userService.updateAppUser(user);
    }

    @PostMapping("/update/user")
    public ResponseEntity<AppUser> updateUserById(@RequestBody AppUser user) {
        return userService.updateAppUser(user);
    }

}