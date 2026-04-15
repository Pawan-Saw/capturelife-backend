package com.pawan.capturelife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pawan.capturelife.model.User;
import com.pawan.capturelife.repository.UserRepository;
import com.pawan.capturelife.config.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ BCrypt inject kiya

    // ✅ Signup API
    @PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody User user) {

        Map<String, Object> response = new HashMap<>();

        if (user.getEmail() == null || user.getPassword() == null) {
            response.put("status", "error");
            response.put("message", "Email & Password required ❌");
            return response;
        }

        if (repo.findByEmail(user.getEmail()) != null) {
            response.put("status", "error");
            response.put("message", "Email already exists ❌");
            return response;
        }

        // ✅ Password hash karke save karo
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        User savedUser = repo.save(user);

        // Password response mein mat bhejo
        savedUser.setPassword(null);

        response.put("status", "success");
        response.put("message", "Signup successful ✅");
        response.put("data", savedUser);

        return response;
    }

    // 🔐 Login API
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {

        Map<String, Object> response = new HashMap<>();

        User dbUser = repo.findByEmail(user.getEmail());

        if (dbUser == null) {
            response.put("status", "error");
            response.put("message", "User not found ❌");
            return response;
        }

        // ✅ BCrypt se password match karo
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            response.put("status", "error");
            response.put("message", "Invalid password ❌");
            return response;
        }

        String token = jwtUtil.generateToken(dbUser.getEmail(), dbUser.getRole());

        // Password token response mein mat bhejo
        dbUser.setPassword(null);

        response.put("status", "success");
        response.put("message", "Login successful ✅");
        response.put("token", token);
        response.put("user", dbUser);

        return response;
    }
}