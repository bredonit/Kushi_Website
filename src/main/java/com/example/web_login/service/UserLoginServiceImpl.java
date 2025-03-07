package com.example.web_login.service;

import com.example.web_login.entity.User;
import com.example.web_login.entity.User_login;
import com.example.web_login.repo.UserLoginRepository;

import java.util.List;

// Assuming you have a repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserLoginServiceImpl extends UserLoginService {

   

    // Constructor-based Dependency Injection
    @Autowired
    public UserLoginRepository userLoginRepository ;

   

    

}
