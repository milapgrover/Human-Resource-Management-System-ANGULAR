import {
  Component,
  OnInit,
  inject,
  signal,
  computed
} from '@angular/core';

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
    password: ''
  };

  activeEmployees = computed(() =>

    this.employees().filter(
      employee => employee.active
    ).length

  );

  departmentCount = computed(() =>

    new Set(
      this.employees().map(
        employee => employee.department
      )
    ).size

  );

  ngOnInit(): void {

    this.fetchEmployees();

  }

  fetchEmployees(): void {

    this.api
      .getEmployees()
      .subscribe(data => {

        this.employees.set(data);

      });

  }

  addEmployee(): void {

    this.api
      .createEmployee({
        ...this.form,
        salary: Number(this.form.salary)
      })
      .subscribe(() => {

        alert(
          'Employee Added Successfully'
        );

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

      });

  }

  deleteEmployee(
    id: number
  ): void {

    this.api
      .deleteEmployee(id)
      .subscribe(() => {

        alert('Employee Deleted');

        this.fetchEmployees();

      });

  }

}