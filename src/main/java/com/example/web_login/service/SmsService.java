package com.example.web_login.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioNumber;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }

    public void sendSms(String to, String message) {
        try {
            Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(twilioNumber),
                    message
            ).create();
            System.out.println("✅ SMS Sent Successfully to: " + to);
        } catch (Exception e) {
            System.err.println("❌ Error Sending SMS to " + to + ": " + e.getMessage());
        }
    }
}
