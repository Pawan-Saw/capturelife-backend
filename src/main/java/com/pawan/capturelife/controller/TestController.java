package com.pawan.capturelife.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class TestController {

    // ✅ Public route - koi bhi access kar sakta hai
    @GetMapping("/")
    public String home() {
        return "Backend Running 🚀";
    }

    // 🔐 Protected route - sirf token wale access kar sakte hain
    @GetMapping("/api/test")
    public Map<String, Object> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Token valid hai! Protected route access hua ✅");
        return response;
    }

    // 🔐 Protected route - Admin only
    @GetMapping("/api/admin")
    public Map<String, Object> admin() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Admin route access hua ✅");
        return response;
    }

    // Admin only route
    @GetMapping("/api/admin/dashboard")
    public Map<String, Object> adminDashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Admin Dashboard ✅ — Sirf ADMIN access kar sakta hai!");
        return response;
    }
}