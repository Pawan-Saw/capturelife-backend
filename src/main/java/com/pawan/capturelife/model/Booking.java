package com.pawan.capturelife.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String shootDate;
    private String shootLocation;
    private String packageName;
    private String price;
    private String status; // PENDING, CONFIRMED, CANCELLED

    // Getters
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getShootDate() { return shootDate; }
    public String getShootLocation() { return shootLocation; }
    public String getPackageName() { return packageName; }
    public String getPrice() { return price; }
    public String getStatus() { return status; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setShootDate(String shootDate) { this.shootDate = shootDate; }
    public void setShootLocation(String shootLocation) { this.shootLocation = shootLocation; }
    public void setPackageName(String packageName) { this.packageName = packageName; }
    public void setPrice(String price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
}