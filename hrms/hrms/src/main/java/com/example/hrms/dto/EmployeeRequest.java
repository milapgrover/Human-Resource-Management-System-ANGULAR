package com.example.hrms.dto;
import lombok.Data;

@Data
public class EmployeeRequest {
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String designation;
    private Double salary;
    private String role;
    private String password;
}
