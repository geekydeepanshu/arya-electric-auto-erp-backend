package com.arya_electric_auto.erp.dto;

public class LoginResponse {

    private Long userId;
    private String username;
    private String role;

    private Long employeeId;
    private String employeeName;

    public LoginResponse(Long userId, String username, String role,
                         Long employeeId, String employeeName) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public Long getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
}
