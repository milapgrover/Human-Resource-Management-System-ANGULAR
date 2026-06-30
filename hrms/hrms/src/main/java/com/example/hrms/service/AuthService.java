package com.example.hrms.service;
import com.example.hrms.dto.LoginRequest;
import com.example.hrms.dto.LoginResponse;
import com.example.hrms.entity.Employee;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    public LoginResponse login(LoginRequest loginRequest)
    {
        Employee employee = employeeRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new RuntimeException("No such Email Exists"));
        if(!passwordEncoder.matches(loginRequest.getPassword() ,  employee.getPassword()))
        {
            throw new RuntimeException("PassWord is Invalid");
        }
        String token = jwtUtil.generateToken(employee.getEmail());
        return LoginResponse.builder()
                .token(token)
                .role(employee.getRole())
                .employeeId(employee.getId())
                .firstName(employee.getFirstName())
                .build();
    }
}
