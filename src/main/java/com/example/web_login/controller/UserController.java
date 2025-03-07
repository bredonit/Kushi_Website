package com.example.web_login.controller;




import java.io.PrintWriter;



import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.stream.Collectors;



import com.example.web_login.entity.Login;
import com.example.web_login.service.LoginService;

import jakarta.servlet.http.HttpSession;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.web_login.entity.Serevice_add;
import com.example.web_login.entity.User;
import com.example.web_login.entity.User_login;
import com.example.web_login.repo.Admin_repo;
import com.example.web_login.repo.UserLoginRepository;
import com.example.web_login.repo.Userrepo;
import com.example.web_login.service.UserService;
import com.example.web_login.service.Userimp;




import jakarta.validation.Valid;



import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")

@Controller


public class UserController {
	
	
    @Autowired
    private Userrepo userRepo;

    @Autowired
    private Userimp userimp ;

    @Autowired
    private  Admin_repo admoin_repo ;

    @Autowired
    private UserLoginRepository userLoginRepository;

    
    @GetMapping("/")
    public String home() {
        return "home";  
        
       
    }
    

    @GetMapping("/Bookingss")
    public String Bookings() {
        return "Bookings";  
    }



    
    @GetMapping("/CustomerHelpDesk")
    public String CustomerHelpDesk() {
        return "CustomerHelpDesk";  
    }
    
    
  

    @GetMapping("/financialmanagement")
    public String financialmanagement() {
        return "financialmanagement";  
    }

    @GetMapping("/1")

    public String Costomers1() {
        return "Customers";  
    }
    

    @GetMapping("/addservice")
    public String AddService() {
        return "Add_Service";  
    }
    

    @GetMapping("/viset")
    public String Viset() {
        return "Viset_services";  
    }

    @GetMapping("/Invoices")
    public String Invoices() {
        return "Invoices";  
    }



    
    @GetMapping("/Admin_help_desk")
    public String Help() {
        return "Admin_help_desk";  
    }
    

    @GetMapping("/123")
    public String about1() {
        return "index";  
    }



    @GetMapping("/home2")
    public String about() {
        return "home2";  
    }

    @GetMapping("/guru")
    public String admin() {
        return "admin";  
    }

  
    @GetMapping("/settings")
    public String settings() {
        return "settings";  
    }

    @GetMapping("/profile")
    public String Profile() {
        return "profile";  
    }

    @GetMapping("/Login")
    public String Login() {
        return "Login";
    }
    
    @GetMapping("/kushi-login")
    public String loginn() {
        return "set";  // Should return set.html
    }

    @GetMapping("/Logout")
    public String Logout() {
        return "Logout";
    }



    // service add data api
    



    
    // service add data api
    



    // service add data api


