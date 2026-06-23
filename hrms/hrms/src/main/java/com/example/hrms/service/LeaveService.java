package com.example.hrms.service;
import com.example.hrms.dto.LeaveRequest;
import com.example.hrms.dto.LeaveResponse;
import com.example.hrms.entity.Employee;
import com.example.hrms.entity.Leave;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    public LeaveResponse applyLeave(LeaveRequest request)
    {
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(()->new RuntimeException("Employee not found"));
        Leave leave  = Leave.builder()
                .employee(employee)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .reason(request.getReason())
                .status("PENDING")
                .build();
        leaveRepository.save(leave);
        return convertToResponse(leave);
    }
    public LeaveResponse approveLeave(Long id)
    {
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus("APPROVED");
        leaveRepository.save(leave);
        return convertToResponse(leave);
    }
    public LeaveResponse rejectLeave(Long id)
    {
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus("REJECT");
        leaveRepository.save(leave);
        return convertToResponse(leave);
    }
    public LeaveResponse convertToResponse(Leave leave)
    {
        return LeaveResponse.builder()
                .id(leave.getId())
                .employeeId(
                        leave.getEmployee().getId())
                .employeeName(
                        leave.getEmployee().getFirstName()
                                + " "
                                + leave.getEmployee().getLastName())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .build();
    }
    public List<LeaveResponse> getAllLeaves()
    {
        return leaveRepository.findAll().stream().map(this::convertToResponse).toList();
    }
    public LeaveResponse getLeave(Long id)
    {
        Leave leave = leaveRepository.findById(id).orElseThrow(()->new RuntimeException("Leave not found"));
        return convertToResponse(leave);
    }
}
