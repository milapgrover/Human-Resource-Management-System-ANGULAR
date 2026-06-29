package com.example.hrms.service;
import com.example.hrms.dto.PayrollRequest;
import com.example.hrms.dto.PayrollResponse;
import com.example.hrms.entity.Employee;
import com.example.hrms.entity.Payroll;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollService {
    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;
    public PayrollResponse generatePayroll(PayrollRequest request)
    {
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(()->new RuntimeException("Employee not found"));
       double netSalary = request.getBasicSalary() + request.getBonus() - request.getDeduction();
        Payroll payroll = Payroll.builder()
                .employee(employee)
                .basicSalary(request.getBasicSalary())
                .bonus(request.getBonus())
                .deduction(request.getDeduction())
                .netSalary(netSalary)
                .generatedDate(LocalDate.now())
                .build();
        payrollRepository.save(payroll);
        return convertToResponse(payroll);
    }
    private PayrollResponse convertToResponse(Payroll payroll)
    {
        return PayrollResponse.builder()
                .id(payroll.getId())
                .employeeId(payroll.getEmployee().getId())
                .employeeName(payroll.getEmployee().getFirstName() + " " + payroll.getEmployee().getLastName())
                .basicSalary(payroll.getBasicSalary())
                .bonus(payroll.getBonus())
                .deduction(payroll.getDeduction())
                .netSalary(payroll.getNetSalary())
                .generatedDate(payroll.getGeneratedDate())
                .build();
    }
    public List<PayrollResponse> getAllPayroll()
    {
        return payrollRepository.findAll().stream().map(this::convertToResponse).toList();
    }
    public PayrollResponse getPayrollById(Long id)
    {
        Payroll payroll = payrollRepository.findById(id).orElseThrow(()->new RuntimeException("Payroll not found"));
        return convertToResponse(payroll);
    }
}
