package com.example.web_login.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_service_info")
public class Serevice_add {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")  // Corrected column name
    private Long serviceId;  

    @Column(name = "service_name")
    private String serviceName;
    
    @Column(name = "service_type")
    private String serviceType;
    
    @Column(name = "service_description")
    private String serviceDescription;
    
    @Column(name = "service_details")
    private String serviceDetails;
    
    @Column(name = "service_cost")
    private Double serviceCost;
    
    @Column(name = "active")
    private String active;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "create_date")  // Fixed spelling (was "crated_date")
    private String createDate;
    
    @Column(name = "updated_by")
    private String updatedBy;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "updated_date")
    private String updatedDate;
    
    @Column(name = "service_image_url")  // Fixed incorrect "sercice_image-url"
    private String serviceImageUrl;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "rating")
    private String rating;

    @Column(name = "rating_count")  // Corrected name (was "RATING-COUNT")
    private String ratingCount;

	@Override
	public String toString() {
		return "Serevice_add [serviceId=" + serviceId + ", serviceName=" + serviceName + ", serviceType=" + serviceType
				+ ", serviceDescription=" + serviceDescription + ", serviceDetails=" + serviceDetails + ", serviceCost="
				+ serviceCost + ", active=" + active + ", createdBy=" + createdBy + ", createDate=" + createDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", serviceImageUrl=" + serviceImageUrl
				+ ", remarks=" + remarks + ", rating=" + rating + ", ratingCount=" + ratingCount + ", getServiceId()="
				+ getServiceId() + ", getServiceName()=" + getServiceName() + ", getServiceType()=" + getServiceType()
				+ ", getServiceDescription()=" + getServiceDescription() + ", getServiceDetails()="
				+ getServiceDetails() + ", getServiceCost()=" + getServiceCost() + ", getActive()=" + getActive()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getCreateDate()=" + getCreateDate() + ", getUpdatedBy()="
				+ getUpdatedBy() + ", getUpdatedDate()=" + getUpdatedDate() + ", getServiceImageUrl()="
				+ getServiceImageUrl() + ", getRemarks()=" + getRemarks() + ", getRating()=" + getRating()
				+ ", getRatingCount()=" + getRatingCount() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public Double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getServiceImageUrl() {
		return serviceImageUrl;
	}

	public void setServiceImageUrl(String serviceImageUrl) {
		this.serviceImageUrl = serviceImageUrl;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(String ratingCount) {
		this.ratingCount = ratingCount;
	}
}
