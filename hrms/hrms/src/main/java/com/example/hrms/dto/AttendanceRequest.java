package com.example.hrms.dto;
import lombok.Data;

@Data
public class AttendanceRequest {
    private Long employeeId;
    private Long attendanceId;
}