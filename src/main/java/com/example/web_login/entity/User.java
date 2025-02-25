	package com.example.web_login.entity;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotNull;
	
	import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
	import lombok.*;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name = "TBL_BOOKING_INFO")
	public class User {
	
		 @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY) 
		    @Column(name = "BOOKING_ID")
		    private Long BOOKING_ID; 
	        
		 @NotNull(message = "Username is required")
		    @Column(name = " CUSTOMER_ID ") 
		    private String CUSTOMER_ID;
		 
		 @NotNull(message = "Username is required")
		    @Column(name = "CUSTOMER_NAME") 
		    private String CUSTOMER_NAME;
		 
		
	
		 
		 @NotNull(message = "Phone number is required")
		    @Column(name = " CUSTOMER_NUMBER") 
		    private String CUSTOMER_NUMBER;
		 
		 @NotNull(message = "Reference name is required")
		    @Column(name = "REFERENCE_NAME")
		    private String REFERENCE_NAME;
		 
		 @NotNull(message = "Reference name is required")
		    @Column(name = " REFERENCE_DETAILS")
		    private String  REFERENCE_DETAILS;
	
		  
		
	
			@NotNull(message = "Booking service name is required")
		    @Column(name = "BOOKING_SERVICE_NAME") 
		    private String BOOKING_SERVICE_NAME;
		    
		    @NotNull(message = "Booking date is required")
		    @Column(name = "BOOKING_DATE")
		    private LocalDate BOOKING_DATE;
		    
		    @NotNull(message = "Booking date is required")
		    @Column(name = "CONFIRMATION_DATE ") 
		    private LocalDate CONFIRMATION_DATE;
		    
		    
		    @Column(name = " BOOKING_STATUS")
		    private String BOOKING_STATUS;
		    
		    
		    @Column(name = "  PAYMENT_STATUS")
		    private String PAYMENT_STATUS;
		    
		    @NotNull(message = "Booking date is required")
		    @Column(name = "  BOOKING_AMOUNT")
		    private String BOOKING_AMOUNT;
		    
		    @NotNull(message = "Booking date is required")
		    @Column(name = "TOTAL_AMOUNT")
		    private double TOTAL_AMOUNT;
		    
		    @NotNull(message = "Customer address is required")
		    @Column(name = "ADDRESS_LINE_1")
		    private String ADDRESS_LINE_1;
	        
		    @NotNull(message = "Customer address is required")
		    @Column(name = "ADDRESS_LINE_2")
		    private String ADDRESS_LINE_2;
		    
		    @NotNull(message = "Customer address is required")
		    @Column(name = "ADDRESS_LINE_3")
		    private String ADDRESS_LINE_3;
		    
		    
		    @NotNull(message = "Customer address is required")
		    @Column(name = " CITY")
		    private String CITY;
		    
		    @NotNull(message = "Postal code is required")
		    @Column(name = " ZIP_CODE")
		    private String ZIP_CODE;
		    
		    @NotNull(message = "Postal code is required")
		    @Column(name = " REMARKS ")
		    private String REMARKS;
		    
		    @NotNull(message = "Postal code is required")
		    @Column(name = " CREATED_BY ")
		    private String CREATED_BY;
		    
		    @NotNull(message = "Postal code is required")
		    @Column(name = " CREATED_DATE ")
		    private String CREATED_DATE;
		    
		    @NotNull(message = "Postal code is required") 
		    @Column(name = " UPDATED_BY ")
		    private String UPDATED_BY;
		    
		    @NotNull(message = "Postal code is required")
		    @Column(name = " UPDATED_DATE ")
		    private String UPDATED_DATE;
		    

		    @NotNull(message = "Postal code is required")
		    @Column(name = " BOOKING_TIME ")
		    private String BOOKING_TIME;
	
		    @PrePersist
		    public void autoGenerateBookingTime() {
		        // Automatically set the booking time when the entity is persisted
		        this.BOOKING_TIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		    }

		    // Getters and setters
		    public String getBOOKING_TIME() {
		        return BOOKING_TIME;
		    }

		    public void setBOOKING_TIME(String BOOKING_TIME) {
		        this.BOOKING_TIME = BOOKING_TIME;
		    }
			@Override
			public String toString() {
				return "User [BOOKING_ID=" + BOOKING_ID + ", CUSTOMER_ID=" + CUSTOMER_ID + ", CUSTOMER_NAME="
						+ CUSTOMER_NAME + ", CUSTOMER_NUMBER=" + CUSTOMER_NUMBER + ", REFERENCE_NAME=" + REFERENCE_NAME
						+ ", REFERENCE_DETAILS=" + REFERENCE_DETAILS + ", BOOKING_SERVICE_NAME=" + BOOKING_SERVICE_NAME
						+ ", BOOKING_DATE=" + BOOKING_DATE + ", CONFIRMATION_DATE=" + CONFIRMATION_DATE
						+ ", BOOKING_STATUS=" + BOOKING_STATUS + ", PAYMENT_STATUS=" + PAYMENT_STATUS + ", BOOKING_AMOUNT="
						+ BOOKING_AMOUNT + ", TOTAL_AMOUNT=" + TOTAL_AMOUNT + ", ADDRESS_LINE_1=" + ADDRESS_LINE_1
						+ ", ADDRESS_LINE_2=" + ADDRESS_LINE_2 + ", ADDRESS_LINE_3=" + ADDRESS_LINE_3 + ", CITY=" + CITY
						+ ", ZIP_CODE=" + ZIP_CODE + ", REMARKS=" + REMARKS + ", CREATED_BY=" + CREATED_BY
						+ ", CREATED_DATE=" + CREATED_DATE + ", UPDATED_BY=" + UPDATED_BY + ", UPDATED_DATE=" + UPDATED_DATE
						+ ", getBOOKING_ID()=" + getBOOKING_ID() + ", getCUSTOMER_ID()=" + getCUSTOMER_ID()
						+ ", getCUSTOMER_NAME()=" + getCUSTOMER_NAME() + ", getCUSTOMER_NUMBER()=" + getCUSTOMER_NUMBER()
						+ ", getREFERENCE_NAME()=" + getREFERENCE_NAME() + ", getREFERENCE_DETAILS()="
						+ getREFERENCE_DETAILS() + ", getBOOKING_SERVICE_NAME()=" + getBOOKING_SERVICE_NAME()
						+ ", getBOOKING_DATE()=" + getBOOKING_DATE() + ", getCONFIRMATION_DATE()=" + getCONFIRMATION_DATE()
						+ ", getBOOKING_STATUS()=" + getBOOKING_STATUS() + ", getPAYMENT_STATUS()=" + getPAYMENT_STATUS()
						+ ", getBOOKING_AMOUNT()=" + getBOOKING_AMOUNT() + ", getTOTAL_AMOUNT()=" + getTOTAL_AMOUNT()
						+ ", getADDRESS_LINE_1()=" + getADDRESS_LINE_1() + ", getADDRESS_LINE_2()=" + getADDRESS_LINE_2()
						+ ", getADDRESS_LINE_3()=" + getADDRESS_LINE_3() + ", getCITY()=" + getCITY() + ", getZIP_CODE()="
						+ getZIP_CODE() + ", getREMARKS()=" + getREMARKS() + ", getCREATED_BY()=" + getCREATED_BY()
						+ ", getCREATED_DATE()=" + getCREATED_DATE() + ", getUPDATED_BY()=" + getUPDATED_BY()
						+ ", getUPDATED_DATE()=" + getUPDATED_DATE() + ", getClass()=" + getClass() + ", hashCode()="
						+ hashCode() + ", toString()=" + super.toString() + "]";
			}
	
			public Long getBOOKING_ID() {
				return BOOKING_ID;
			}
	
			public void setBOOKING_ID(Long bOOKING_ID) {
				BOOKING_ID = bOOKING_ID;
			}
	
			public String getCUSTOMER_ID() {
				return CUSTOMER_ID;
			}
	
			public void setCUSTOMER_ID(String cUSTOMER_ID) {
				CUSTOMER_ID = cUSTOMER_ID;
			}
	
			public String getCUSTOMER_NAME() {
				return CUSTOMER_NAME;
			}
	
			public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
				CUSTOMER_NAME = cUSTOMER_NAME;
			}
	
			public String getCUSTOMER_NUMBER() {
				return CUSTOMER_NUMBER;
			}
	
			public void setCUSTOMER_NUMBER(String cUSTOMER_NUMBER) {
				CUSTOMER_NUMBER = cUSTOMER_NUMBER;
			}
	
			public String getREFERENCE_NAME() {
				return REFERENCE_NAME;
			}
	
			public void setREFERENCE_NAME(String rEFERENCE_NAME) {
				REFERENCE_NAME = rEFERENCE_NAME;
			}
	
			public String getREFERENCE_DETAILS() {
				return REFERENCE_DETAILS;
			}
	
			public void setREFERENCE_DETAILS(String rEFERENCE_DETAILS) {
				REFERENCE_DETAILS = rEFERENCE_DETAILS;
			}
	
			public String getBOOKING_SERVICE_NAME() {
				return BOOKING_SERVICE_NAME;
			}
	
			public void setBOOKING_SERVICE_NAME(String bOOKING_SERVICE_NAME) {
				BOOKING_SERVICE_NAME = bOOKING_SERVICE_NAME;
			}
	
			public LocalDate getBOOKING_DATE() {
				return BOOKING_DATE;
			}
	
			public void setBOOKING_DATE(LocalDate bOOKING_DATE) {
				BOOKING_DATE = bOOKING_DATE;
			}
	
			public LocalDate getCONFIRMATION_DATE() {
				return CONFIRMATION_DATE;
			}
	
			public void setCONFIRMATION_DATE(LocalDate cONFIRMATION_DATE) {
				CONFIRMATION_DATE = cONFIRMATION_DATE;
			}
	
			public String  getBOOKING_STATUS() {
				return BOOKING_STATUS;
			}
			
	
			public void setBOOKING_STATUS(String bOOKING_STATUS) {
				BOOKING_STATUS = bOOKING_STATUS;
			}
	
			public String  getPAYMENT_STATUS() {
				return PAYMENT_STATUS;
			}
	
			public void setPAYMENT_STATUS(String  pAYMENT_STATUS) {
				PAYMENT_STATUS = pAYMENT_STATUS;
			}
	
			public String  getBOOKING_AMOUNT() {
				return BOOKING_AMOUNT;
			}
	
			public void setBOOKING_AMOUNT(String  bOOKING_AMOUNT) {
				BOOKING_AMOUNT = bOOKING_AMOUNT;
			}
	
			public double  getTOTAL_AMOUNT() {
				return TOTAL_AMOUNT;
			}
	
			public void setTOTAL_AMOUNT(@NotNull(message = "Booking date is required") double  tOTAL_AMOUNT) {
				TOTAL_AMOUNT = tOTAL_AMOUNT;
			}
	
			public String getADDRESS_LINE_1() {
				return ADDRESS_LINE_1;
			}
	
			public void setADDRESS_LINE_1(String aDDRESS_LINE_1) {
				ADDRESS_LINE_1 = aDDRESS_LINE_1;
			}
	
			public String getADDRESS_LINE_2() {
				return ADDRESS_LINE_2;
			}
	
			public void setADDRESS_LINE_2(String aDDRESS_LINE_2) {
				ADDRESS_LINE_2 = aDDRESS_LINE_2;
			}
	
			public String getADDRESS_LINE_3() {
				return ADDRESS_LINE_3;
			}
	
			public void setADDRESS_LINE_3(String aDDRESS_LINE_3) {
				ADDRESS_LINE_3 = aDDRESS_LINE_3;
			}
	
			public String getCITY() {
				return CITY;
			}
	
			public void setCITY(String cITY) {
				CITY = cITY;
			}
	
			public String getZIP_CODE() {
				return ZIP_CODE;
			}
	
			public void setZIP_CODE(String zIP_CODE) {
				ZIP_CODE = zIP_CODE;
			}
	
			public String getREMARKS() {
				return REMARKS;
			}
	
			public void setREMARKS(String rEMARKS) {
				REMARKS = rEMARKS;
			}
	
			public String getCREATED_BY() {
				return CREATED_BY;
			}
	
			public void setCREATED_BY(String cREATED_BY) {
				CREATED_BY = cREATED_BY;
			}
	
			public String getCREATED_DATE() {
				return CREATED_DATE;
			}
	
			public void setCREATED_DATE(String cREATED_DATE) {
				CREATED_DATE = cREATED_DATE;
			}
	
			public String getUPDATED_BY() {
				return UPDATED_BY;
			}
	
			public void setUPDATED_BY(String uPDATED_BY) {
				UPDATED_BY = uPDATED_BY;
			}
	
			public String getUPDATED_DATE() {
				return UPDATED_DATE;
			}
	
			public void setUPDATED_DATE(String uPDATED_DATE) {
				UPDATED_DATE = uPDATED_DATE;
			}
	
			
		   
	
	}
	
	    