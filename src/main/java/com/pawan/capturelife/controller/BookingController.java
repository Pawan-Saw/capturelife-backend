package com.pawan.capturelife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pawan.capturelife.model.Booking;
import com.pawan.capturelife.repository.BookingRepository;
import com.pawan.capturelife.config.EmailService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private EmailService emailService;

    // ✅ New booking
    @PostMapping
    public Map<String, Object> createBooking(@RequestBody Booking booking) {
        Map<String, Object> response = new HashMap<>();

        if (booking.getFullName() == null || booking.getEmail() == null) {
            response.put("status", "error");
            response.put("message", "Name & Email required ❌");
            return response;
        }

        booking.setStatus("PENDING");
        Booking saved = bookingRepo.save(booking);

        // ✅ User ko confirmation email
        try {
            emailService.sendBookingConfirmation(
                booking.getEmail(),
                booking.getFullName(),
                booking.getPackageName(),
                booking.getShootDate()
            );
        } catch (Exception e) {
            System.out.println("Email error: " + e.getMessage());
        }

        // ✅ Admin ko notification email
        try {
            emailService.sendAdminNotification(
                "pksaw7717@gmail.com", // ← apna admin email daalo
                booking.getFullName(),
                booking.getPackageName(),
                booking.getShootDate(),
                booking.getPhone()
            );
        } catch (Exception e) {
            System.out.println("Admin email error: " + e.getMessage());
        }

        response.put("status", "success");
        response.put("message", "Booking confirmed ✅");
        response.put("data", saved);
        return response;
    }

    // ✅ Saari bookings
    @GetMapping
    public Map<String, Object> getAllBookings() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", bookingRepo.findAll());
        return response;
    }

    // ✅ Email se booking
    @GetMapping("/my/{email}")
    public Map<String, Object> getMyBookings(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", bookingRepo.findByEmail(email));
        return response;
    }

    // ✅ Status update
    @PutMapping("/{id}/status")
    public Map<String, Object> updateStatus(@PathVariable Long id,
                                             @RequestParam String status) {
        Map<String, Object> response = new HashMap<>();
        Booking booking = bookingRepo.findById(id).orElse(null);
        if (booking == null) {
            response.put("status", "error");
            response.put("message", "Booking not found ❌");
            return response;
        }
        booking.setStatus(status);
        bookingRepo.save(booking);
        response.put("status", "success");
        response.put("message", "Status updated ✅");
        return response;
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteBooking(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        bookingRepo.deleteById(id);
        response.put("status", "success");
        response.put("message", "Booking cancelled ✅");
        return response;
    }
}