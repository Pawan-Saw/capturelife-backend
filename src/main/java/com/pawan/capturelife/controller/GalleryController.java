package com.pawan.capturelife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pawan.capturelife.model.Gallery;
import com.pawan.capturelife.repository.GalleryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepo;

    // ✅ Public — Saari photos dekho
    @GetMapping("/api/gallery")
    public Map<String, Object> getAllPhotos() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", galleryRepo.findAll());
        return res;
    }

    // ✅ Public — Category se photos dekho
    @GetMapping("/api/gallery/{category}")
    public Map<String, Object> getByCategory(@PathVariable String category) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", galleryRepo.findByCategory(category));
        return res;
    }

    // ✅ Admin — Photo add karo
    @PostMapping("/api/admin/gallery")
    public Map<String, Object> addPhoto(@RequestBody Gallery gallery) {
        Map<String, Object> res = new HashMap<>();
        Gallery saved = galleryRepo.save(gallery);
        res.put("status", "success");
        res.put("message", "Photo added ✅");
        res.put("data", saved);
        return res;
    }

    // ✅ Admin — Photo edit karo
    @PutMapping("/api/admin/gallery/{id}")
    public Map<String, Object> updatePhoto(@PathVariable Long id,
                                            @RequestBody Gallery updated) {
        Map<String, Object> res = new HashMap<>();
        Gallery g = galleryRepo.findById(id).orElse(null);
        if (g == null) {
            res.put("status", "error");
            res.put("message", "Photo not found ❌");
            return res;
        }
        g.setImageUrl(updated.getImageUrl());
        g.setTitle(updated.getTitle());
        g.setCategory(updated.getCategory());
        g.setDescription(updated.getDescription());
        galleryRepo.save(g);
        res.put("status", "success");
        res.put("message", "Photo updated ✅");
        return res;
    }

    // ✅ Admin — Photo delete karo
    @DeleteMapping("/api/admin/gallery/{id}")
    public Map<String, Object> deletePhoto(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        galleryRepo.deleteById(id);
        res.put("status", "success");
        res.put("message", "Photo deleted ✅");
        return res;
    }
}