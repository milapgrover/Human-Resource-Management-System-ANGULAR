package com.example.hrms.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name= "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String employeeCode;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String department;
    private String designation;
    private Double salary;
    private LocalDate joiningDate;
    private String role;
    private String password;
    private boolean active;
    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendance;
    @OneToMany(mappedBy = "employee")
    private List<Leave> leaves;
}
