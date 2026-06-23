package com.example.hrms.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class LeaveResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status;
}
