package com.example.web_login.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.web_login.entity.Serevice_add;
import com.example.web_login.repo.Admin_repo;
@CrossOrigin(origins = "*")
@Controller
public class ServiceController {

	 @Autowired
	    private Admin_repo admin_repo;
    // This will handle GET requests for "/service"
   

    
	 @GetMapping("/service")
	 public ResponseEntity<List<Serevice_add>> getAllServices() {
	     try {
	         List<Serevice_add> serviceList = admin_repo.findAll();
	         System.out.println("Fetched Services: " + serviceList); // Debugging
	         
	         if (serviceList.isEmpty()) {
	             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	         }

	         return ResponseEntity.status(HttpStatus.OK).body(serviceList);

	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	     }
	 }
	 //delite 
	 
	 @DeleteMapping("/service/{id}")
	 public ResponseEntity<?> deleteService(@PathVariable Long id) {
	     try {
	    	 admin_repo.deleteById(id);
	         return ResponseEntity.ok().body("Service deleted successfully.");
	     } catch (Exception e) {
	         e.printStackTrace();  // Log the exception
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body("Failed to delete service.");
	     }
	 }

	 





    // This will handle requests for "/service/another"
	 @PostMapping("/add-service")
	 public ResponseEntity<?> addService(@RequestBody Serevice_add service) {
	     try {
	         if (service.getServiceName() == null || service.getServiceName().trim().isEmpty()) {
	             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Service name is required"));
	         }

	         Serevice_add savedService = admin_repo.save(service);
	         return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Service added successfully", "service", savedService));
	     } catch (Exception e) {
	         e.printStackTrace(); // Shows the full error in the console
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
	     }
	 }


    // You can also use more specific mappings (POST, PUT, etc.)
    // @PostMapping, @PutMapping, etc.
}
