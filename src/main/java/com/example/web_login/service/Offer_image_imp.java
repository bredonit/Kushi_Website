package com.example.web_login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web_login.entity.Offer_image;
import com.example.web_login.repo.Offer_image_repo;

@Service
public class Offer_image_imp implements Offer_image_service {

    @Autowired
    private Offer_image_repo offer_image_repo;

    @Override
    public void addimage(Offer_image offer_image) {
        // Save the offer image to the database
        offer_image_repo.save(offer_image);
    }

    @Override
    public List<Offer_image> getAllImages() {
        // Fetch all images from the database
        return offer_image_repo.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        // Check if the image exists by ID
        return offer_image_repo.existsById(id);
    }

    @Override
    public void deleteImage(Long id) {
        // Delete the image by ID
        offer_image_repo.deleteById(id);
    }

    @Override
    public Offer_image getImageById(Long id) {
        // Fetch an image by its ID
        return offer_image_repo.findById(id).orElse(null);  // Return null if not found
    }
}
