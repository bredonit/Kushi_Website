package com.example.web_login.service;

import java.util.List;

import com.example.web_login.entity.Offer_image;

public interface Offer_image_service {

    void addimage(Offer_image offer_image);  // Add a new image

    List<Offer_image> getAllImages();  // Get all images

    boolean existsById(Long id);  // Check if an image exists by ID

    void deleteImage(Long id);  // Delete an image by ID

    Offer_image getImageById(Long id);  // Get an image by its ID
}
