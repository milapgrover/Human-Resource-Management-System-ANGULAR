package com.example.hrms.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequest {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