    @GetMapping("/Service_Booking/api")
    public ResponseEntity<List<Serevice_add>> getAllServicesAsApi() {
        try {
            List<Serevice_add> services = admoin_repo.findAll();
            if (services.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(services);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    
    
    
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        // Validate required fields
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        try {
            // Save user if all fields are valid
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error while adding user: " + e.getMessage());
        }
    }
    
    // papular service data api

    @GetMapping("/api/services-details")
    public ResponseEntity<List<Map<String, Object>>> getServiceDetails() {
        try {
            // Fetch all services from the repository
            List<Serevice_add> services = admoin_repo.findAll();

            // If no services are found, return a 204 No Content response
            if (services.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            // Create a list to store service details
            List<Map<String, Object>> serviceDetails = new ArrayList<>();

            for (Serevice_add service : services) {
                Map<String, Object> serviceData = new HashMap<>();
                serviceData.put("serviceName", service.getServiceName());





                serviceData.put("rating", service.getRating() != null ? service.getRating() : "0"); 




                serviceData.put("image", service.getServiceImageUrl() != null ? service.getServiceImageUrl() : "default-image.jpg");
                serviceData.put("amount", service.getServiceCost() != null ? service.getServiceCost() : 0.0);

                // Get rating count (ensure it's not null)
                serviceData.put("ratingCount", service.getRatingCount  () != null ? service.getRatingCount() : 0);

                // Add the service data to the list
                serviceDetails.add(serviceData);
            }

            // Return the service details as a response
            return ResponseEntity.ok(serviceDetails);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // statistics table data displey api

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getStatistics(
            @RequestParam(value = "timePeriod", required = false, defaultValue = "all-time") String timePeriod) {
        try {
            List<User> users = userRepo.findAll();
            LocalDate today = LocalDate.now();

            // Filter users based on the timePeriod
            if ("one-week".equalsIgnoreCase(timePeriod)) {
                users = users.stream()

                             .filter(user -> user.getBOOKING_DATE() != null && 




                                           user.getBOOKING_DATE().isAfter(today.minusWeeks(1)))
                             .collect(Collectors.toList());
            } else if ("two-weeks".equalsIgnoreCase(timePeriod)) {
                users = users.stream()
                             .filter(user -> user.getBOOKING_DATE() != null && 
                                       user.getBOOKING_DATE().isAfter(today.minusWeeks(2)))
                             .collect(Collectors.toList());
            } else if ("one-month".equalsIgnoreCase(timePeriod)) {
                users = users.stream()
                             .filter(user -> user.getBOOKING_DATE() != null && 
                                           user.getBOOKING_DATE().isAfter(today.minusMonths(1)))
                             .collect(Collectors.toList());
            }

            // Aggregate data for the chart
            Map<String, Double> serviceRevenue = new HashMap<>();
            double totalBookingAmount = 0.0;
            for (User user : users) {
                String service = user.getBOOKING_SERVICE_NAME(); // Replace with actual field for service name
                double amount = user.getTOTAL_AMOUNT(); // Replace with actual field for revenue amount
                totalBookingAmount += amount;
                serviceRevenue.put(service, serviceRevenue.getOrDefault(service, 0.0) + amount);
            }

            // Prepare response data
            Map<String, Object> response = new HashMap<>();
            response.put("labels", new ArrayList<>(serviceRevenue.keySet())); // Service names for the chart
            response.put("data", new ArrayList<>(serviceRevenue.values()));  // Revenue amounts for the chart
            response.put("totalCustomers", users.size());                    // Total number of customers
            response.put("totalBookingAmount", totalBookingAmount);          // Total revenue amount

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // displaying recent booking -- new bookings data

    @GetMapping("/recent-bookings")
    public ResponseEntity<List<User>> getRecentBookings() {
        try {
            // Assuming 'Booking' is your entity class for bookings
            List<User> allBookings = userRepo.findAll();

            // Sort the bookings by booking date in descending order
            List<User> sortedBookings = allBookings.stream()
                    .sorted((b1, b2) -> b2.getBOOKING_DATE().compareTo(b1.getBOOKING_DATE()))
                    .limit(5)
                    .collect(Collectors.toList());

            if (sortedBookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.status(HttpStatus.OK).body(sortedBookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // overwive contant displaying data api
   

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(required = false) String timePeriod) {
        try {
            // Fetch all users and bookings
            List<User> users = userRepo.findAll(); // Fetch users
            List<User> bookings = userRepo.findAll(); // Fetch bookings

            // Initialize totals
            BigDecimal totalBookingAmount = BigDecimal.ZERO;
            int totalCustomers = 0;

            // Get the current date for filtering
            LocalDate today = LocalDate.now();

            // Filter users and bookings based on the time period
            if ("one-week".equalsIgnoreCase(timePeriod)) {
                LocalDate startOfWeek = today.minusWeeks(1);
                
                // Filter bookings for the past week and calculate total booking amount
                totalBookingAmount = bookings.stream()
                    .filter(b -> !b.getBOOKING_DATE().isBefore(startOfWeek))
                    .map(b -> new BigDecimal(b.getBOOKING_AMOUNT()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                // Filter customers for the past week
                totalCustomers = (int) users.stream()
                    .filter(u -> !u.getCONFIRMATION_DATE().isBefore(startOfWeek))
                    .count();
            } else if ("two-weeks".equalsIgnoreCase(timePeriod)) {
                LocalDate startOfTwoWeeks = today.minusWeeks(2);
                
                // Filter bookings for the past two weeks and calculate total booking amount
                totalBookingAmount = bookings.stream()
                    .filter(b -> !b.getBOOKING_DATE().isBefore(startOfTwoWeeks))
                    .map(b -> new BigDecimal(b.getBOOKING_AMOUNT()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                // Filter customers for the past two weeks
                totalCustomers = (int) users.stream()
                    .filter(u -> !u.getCONFIRMATION_DATE().isBefore(startOfTwoWeeks))
                    .count();
            } else if ("one-month".equalsIgnoreCase(timePeriod)) {
                LocalDate startOfMonth = today.minusMonths(1);
                
                // Filter bookings for the past month and calculate total booking amount
                totalBookingAmount = bookings.stream()
                    .filter(b -> !b.getBOOKING_DATE().isBefore(startOfMonth))
                    .map(b -> new BigDecimal(b.getBOOKING_AMOUNT()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                // Filter customers for the past month
                totalCustomers = (int) users.stream()
                    .filter(u -> !u.getCONFIRMATION_DATE().isBefore(startOfMonth))
                    .count();
            } else {
                // For all-time data
                totalBookingAmount = bookings.stream()
                    .map(b -> new BigDecimal(b.getBOOKING_AMOUNT()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                totalCustomers = users.size();
            }

            // Prepare the response
            Map<String, Object> response = new HashMap<>();
            response.put("totalBookingAmount", totalBookingAmount);
            response.put("totalCustomers", totalCustomers);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    
    // all bookings
  @GetMapping("/api/bookings")
    public ResponseEntity<List<User>> getAllBookings() {
        try {
            // Fetch all bookings from the database
            List<User> bookings = userRepo.findAll(); // Assuming User entity holds the booking data

            // If no bookings are found, return a 204 No Content response
            if (bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            // Return all bookings as the response
            return ResponseEntity.status(HttpStatus.OK).body(bookings);
        } catch (Exception e) {
            // In case of an error, return a 500 Internal Server Error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


        
    // fancial managenent api 

  @GetMapping("/service-report")
    public ResponseEntity<List<Map<String, Object>>> getServiceReport() {
        try {
            List<Map<String, Object>> reportData = userimp.getServiceReport();

            if (reportData.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(reportData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
        }






    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Login login, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        LoginService loginService = new LoginService();
        boolean isValidUser = loginService.validateLoginByEmail(login);

        if (isValidUser) {
            session.setAttribute("adminEmail", login.getEmail()); // Store email in session
            response.put("message", "Login Successful");
            return ResponseEntity.ok(response); // Return success with 200 OK
        } else {
            response.put("message", "Your credentials are wrong"); // Custom error message
            return ResponseEntity.status(200).body(response); // Return 200 OK but with the error message
        }
    }

    // Logout endpointd
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return ResponseEntity.ok("Logout Successful");
    }


 @PutMapping("/api/bookings/{id}")
    public ResponseEntity<User> updateBooking(@PathVariable Long id, @RequestBody User updatedBooking) {
        try {
            Optional<User> existingBooking = userRepo.findById(id);

            if (existingBooking.isPresent()) {
                User booking = existingBooking.get();

                // ✅ Correctly updating the status from the request body
                booking.setBOOKING_STATUS(updatedBooking.getBOOKING_STATUS());

                // Save the updated booking
                User savedBooking = userRepo.save(booking);

                return ResponseEntity.ok(savedBooking);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // top - booking customer data in booking table api data
   @GetMapping("/top-booked-customers")
    public ResponseEntity<Map<String, Object>> getTopBookedCustomers() {
        try {
            // Fetch bookings
        	 List<User> bookings = userRepo.findAll(); // Fetch bookings

            // Create a map to count bookings per customer
            Map<String, Integer> customerBookingCount = new HashMap<>();

            // Count bookings for each customer
            for (User booking : bookings) {
                String customerId = booking.getCUSTOMER_ID(); // Ensure this method exists and returns a String
                customerBookingCount.put(customerId, customerBookingCount.getOrDefault(customerId, 0) + 1);
            }

            // Sort customers by booking count in descending order and get top 5
            List<Map.Entry<String, Integer>> topCustomers = customerBookingCount.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(5)
                .collect(Collectors.toList());

            // Prepare the response
            List<Map<String, Object>> responseList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : topCustomers) {
                Map<String, Object> customerData = new HashMap<>();
                customerData.put("customerId", entry.getKey());
                customerData.put("bookingCount", entry.getValue());
                responseList.add(customerData);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("topBookedCustomers", responseList);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // this is top-customer data displaying
   
   @GetMapping("/api/getTopCustomers")
    public ResponseEntity<?> getTopCustomers(@RequestParam(required = false) String timePeriod) {
        try {
            LocalDate startDate = null;

            // Handle different time periods
            if (timePeriod != null) {
                switch (timePeriod) {
                    case "one-week":
                        startDate = LocalDate.now().minusWeeks(1);  // Last 1 week
                        break;
                    case "two-weeks":
                        startDate = LocalDate.now().minusWeeks(2);  // Last 2 weeks
                        break;
                    case "one-month":
                        startDate = LocalDate.now().minusMonths(1);  // Last 1 month
                        break;
                    case "all-time":
                        startDate = null;  // No filter, fetch all records
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Invalid time period");
                }
            }

            // Fetch top customers using the service layer
            List<Map<String, Object>> topCustomers = userimp.getTopCustomers(startDate);

            // Return filtered or all top customers based on the date range
            return ResponseEntity.ok(topCustomers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to fetch top customers!");  // Handling errors
        }
    }

    // new booking customr data

   @GetMapping("/api/getTopCustomers-2")
    public ResponseEntity<?> getTopCustomers1(@RequestParam(required = false) String timePeriod) {
        try {
            LocalDate startDate = null;

            // Handling timePeriod filtering
            if (timePeriod != null) {
                switch (timePeriod) {
                    case "one-week":
                        startDate = LocalDate.now().minusWeeks(1);  // Last 1 week
                        break;
                    case "two-weeks":
                        startDate = LocalDate.now().minusWeeks(2);  // Last 2 weeks
                        break;
                    case "one-month":
                        startDate = LocalDate.now().minusMonths(1);  // Last 1 month
                        break;
                    case "all-time":
                        startDate = null;  // No filter, fetch all records
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Invalid time period");
                }
            }

            // Fetch top customers with their latest service and booking date using the service layer
            List<Map<String, Object>> topCustomers = userimp.getTopCustomersWithService(startDate);

            return ResponseEntity.ok(topCustomers);  // Returning top customers
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to fetch top customers!");  // Handling errors
        }
    }


   @PostMapping("/api/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            // Call the register method from UserService to save the user
            userimp.register(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            // Print the exception to the console for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed!");
        }
    }


   
   @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            // Find the existing user
            User existingUser = userRepo.findById(id).orElse(null);
            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            
            // Update the fields of the existing user
           
                
            // Save the updated user
            userRepo.save(existingUser);
            return ResponseEntity.status(HttpStatus.OK).body("User updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error while updating user: " + e.getMessage());
        }
    }


   
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            if (!userRepo.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            userRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error while deleting user: " + e.getMessage());
        }
   }

//Invoice Management 

    @GetMapping("/api/invoices")
    public ResponseEntity<List<Map<String, Object>>> getAllBooking() {
        try {
            List<User> bookings = userRepo.findAll(); // Fetch bookings from the database

            if (bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            // Map bookings to relevant invoice data (customize as needed)
            List<Map<String, Object>> invoiceData = bookings.stream().map(booking -> {
                Map<String, Object> bookingInfo = new HashMap<>();
                bookingInfo.put("id", booking.getBOOKING_ID());
                bookingInfo.put("customerName", booking.getCUSTOMER_NAME());
                bookingInfo.put("customerEmail", booking.getCUSTOMER_EMAIL());
                bookingInfo.put("customerPhone", booking.getCUSTOMER_NUMBER());
                bookingInfo.put("totalAmount", booking.getTOTAL_AMOUNT());
                bookingInfo.put("serviceName", booking.getBOOKING_SERVICE_NAME());

                // No invoiceId generation here
                // Simply return the data without invoiceId
                return bookingInfo;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(invoiceData);  // Return invoice data as JSON
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

