package com.arya_electric_auto.erp.dto;
import java.time.LocalDateTime;

public class EmployeeResponse {

    private Long id;
    private String fullName;
    private String role;
    private String phone;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public EmployeeResponse(Long id,
                            String fullName,
                            String role,
                            String phone,
                            Boolean isActive,
                            LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
        this.phone = phone;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
    public String getPhone() { return phone; }
    public Boolean getIsActive() { return isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}