package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.LoginRequest;
import com.arya_electric_auto.erp.dto.LoginResponse;
import com.arya_electric_auto.erp.entity.Employee;
import com.arya_electric_auto.erp.entity.User;
import com.arya_electric_auto.erp.repository.UserRepository;
import com.arya_electric_auto.erp.security.JwtUtil;
import com.arya_electric_auto.erp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       EmployeeRepository employeeRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByUsernameAndDeletedAtIsNull(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        if (user.getIsActive() != null && !user.getIsActive()) {
            throw new RuntimeException("User is inactive");
        }

        Employee emp = user.getEmployee();

        // 🔥 GENERATE TOKEN
        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole().name()
        );

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getRole().name(),
                emp.getId(),
                emp.getFullName(),
                token   // ✅ NEW
        );
    }
   
}