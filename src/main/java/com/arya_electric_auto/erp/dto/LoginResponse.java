package com.arya_electric_auto.erp.dto;

public class LoginResponse {

    private Long userId;
    private String username;
    private String role;

    private Long employeeId;
    private String employeeName;
    private String token;

    public LoginResponse(Long userId, String username, String role,
                         Long employeeId, String employeeName, String token) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.token = token;
    }

    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public Long getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
