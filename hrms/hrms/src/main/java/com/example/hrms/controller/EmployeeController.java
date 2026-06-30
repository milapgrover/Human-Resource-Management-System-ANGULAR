package com.example.hrms.controller;
import com.example.hrms.dto.EmployeeRequest;
import com.example.hrms.dto.EmployeeResponse;
import com.example.hrms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees()
    {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id)
    {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest)
    {
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeRequest));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }
}
