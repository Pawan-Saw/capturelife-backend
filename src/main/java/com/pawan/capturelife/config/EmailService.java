package com.pawan.capturelife.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // ✅ Booking confirmation — User ko email
    public void sendBookingConfirmation(String toEmail, String name, 
                                        String packageName, String date) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Booking Confirmed! - CaptureLife Photography");
        message.setText(
            "Dear " + name + ",\n\n" +
            "Your booking has been confirmed! 🎉\n\n" +
            "Package: " + packageName + "\n" +
            "Date: " + date + "\n\n" +
            "We will contact you soon with more details.\n\n" +
            "Thank you for choosing CaptureLife Photography!\n\n" +
            "Best Regards,\n" +
            "CaptureLife Team"
        );
        mailSender.send(message);
    }

    // ✅ Admin ko notification — Nai booking aayi
    public void sendAdminNotification(String adminEmail, String clientName,
                                       String packageName, String date,
                                       String phone) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("New Booking Alert! - CaptureLife");
        message.setText(
            "New Booking Received! 📸\n\n" +
            "Client Name: " + clientName + "\n" +
            "Package: " + packageName + "\n" +
            "Date: " + date + "\n" +
            "Phone: " + phone + "\n\n" +
            "Login to admin panel to confirm the booking.\n\n" +
            "CaptureLife Admin"
        );
        mailSender.send(message);
    }

    // ✅ Contact form — Admin ko email
    public void sendContactNotification(String adminEmail, String clientName,
                                         String clientEmail, String phone,
                                         String messageText) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("New Contact Message! - CaptureLife");
        message.setText(
            "New Contact Message Received!\n\n" +
            "Name: " + clientName + "\n" +
            "Email: " + clientEmail + "\n" +
            "Phone: " + phone + "\n" +
            "Message: " + messageText + "\n\n" +
            "CaptureLife Admin"
        );
        mailSender.send(message);
    }
}