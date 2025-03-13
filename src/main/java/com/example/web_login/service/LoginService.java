package com.example.web_login.service;

import com.example.web_login.entity.Login;
import com.example.web_login.repo.LoginRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    // Validate login using email
    public boolean validateLoginByEmail(Login login) {
        Login storedLogin = loginRepository.findByEmail(login.getEmail());
        return storedLogin != null && storedLogin.getPassword().equals(login.getPassword());
    }
    public Login findByAdminUsername(String adminUsername) {
        return loginRepository.findByAdminUsername(adminUsername); // Fetch user by username
    }
    public Login validateLoginByUsername(String adminUsername, String password) {
        Login storedLogin = loginRepository.findByAdminUsername(adminUsername);
        if (storedLogin != null && storedLogin.getPassword().equals(password)) {
            return storedLogin; // Return user if credentials match
        }
        return null; // Invalid credentials
    }



    // Fetch admin details by email
    public Login findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    // Fetch all users
    public List<Login> findAllUsers() {
        return loginRepository.findAll();
    }
    public void saveUser(Login user) {
        loginRepository.save(user);
    }
}
