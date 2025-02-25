package com.example.web_login.controller;

import com.example.web_login.entity.User_login;
import com.example.web_login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class User_login_controller {

    @Autowired
    private UserLoginService userLoginService;

   
    //get all login user data
    @GetMapping("/all")
    public ResponseEntity<List<User_login>> getAllUsers() {
        try {
            List<User_login> users = userLoginService.getAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    

    
    
}
