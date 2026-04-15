package com.pawan.capturelife.model;

import jakarta.persistence.*;

@Entity
@Table(name = "testimonials")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String review;
    private int rating; // 1-5
    private String imageUrl;

    // Getters
    public Long getId() { return id; }
    public String getClientName() { return clientName; }
    public String getReview() { return review; }
    public int getRating() { return rating; }
    public String getImageUrl() { return imageUrl; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public void setReview(String review) { this.review = review; }
    public void setRating(int rating) { this.rating = rating; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}