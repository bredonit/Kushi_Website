package com.example.web_login.repo;

import com.example.web_login.entity.User_login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<User_login, Long> {
    // JpaRepository already provides the findAll method
}
