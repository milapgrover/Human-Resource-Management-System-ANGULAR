package com.example.hrms.service;
import com.example.hrms.dto.AttendanceRequest;
import com.example.hrms.dto.AttendanceResponse;
import com.example.hrms.entity.Attendance;
import com.example.hrms.entity.Employee;
import com.example.hrms.repository.AttendanceRepository;
import com.example.hrms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private AttendanceResponse convertToResponse(Attendance attendance)
    {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .employeeName(attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName())
                .attendanceDate(attendance.getAttendanceDate())
                .checkInTime(attendance.getCheckInTime())
                .checkOutTime(attendance.getCheckOutTime())
                .workingHours(attendance.getWorkingHours())
            .build();
    }

    public List<AttendanceResponse> getAllAttendance()
    {
        return attendanceRepository.findAll().stream().map(this::convertToResponse).toList();
    }
    public AttendanceResponse getAttendance(Long id)
    {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow(()->new RuntimeException("Attendance not found"));
        return convertToResponse(attendance);
    }
    public AttendanceResponse checkIn(AttendanceRequest attendanceRequest)
    {
        Employee employee = employeeRepository.findById(attendanceRequest.getEmployeeId()).orElseThrow(()->new RuntimeException("Employee does not Exists"));
        Attendance attendance = Attendance.builder()
                .employee(employee).attendanceDate(LocalDate.now()).checkInTime(LocalDateTime.now()).build();
        attendanceRepository.save(attendance);
        return convertToResponse(attendance);
    }
    public AttendanceResponse checkOut(Long attendanceId)
    {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(()->new RuntimeException("Employee Does not check In"));
        attendance.setCheckOutTime(LocalDateTime.now());
        double hours = Duration.between(attendance.getCheckInTime() , attendance.getCheckOutTime()).toHours();
        attendance.setWorkingHours(hours);
        attendanceRepository.save(attendance);
        return convertToResponse(attendance);
    }
}
