package com.example.web_login.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.web_login.entity.Offer_image;

public interface Offer_image_repo extends JpaRepository<Offer_image, Long> {
    // You can define custom queries here if needed
}
