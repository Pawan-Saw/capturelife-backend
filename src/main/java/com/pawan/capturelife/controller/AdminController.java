package com.pawan.capturelife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pawan.capturelife.model.*;
import com.pawan.capturelife.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired private BookingRepository bookingRepo;
    @Autowired private TestimonialRepository testimonialRepo;
    @Autowired private PricingRepository pricingRepo;
    @Autowired private ContactRepository contactRepo;

    // ==================== BOOKINGS ====================

    // ✅ Saari bookings dekho
    @GetMapping("/bookings")
    public Map<String, Object> getAllBookings() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", bookingRepo.findAll());
        return res;
    }

    // ✅ Booking status update karo
    @PutMapping("/bookings/{id}/status")
    public Map<String, Object> updateBookingStatus(@PathVariable Long id,
                                                    @RequestParam String status) {
        Map<String, Object> res = new HashMap<>();
        Booking booking = bookingRepo.findById(id).orElse(null);
        if (booking == null) {
            res.put("status", "error");
            res.put("message", "Booking not found ❌");
            return res;
        }
        booking.setStatus(status);
        bookingRepo.save(booking);
        res.put("status", "success");
        res.put("message", "Booking status updated ✅");
        return res;
    }

    // ✅ Booking delete karo
    @DeleteMapping("/bookings/{id}")
    public Map<String, Object> deleteBooking(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        bookingRepo.deleteById(id);
        res.put("status", "success");
        res.put("message", "Booking deleted ✅");
        return res;
    }

    // ==================== TESTIMONIALS ====================

    // ✅ Saare testimonials dekho
    @GetMapping("/testimonials")
    public Map<String, Object> getAllTestimonials() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", testimonialRepo.findAll());
        return res;
    }

    // ✅ Testimonial add karo
    @PostMapping("/testimonials")
    public Map<String, Object> addTestimonial(@RequestBody Testimonial testimonial) {
        Map<String, Object> res = new HashMap<>();
        Testimonial saved = testimonialRepo.save(testimonial);
        res.put("status", "success");
        res.put("message", "Testimonial added ✅");
        res.put("data", saved);
        return res;
    }

    // ✅ Testimonial edit karo
    @PutMapping("/testimonials/{id}")
    public Map<String, Object> updateTestimonial(@PathVariable Long id,
                                                  @RequestBody Testimonial updated) {
        Map<String, Object> res = new HashMap<>();
        Testimonial t = testimonialRepo.findById(id).orElse(null);
        if (t == null) {
            res.put("status", "error");
            res.put("message", "Testimonial not found ❌");
            return res;
        }
        t.setClientName(updated.getClientName());
        t.setReview(updated.getReview());
        t.setRating(updated.getRating());
        t.setImageUrl(updated.getImageUrl());
        testimonialRepo.save(t);
        res.put("status", "success");
        res.put("message", "Testimonial updated ✅");
        return res;
    }

    // ✅ Testimonial delete karo
    @DeleteMapping("/testimonials/{id}")
    public Map<String, Object> deleteTestimonial(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        testimonialRepo.deleteById(id);
        res.put("status", "success");
        res.put("message", "Testimonial deleted ✅");
        return res;
    }

    // ==================== PRICING ====================

    // ✅ Saare packages dekho
    @GetMapping("/pricing")
    public Map<String, Object> getAllPricing() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", pricingRepo.findAll());
        return res;
    }

    // ✅ Package add karo
    @PostMapping("/pricing")
    public Map<String, Object> addPricing(@RequestBody Pricing pricing) {
        Map<String, Object> res = new HashMap<>();
        Pricing saved = pricingRepo.save(pricing);
        res.put("status", "success");
        res.put("message", "Package added ✅");
        res.put("data", saved);
        return res;
    }

    // ✅ Package edit karo
    @PutMapping("/pricing/{id}")
    public Map<String, Object> updatePricing(@PathVariable Long id,
                                              @RequestBody Pricing updated) {
        Map<String, Object> res = new HashMap<>();
        Pricing p = pricingRepo.findById(id).orElse(null);
        if (p == null) {
            res.put("status", "error");
            res.put("message", "Package not found ❌");
            return res;
        }
        p.setPackageName(updated.getPackageName());
        p.setPrice(updated.getPrice());
        p.setDuration(updated.getDuration());
        p.setPhotos(updated.getPhotos());
        p.setDescription(updated.getDescription());
        p.setCategory(updated.getCategory());
        pricingRepo.save(p);
        res.put("status", "success");
        res.put("message", "Package updated ✅");
        return res;
    }

    // ✅ Package delete karo
    @DeleteMapping("/pricing/{id}")
    public Map<String, Object> deletePricing(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        pricingRepo.deleteById(id);
        res.put("status", "success");
        res.put("message", "Package deleted ✅");
        return res;
    }

    // ==================== CONTACT MESSAGES ====================

    // ✅ Saare messages dekho
    @GetMapping("/contacts")
    public Map<String, Object> getAllContacts() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", contactRepo.findAll());
        return res;
    }

    // ✅ Message read mark karo
    @PutMapping("/contacts/{id}/read")
    public Map<String, Object> markAsRead(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        ContactMessage msg = contactRepo.findById(id).orElse(null);
        if (msg == null) {
            res.put("status", "error");
            res.put("message", "Message not found ❌");
            return res;
        }
        msg.setStatus("READ");
        contactRepo.save(msg);
        res.put("status", "success");
        res.put("message", "Marked as read ✅");
        return res;
    }

    // ✅ Message delete karo
    @DeleteMapping("/contacts/{id}")
    public Map<String, Object> deleteContact(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        contactRepo.deleteById(id);
        res.put("status", "success");
        res.put("message", "Message deleted ✅");
        return res;
    }
}