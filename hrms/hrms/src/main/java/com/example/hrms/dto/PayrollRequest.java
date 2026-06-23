package com.example.hrms.dto;
import lombok.Data;

@Data
public class PayrollRequest {
    private Long employeeId;
    private Double basicSalary;
    private Double bonus;
    private Double deduction;
}