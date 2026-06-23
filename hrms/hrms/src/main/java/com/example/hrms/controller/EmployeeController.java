package com.example.hrms.controller;
import com.example.hrms.dto.EmployeeRequest;
import com.example.hrms.dto.EmployeeResponse;
import com.example.hrms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping
    public List<EmployeeResponse> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable Long id)
    {
        return employeeService.getEmployee(id);
    }
    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest request)
    {
        return employeeService.createEmployee(request);
    }
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id , @RequestBody EmployeeRequest request)
    {
        return employeeService.updateEmployee(id , request);
    }
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id)
    {
        return employeeService.deleteEmployee(id);
    }
}
