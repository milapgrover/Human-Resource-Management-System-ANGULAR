package com.example.hrms.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private Double basicSalary;
    private Double bonus;
    private Double deduction;
    private Double netSalary;
    private LocalDate generatedDate;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
