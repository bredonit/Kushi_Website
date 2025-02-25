package com.example.web_login.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "tbl_offer_image_info")
public class Offer_image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;  // Service ID (Auto-generated)

    @Column(name = "offer_image_url")  
    private String offerImageUrl; 
    @Column(name = "offer_image_name")
// Renamed to follow Java conventions
    private String offerImageName; // Renamed to follow Java conventions
	@Override
	public String toString() {
		return "Offer_image [serviceId=" + serviceId + ", offerImageUrl=" + offerImageUrl + ", offerImageName="
				+ offerImageName + ", getServiceId()=" + getServiceId() + ", getOfferImageUrl()=" + getOfferImageUrl()
				+ ", getOfferImageName()=" + getOfferImageName() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getOfferImageUrl() {
		return offerImageUrl;
	}
	public void setOfferImageUrl(String offerImageUrl) {
		this.offerImageUrl = offerImageUrl;
	}
	public String getOfferImageName() {
		return offerImageName;
	}
	public void setOfferImageName(String offerImageName) {
		this.offerImageName = offerImageName;
	}
}
