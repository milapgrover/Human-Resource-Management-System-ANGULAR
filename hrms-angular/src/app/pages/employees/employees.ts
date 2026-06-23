import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Sidebar } from '../../components/sidebar/sidebar';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-employees',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    Sidebar
  ],
  templateUrl: './employees.html',
  styleUrl: './employees.css'
})
export class Employees implements OnInit {

  private api = inject(ApiService);

  employees: any[] = [];

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
    password: ''
  };

  ngOnInit(): void {
    this.fetchEmployees();
  }

  fetchEmployees() {
    this.api.getEmployees().subscribe({
      next: (data) => this.employees = data,
      error: (err) => console.error(err)
    });
  }

  addEmployee() {

    this.api.createEmployee({
      ...this.form,
      salary: Number(this.form.salary)
    }).subscribe({
      next: () => {

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
          password: ''
        };

        this.fetchEmployees();
      },
      error: (err) => {
        console.error(err);
        alert('Failed To Add Employee');
      }
    });
  }

  deleteEmployee(id: number) {

    this.api.deleteEmployee(id).subscribe({
      next: () => {
        alert('Employee Deleted');
        this.fetchEmployees();
      },
      error: (err) => {
        console.error(err);
        alert('Delete Failed');
      }
    });
  }

  activeEmployees() {
    return this.employees.filter(
      e => e.active
    ).length;
  }

  departmentCount() {
    return new Set(
      this.employees.map(
        e => e.department
      )
    ).size;
  }
}