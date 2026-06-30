import { Component, OnInit, inject, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ApiService } from '../../services/api';
import { Navbar } from '../../components/navbar/navbar';

@Component({
  selector: 'app-employees',
  standalone: true,
  imports: [CommonModule, FormsModule, Navbar],
  templateUrl: './employees.html',
  styleUrl: './employees.css',
})
export class Employees implements OnInit {
  private api = inject(ApiService);

  employees = signal<any[]>([]);

  form = {
    employeeCode: '',
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    department: '',
    designation: '',
    salary: '',
    role: '',
    password: '',
  };

  activeEmployees = computed(
    () => this.employees().filter((employee) => employee.active).length,
  );

  departmentCount = computed(
    () => new Set(this.employees().map((employee) => employee.department)).size,
  );

  ngOnInit(): void {
    this.fetchEmployees();
  }

  isAdmin(): boolean {
    return localStorage.getItem('role') === 'ADMIN';
  }

  fetchEmployees(): void {
    this.api.getEmployees().subscribe((data) => {
      this.employees.set(data);
    });
  }

  addEmployee(): void {
    this.api
      .createEmployee({
        ...this.form,
        salary: Number(this.form.salary),
      })
      .subscribe(() => {
        alert('Employee Added Successfully');

        this.form = {
          employeeCode: '',
          firstName: '',
          lastName: '',
          email: '',
          phoneNumber: '',
          department: '',
          designation: '',
          salary: '',
          role: '',
          password: '',
        };

        this.fetchEmployees();
      });
  }

  deleteEmployee(id: number): void {
    this.api.deleteEmployee(id).subscribe(() => {
      alert('Employee Deleted');
      this.fetchEmployees();
    });
  }
}
