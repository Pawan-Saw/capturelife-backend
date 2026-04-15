package com.pawan.capturelife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pawan.capturelife.model.ContactMessage;
import com.pawan.capturelife.repository.ContactRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin("*")
public class ContactController {

    @Autowired
    private ContactRepository contactRepo;

    @PostMapping
    public Map<String, Object> sendMessage(@RequestBody ContactMessage message) {
        Map<String, Object> res = new HashMap<>();
        message.setStatus("UNREAD");
        contactRepo.save(message);
        res.put("status", "success");
        res.put("message", "Message sent ✅");
        return res;
    }
}