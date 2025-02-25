package com.example.web_login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity     @Table(name = "USER_LOGIN_INFO")
public class User_login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "USER_LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "PHONE_NUMBER", length = 15)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "GENDER", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'M'")
    private char gender;

    @Column(name = "LAST_LOGIN")
    private java.sql.Timestamp lastLogin;

    @Column(name = "PROFILE_PICTURE", columnDefinition = "VARCHAR(255) DEFAULT 'default_profile.jpg'")
    private String profilePicture;

	@Override
	public String toString() {
		return "User_login [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", gender=" + gender + ", lastLogin=" + lastLogin
				+ ", profilePicture=" + profilePicture + ", getUserId()=" + getUserId() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getPhoneNumber()=" + getPhoneNumber()
				+ ", getEmail()=" + getEmail() + ", getGender()=" + getGender() + ", getLastLogin()=" + getLastLogin()
				+ ", getProfilePicture()=" + getProfilePicture() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public java.sql.Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(java.sql.Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
}
