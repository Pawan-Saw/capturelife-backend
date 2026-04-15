package com.pawan.capturelife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pawan.capturelife.model.Pricing;
import java.util.List;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {
    List<Pricing> findByCategory(String category);
}