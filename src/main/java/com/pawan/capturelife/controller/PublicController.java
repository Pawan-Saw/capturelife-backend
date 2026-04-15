package com.pawan.capturelife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pawan.capturelife.repository.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
@CrossOrigin("*")
public class PublicController {

    @Autowired private TestimonialRepository testimonialRepo;
    @Autowired private PricingRepository pricingRepo;
    @Autowired private GalleryRepository galleryRepo;

    // ✅ Saare testimonials
    @GetMapping("/testimonials")
    public Map<String, Object> getTestimonials() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", testimonialRepo.findAll());
        return res;
    }

    // ✅ Saare pricing packages
    @GetMapping("/pricing")
    public Map<String, Object> getPricing() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", pricingRepo.findAll());
        return res;
    }

    // ✅ Category wise pricing
    @GetMapping("/pricing/{category}")
    public Map<String, Object> getPricingByCategory(@PathVariable String category) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", pricingRepo.findByCategory(category));
        return res;
    }

    // ✅ Saari gallery photos
    @GetMapping("/gallery")
    public Map<String, Object> getGallery() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", galleryRepo.findAll());
        return res;
    }

    // ✅ Category wise gallery
    @GetMapping("/gallery/{category}")
    public Map<String, Object> getGalleryByCategory(@PathVariable String category) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", galleryRepo.findByCategory(category));
        return res;
    }
}