package com.pawan.capturelife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pawan.capturelife.model.Testimonial;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
}