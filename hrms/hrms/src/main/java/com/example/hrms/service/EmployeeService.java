package com.example.hrms.service;
import com.example.hrms.dto.EmployeeRequest;
import com.example.hrms.dto.EmployeeResponse;
import com.example.hrms.entity.Employee;
import com.example.hrms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    public EmployeeResponse createEmployee(EmployeeRequest request)
    {
        Employee employee = Employee.builder()
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .salary(request.getSalary())
                .role(request.getRole())
                .joiningDate(LocalDate.now())
                .active(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        return convertToResponse(repository.save(employee));
    }
    private EmployeeResponse convertToResponse(Employee employee)
    {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .employeeCode(employee.getEmployeeCode())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .joiningDate(employee.getJoiningDate())
                .role(employee.getRole())
                .active(employee.isActive())
                .build();
    }
    public List<EmployeeResponse> getAllEmployees()
    {
        return repository.findAll().stream().map(this::convertToResponse).toList();
    }
    public EmployeeResponse getEmployee(Long id)
    {
        Employee employee = repository.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found"));
        return convertToResponse(employee);
    }
    public EmployeeResponse updateEmployee(Long id , EmployeeRequest request)
    {
        Employee employee = repository.findById(id).orElseThrow(()-> new RuntimeException("Employee not found"));
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        repository.save(employee);
        return convertToResponse(employee);
    }
    public String deleteEmployee(Long id)
    {
        Employee employee = repository.findById(id).orElseThrow(()->new RuntimeException("Employee not Found"));
        repository.delete(employee);
        return "Employee deleted successfully";
    }
}
