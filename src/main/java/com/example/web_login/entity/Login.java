package com.example.web_login.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // User ID

    @Column(nullable = false, unique = true)
    private String adminId; // Unique Admin ID

    @Column(nullable = false)
    private String adminName; // Admin Name

    @Column(nullable = false, unique = true)
    private String adminUsername; // Admin Username

    @Column(nullable = false, unique = true)
    private String email; // Admin Email

    @Column(nullable = false)
    private String password; // Admin Password

    @Column(nullable = false)
    private String phoneNumber; // Admin Phone Number

    @Column(nullable = true)
    private String profilePicture; // Profile Picture (URL or Path)

    public String getEmail() {
        return this.email;
    }

    public Object getPassword() {
        return this.password;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

		
}
