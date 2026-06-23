package com.example.hrms.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String designation;
    private Double salary;
    private LocalDate joiningDate;
    private String role;
    private boolean active;
}
