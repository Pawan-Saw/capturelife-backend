package com.pawan.capturelife.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pricing")
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String packageName;
    private String price;
    private String duration;
    private String photos;
    private String description;
    private String category; // WEDDING, PREWEDDING, PORTRAIT etc

    // Getters
    public Long getId() { return id; }
    public String getPackageName() { return packageName; }
    public String getPrice() { return price; }
    public String getDuration() { return duration; }
    public String getPhotos() { return photos; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setPackageName(String packageName) { this.packageName = packageName; }
    public void setPrice(String price) { this.price = price; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setPhotos(String photos) { this.photos = photos; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
}