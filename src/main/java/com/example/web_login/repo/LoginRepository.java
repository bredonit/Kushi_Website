package com.example.web_login.repo;

import com.example.web_login.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByAdminUsername(String adminUsername);
    // Query to find user by email
    Login findByEmail(String email); // Corrected parameter type to String

}
