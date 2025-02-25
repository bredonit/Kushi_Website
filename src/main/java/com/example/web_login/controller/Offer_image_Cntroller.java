package com.example.web_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.web_login.service.Offer_image_service;
import com.example.web_login.entity.Offer_image;

import java.util.List;

@RestController
@RequestMapping("/offer-images")  // Base URL for all mappings in this controller
public class Offer_image_Cntroller {

    @Autowired
    private Offer_image_service offer_image_service;

    // GET mapping to fetch all images
    @GetMapping
    public ResponseEntity<List<Offer_image>> getAllOfferImages() {
        try {
            List<Offer_image> offerImages = offer_image_service.getAllImages();  // Call service to fetch all images
            if (offerImages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Return 204 if no content found
            }
            return ResponseEntity.status(HttpStatus.OK).body(offerImages);  // Return 200 with images
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Return 500 if error occurs
        }
    }


    // POST mapping to create a new image record
    @PostMapping
    public ResponseEntity<String> addOfferImage(@RequestBody Offer_image offer_image) {
        try {
            offer_image_service.addimage(offer_image);  // Call service to save the image
            return ResponseEntity.status(HttpStatus.CREATED).body("Image added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error while adding image: " + e.getMessage());
        }
    }

    // PUT mapping to update an existing image
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOfferImage(@PathVariable Long id, @RequestBody Offer_image offer_image) {
        try {
            Offer_image existingImage = offer_image_service.getImageById(id);  // Find the image by ID
            if (existingImage == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }

            // Update the existing image with the new data
            existingImage.setOfferImageName(offer_image.getOfferImageName());
            existingImage.setOfferImageUrl(offer_image.getOfferImageUrl());

            offer_image_service.addimage(existingImage);  // Save the updated image

            return ResponseEntity.status(HttpStatus.OK).body("Image updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error while updating image: " + e.getMessage());
        }
    }

    // DELETE mapping to remove an image by its ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOfferImage(@PathVariable Long id) {
        try {
            if (!offer_image_service.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }
            offer_image_service.deleteImage(id);  // Call service to delete the image
            return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error while deleting image: " + e.getMessage());
        }
    }
}
