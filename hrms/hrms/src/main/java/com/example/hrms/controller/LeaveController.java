package com.example.hrms.controller;
import com.example.hrms.dto.LeaveRequest;
import com.example.hrms.dto.LeaveResponse;
import com.example.hrms.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leaves")
public class LeaveController {
    private final LeaveService leaveService;
    @GetMapping
    public ResponseEntity<List<LeaveResponse>> getAllLeaves()
    {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }
    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponse> getLeave(@PathVariable Long id)
    {
        return ResponseEntity.ok(leaveService.getLeave(id));
    }
    @PutMapping("/approve/{id}")
    public ResponseEntity<LeaveResponse> approveLeave(@PathVariable Long id)
    {
        return ResponseEntity.ok(leaveService.approveLeave(id));
    }
    @PutMapping("/reject/{id}")
    public ResponseEntity<LeaveResponse> rejectLeave(@PathVariable Long id)
    {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }
    @PostMapping
    public ResponseEntity<LeaveResponse> applyLeave(@RequestBody LeaveRequest request)
    {
        return ResponseEntity.ok(leaveService.applyLeave(request));
    }
}
