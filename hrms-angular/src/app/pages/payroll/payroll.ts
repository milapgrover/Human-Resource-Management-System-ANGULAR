import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Sidebar } from '../../components/sidebar/sidebar';
import { Navbar } from '../../components/navbar/navbar';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-payroll',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    Sidebar,
    Navbar
  ],
  templateUrl: './payroll.html',
  styleUrl: './payroll.css'
})
export class Payroll implements OnInit {

  private api = inject(ApiService);

  payrolls: any[] = [];
  employees: any[] = [];

  form = {
    employeeId: '',
    basicSalary: '',
    bonus: '',
    deduction: ''
  };

  ngOnInit(): void {
    this.fetchPayrolls();
    this.fetchEmployees();
  }

  fetchPayrolls() {
    this.api.getPayroll().subscribe({
      next: (data) => this.payrolls = data,
      error: (err) => console.error(err)
    });
  }

  fetchEmployees() {
    this.api.getEmployees().subscribe({
      next: (data) => this.employees = data,
      error: (err) => console.error(err)
    });
  }

  generatePayroll() {

    this.api.createPayroll({
      employeeId: Number(this.form.employeeId),
      basicSalary: Number(this.form.basicSalary),
      bonus: Number(this.form.bonus),
      deduction: Number(this.form.deduction)
    }).subscribe({
      next: () => {

        alert('Payroll Generated Successfully');

        this.form = {
          employeeId: '',
          basicSalary: '',
          bonus: '',
          deduction: ''
        };

        this.fetchPayrolls();
      },
      error: (err) => {
        console.error(err);
        alert('Failed To Generate Payroll');
      }
    });
  }

  getTotalBonus(): number {
    return this.payrolls.reduce(
      (sum, p) => sum + (p.bonus || 0),
      0
    );
  }

  getTotalSalary(): number {
    return this.payrolls.reduce(
      (sum, p) => sum + (p.netSalary || 0),
      0
    );
  }
}