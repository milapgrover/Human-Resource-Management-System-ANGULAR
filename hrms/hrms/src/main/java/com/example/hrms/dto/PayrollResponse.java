package com.example.hrms.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class PayrollResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private Double basicSalary;
    private Double bonus;
    private Double deduction;
    private Double netSalary;
    private LocalDate generatedDate;
}