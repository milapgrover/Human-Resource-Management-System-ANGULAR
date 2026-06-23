package com.example.hrms.controller;
import com.example.hrms.dto.LeaveRequest;
import com.example.hrms.dto.LeaveResponse;
import com.example.hrms.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leaves")
public class LeaveController {
    private final LeaveService leaveService;
    @GetMapping
    public List<LeaveResponse> getAllLeaves()
    {
        return leaveService.getAllLeaves();
    }
    @GetMapping("/{id}")
    public LeaveResponse getLeave(@PathVariable Long id)
    {
        return leaveService.getLeave(id);
    }
    @PutMapping("/approve/{id}")
    public LeaveResponse approveLeave(@PathVariable Long id)
    {
        return leaveService.approveLeave(id);
    }
    @PutMapping("/reject/{id}")
    public LeaveResponse rejectLeave(@PathVariable Long id)
    {
        return leaveService.rejectLeave(id);
    }
    @PostMapping
    public LeaveResponse applyLeave(@RequestBody LeaveRequest request)
    {
        return leaveService.applyLeave(request);
    }
}
