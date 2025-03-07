package com.example.web_login.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.web_login.entity.User;
import com.example.web_login.repo.UserLoginRepository;
import com.example.web_login.repo.Userrepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class Userimp {
    
    @Autowired
    private EntityManager entityManager;
    
    
    
    @Autowired
    private UserLoginRepository userLoginRepository;

    // This method retrieves top customers based on booking count
    public List<Map<String, Object>> getTopCustomers(LocalDate startDate) {
        String query = "SELECT u.USER_ID AS userId, u.USER_FIRST_NAME AS firstName, u.PROFILE_PICTURE AS profilePicture, " +
                       "       COUNT(b.BOOKING_ID) AS bookingCount " +
                       "FROM TBL_BOOKING_INFO b " +
                       "JOIN USER_LOGIN_INFO u ON b.CUSTOMER_ID = u.USER_ID " +
                       (startDate != null ? "WHERE b.BOOKING_DATE >= :startDate " : "") +
                       "GROUP BY u.USER_ID, u.USER_FIRST_NAME, u.PROFILE_PICTURE " +
                       "ORDER BY bookingCount DESC";

        Query nativeQuery = entityManager.createNativeQuery(query);

        if (startDate != null) {
            nativeQuery.setParameter("startDate", java.sql.Date.valueOf(startDate));
        }

        @SuppressWarnings("unchecked")
        List<Object[]> results = nativeQuery.getResultList();

        List<Map<String, Object>> topCustomers = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> customer = new HashMap<>();
            customer.put("userId", row[0]);
            customer.put("name", row[1]);
            customer.put("imageUrl", row[2]);
            customer.put("bookingCount", row[3]);
            topCustomers.add(customer);
        }

        return topCustomers;
    }
   
    // This method retrieves top customers with their latest booking service and date
    public List<Map<String, Object>> getTopCustomersWithService(LocalDate startDate) {
        String query = "SELECT u.USER_ID AS userId, " +
                       "       u.USER_FIRST_NAME AS firstName, " +
                       "       u.PROFILE_PICTURE AS profilePicture, " +
                       "       COUNT(b.BOOKING_ID) AS bookingCount, " +
                       "       MAX(b.BOOKING_DATE) AS bookingDate, " +
                       "       MAX(b.BOOKING_TIME) AS bookingTime, " + 
                       "       b.BOOKING_SERVICE_NAME AS bookingServiceName " +
                       "FROM TBL_BOOKING_INFO b " +
                       "JOIN USER_LOGIN_INFO u ON b.CUSTOMER_ID = u.USER_ID " +
                       (startDate != null ? "WHERE b.BOOKING_DATE >= :startDate " : "") +
                       "GROUP BY u.USER_ID, u.USER_FIRST_NAME, u.PROFILE_PICTURE, b.BOOKING_SERVICE_NAME " +
                       "ORDER BY MAX(b.BOOKING_DATE) DESC"; 

        Query nativeQuery = entityManager.createNativeQuery(query);

        if (startDate != null) {
            nativeQuery.setParameter("startDate", java.sql.Date.valueOf(startDate));
        }

        @SuppressWarnings("unchecked")
        List<Object[]> results = nativeQuery.getResultList();

        List<Map<String, Object>> topCustomers = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> customer = new HashMap<>();
            customer.put("userId", row[0]);
            customer.put("name", row[1]); // Mapping firstName to name
            customer.put("imageUrl", row[2]); // Mapping profilePicture to imageUrl
            customer.put("bookingCount", row[3]);
            customer.put("bookingDate", row[4]); // Adding bookingDate
            customer.put("bookingTime", row[5]); // Adding bookingTime
            customer.put("bookingServiceName", row[6]); // Adding bookingServiceName
            topCustomers.add(customer);
        }

        return topCustomers;
    }

    // Register method if needed
    public void register(User user) {
        // Logic to register the user
    }
    public double getTOTAL_AMOUNT() {
        return getTOTAL_AMOUNT();
    }
    
    @Autowired

    private  Userrepo userRepo;
    private static final String ADMIN_NUMBER = "+919100287352"; 



	
    public void UserService(Userrepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<Map<String, Object>> getServiceReport() {
        List<User> bookings = userRepo.findAll(); // Fetch all bookings

        // Group bookings by service name
        Map<String, List<User>> groupedBookings = bookings.stream()
            .filter(user -> user.getBOOKING_SERVICE_NAME() != null) // Avoid null service names
            .collect(Collectors.groupingBy(User::getBOOKING_SERVICE_NAME, LinkedHashMap::new, Collectors.toList()));

        List<Map<String, Object>> result = new ArrayList<>();

        for (Map.Entry<String, List<User>> entry : groupedBookings.entrySet()) {
            String serviceName = entry.getKey();
            List<User> serviceBookings = entry.getValue();

            double totalRevenue = serviceBookings.stream()
                .mapToDouble(User::getTOTAL_AMOUNT) // Sum up total amounts
                .sum();

            int bookingCount = serviceBookings.size(); // Count bookings

            Map<String, Object> map = new HashMap<>();
            map.put("serviceName", serviceName);
            map.put("totalRevenue", totalRevenue);
            map.put("bookingCount", bookingCount);

            result.add(map);
        }
        return result;
    

}

    @Autowired
    private SmsService smsService;

    @Transactional
    public User saveBooking(User booking) {
        // ✅ Save booking to the database
        User savedBooking = userRepo.save(booking);

        // ✅ Admin Notification
        String adminNumber = "+919100287352"; // Ensure correct admin number
        smsService.sendSms(adminNumber, "📢 New Booking Received: " + savedBooking.getBOOKING_SERVICE_NAME());
        System.out.println("📩 Admin notified!");

        // ✅ Customer Notification
        String customerNumber = savedBooking.getCUSTOMER_NUMBER();
        
        if (customerNumber != null) {
            customerNumber = customerNumber.trim(); // Remove spaces

            if (customerNumber.matches("\\d{10}")) { // Validate 10-digit number
                smsService.sendSms("+91" + customerNumber, "✅ Booking Confirmed! Your Booking ID: " + savedBooking.getBOOKING_ID());
                System.out.println("📩 SMS sent to customer: " + customerNumber);
            } else {
                System.err.println("❌ Invalid customer number: " + customerNumber);
            }
        } else {
            System.err.println("❌ Customer number is NULL!");
        }

        return savedBooking;
    }

}

