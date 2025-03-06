package com.example.web_login.controller;

import com.example.web_login.entity.Login;
import com.example.web_login.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/log")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    // ✅ Login Endpoint - Validates Username & Password
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody(required = false) Login login, HttpSession session) {
        System.out.println("Login request received: " + login);

        Map<String, Object> response = new HashMap<>();

        if (login == null || login.getAdminUsername() == null || login.getPassword() == null) {
            response.put("message", "Username and password are required!");
            System.out.println("Error: Missing username or password.");
            return ResponseEntity.badRequest().body(response);
        }

        Login validUser = loginService.findByAdminUsername(login.getAdminUsername());
        System.out.println("Found user: " + validUser);

        if (validUser != null && validUser.getPassword().equals(login.getPassword())) {
            session.setAttribute("loggedInUser", validUser);
            response.put("message", "Login Successful");
            response.put("user", validUser);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid username or password!");
            System.out.println("Login failed: Invalid credentials.");
            return ResponseEntity.status(401).body(response);
        }
    }

    // ✅ Logout Endpoint - Clears User Session
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        session.invalidate(); // Destroy session
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout Successful");
        return ResponseEntity.ok(response);
    }

    // ✅ Fetch Current Logged-in User
    @GetMapping("/me")
    public ResponseEntity<Object> getLoggedInUser(HttpSession session) {
        Object userObj = session.getAttribute("loggedInUser");

        if (userObj == null) {
            return ResponseEntity.status(401).body("No active session found");
        }

        System.out.println("Session User: " + userObj); // ✅ Debugging: Check session
        return ResponseEntity.ok(userObj);
    }


    // ✅ Fetch All Users
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<Login> users = loginService.findAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.status(404).body("No users found!");
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error fetching users: ", e);
            return ResponseEntity.status(500).body("Server error! Please try again later.");
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody Login updatedUser, HttpSession session) {
        Login currentUser = (Login) session.getAttribute("loggedInUser");

        if (currentUser == null) {
            return ResponseEntity.status(401).body("No active session. Please log in.");
        }

        Login existingUser = loginService.findByAdminUsername(currentUser.getAdminUsername());

        if (existingUser != null) {
            existingUser.setAdminUsername(updatedUser.getAdminUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            loginService.saveUser(existingUser);

            // ✅ Update session with new details
            session.setAttribute("loggedInUser", existingUser);

            return ResponseEntity.ok("User updated successfully!");
        } else {
            return ResponseEntity.status(404).body("User not found!");
        }
    }
}
