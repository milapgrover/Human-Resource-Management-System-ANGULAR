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
    @GetMapping
    public List<AttendanceResponse> getAllAttendance()
    {
        return attendanceService.getAllAttendance();
    }
    @GetMapping("/{id}")
    public AttendanceResponse getAttendanceById(@PathVariable Long id)
    {
        return attendanceService.getAttendance(id);
    }
    @PostMapping("/checkIn")
    public AttendanceResponse checkIn(@RequestBody AttendanceRequest attendanceRequest)
    {
        return attendanceService.checkIn(attendanceRequest);
    }
    @PostMapping("/checkOut")
    public AttendanceResponse checkOut(@RequestBody AttendanceRequest attendanceRequest)
    {
        return attendanceService.checkOut(attendanceRequest.getAttendanceId());
    }
}