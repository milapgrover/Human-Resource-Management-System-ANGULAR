package com.example.hrms.controller;
import com.example.hrms.dto.PayrollRequest;
import com.example.hrms.dto.PayrollResponse;
import com.example.hrms.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payroll")
public class PayrollController {
    private final PayrollService payrollService;
    @GetMapping
    public List<PayrollResponse> getAllPayrolls()
    {
        return payrollService.getAllPayroll();
    }
    @GetMapping("/{id}")
    public PayrollResponse getPayroll(@PathVariable Long id)
    {
        return payrollService.getPayrollById(id);
    }
    @PostMapping
    public PayrollResponse generatePayroll(@RequestBody PayrollRequest payrollRequest)
    {
        return payrollService.generatePayroll(payrollRequest);
    }
}
