package com.example.hrms.service;
import com.example.hrms.dto.EmployeeRequest;
import com.example.hrms.dto.EmployeeResponse;
import com.example.hrms.entity.Employee;
import com.example.hrms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private EmployeeResponse convertToResponse(Employee employee)
    {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .employeeCode(employee.getEmployeeCode())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .joiningDate(employee.getJoiningDate())
                .role(employee.getRole())
                .active(employee.isActive())
                .build();
    }
    public EmployeeResponse createEmployee(EmployeeRequest request)
    {
        Employee employee = Employee.builder()
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .salary(request.getSalary())
                .role(request.getRole())
                .joiningDate(LocalDate.now())
                .active(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        employee  = employeeRepository.save(employee);
        return convertToResponse(employee);
    }
    public String deleteEmployee(Long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee not Found"));
        employeeRepository.delete(employee);
        return  "Employee deleted Sucessfully";
    }
    public EmployeeResponse updateEmployee(Long id , EmployeeRequest employeeRequest)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee Not Found"));
        employee.setEmployeeCode(employeeRequest.getEmployeeCode());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setDesignation(employeeRequest.getDesignation());
        employee.setSalary(employeeRequest.getSalary());
        employee = employeeRepository.save(employee);
        return convertToResponse(employee);
    }
    public List<EmployeeResponse> getAllEmployees()
    {
        return employeeRepository.findAll().stream().map(this :: convertToResponse).toList();
    }
    public EmployeeResponse getEmployee(Long id)
    {
         Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee Does Not Exists"));
        return convertToResponse(employee);
    }
}
