import {
  Component,
  OnInit,
  inject,
  signal,
  computed
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Navbar } from '../../components/navbar/navbar';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-payroll',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    Navbar
  ],
  templateUrl: './payroll.html',
  styleUrl: './payroll.css'
})
export class Payroll implements OnInit {

  private api = inject(ApiService);
  payrolls = signal<any[]>([]);
  employees = signal<any[]>([]);
  form = {
    employeeId: '',
    basicSalary: '',
    bonus: '',
    deduction: ''
  };


  totalBonus = computed(() =>

    this.payrolls().reduce(
      (sum, payroll) =>
        sum + (payroll.bonus || 0),
      0
    )

  );

  totalSalary = computed(() =>

    this.payrolls().reduce(
      (sum, payroll) =>
        sum + (payroll.netSalary || 0),
      0
    )

  );

  ngOnInit(): void {
    this.fetchPayrolls();
    this.fetchEmployees();

  }

  fetchPayrolls(): void {

    this.api
      .getPayroll()
      .subscribe(data => {

        this.payrolls.set(data);

      });

  }

  fetchEmployees(): void {

    this.api
      .getEmployees()
      .subscribe(data => {

        this.employees.set(data);

      });

  }

  generatePayroll(): void {

    this.api
      .createPayroll({
        employeeId:
          Number(this.form.employeeId),

        basicSalary:
          Number(this.form.basicSalary),

        bonus:
          Number(this.form.bonus),

        deduction:
          Number(this.form.deduction)
      })
      .subscribe(() => {

        alert(
          'Payroll Generated Successfully'
        );

        this.form = {
          employeeId: '',
          basicSalary: '',
          bonus: '',
          deduction: ''
        };

        this.fetchPayrolls();

      });

  }

}