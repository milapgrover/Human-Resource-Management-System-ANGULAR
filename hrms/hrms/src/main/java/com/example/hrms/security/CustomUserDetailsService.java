package com.example.hrms.security;

import com.example.hrms.entity.Employee;
import com.example.hrms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Employee employee = employeeRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"));

        return User.builder()
                .username(employee.getEmail())
                .password(employee.getPassword())
                .authorities(
                        List.of(
                                new SimpleGrantedAuthority(
                                        "ROLE_" +
                                                employee.getRole())))
                .build();
    }
}