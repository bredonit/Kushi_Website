package com.example.web_login.service;

import com.example.web_login.entity.User_login;
import com.example.web_login.repo.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    // Method to get all users
    public List<User_login> getAllUsers() {
        return userLoginRepository.findAll();  // Assuming findAll() is defined in UserLoginRepository
    }
    
    
}
