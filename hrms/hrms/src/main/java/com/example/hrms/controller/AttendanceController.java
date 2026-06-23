package com.example.hrms.controller;
import com.example.hrms.dto.AttendanceRequest;
import com.example.hrms.dto.AttendanceResponse;
import com.example.hrms.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    @PostMapping("/checkin")
    public AttendanceResponse checkIn(@RequestBody AttendanceRequest request) {
        return attendanceService.checkIn(request);
    }
    @PostMapping("/checkout")
    public AttendanceResponse checkOut(@RequestBody AttendanceRequest request) {
        return attendanceService.checkOut(request.getAttendanceId());
    }
    @GetMapping
    public List<AttendanceResponse> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }
    @GetMapping("/{id}")
    public AttendanceResponse getAttendance(@PathVariable Long id) {
        return attendanceService.getAttendance(id);
    }
}