package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;



@Entity
@Table(name = "users")
public class User extends BaseEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    // 🔗 FK mapping (correct)
	    @ManyToOne
	    @JoinColumn(name = "employee_id", nullable = false)
	    private Employee employee;

	    // 📊 ENUM
	    @Enumerated(EnumType.STRING)
	    @Column(name = "role")
	    private Role role;

	    // 🔘 active flag
	    @Column(name = "is_active")
	    private Boolean isActive;

	    // ⏱ last_login mapping FIXED
	    @Column(name = "last_login")
	    private LocalDateTime lastLogin;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public Boolean getIsActive() {
			return isActive;
		}

		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}

		public LocalDateTime getLastLogin() {
			return lastLogin;
		}

		public void setLastLogin(LocalDateTime lastLogin) {
			this.lastLogin = lastLogin;
		}

	    // getters/setters
	    
	    
	}
    
    
    
