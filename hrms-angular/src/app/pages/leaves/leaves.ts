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
import { Navbar } from '../../components/navbar/navbar';

import { ApiService } from '../../services/api';

@Component({
  selector: 'app-leaves',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    Sidebar,
    Navbar
],
  templateUrl: './leaves.html',
  styleUrl: './leaves.css'
})
export class Leaves implements OnInit {

  private api = inject(ApiService);

  leaves = signal<any[]>([]);

  employees = signal<any[]>([]);

  form = {
    employeeId: '',
    startDate: '',
    endDate: '',
    reason: ''
  };

  approvedCount = computed(() =>

    this.leaves().filter(
      leave =>
        leave.status === 'APPROVED'
    ).length

  );

  pendingCount = computed(() =>

    this.leaves().filter(
      leave =>
        leave.status === 'PENDING'
    ).length

  );

  ngOnInit(): void {

    this.fetchLeaves();

    this.fetchEmployees();

  }

  fetchLeaves(): void {

    this.api
      .getLeaves()
      .subscribe(data => {

        this.leaves.set(data);

      });

  }

  fetchEmployees(): void {

    this.api
      .getEmployees()
      .subscribe(data => {

        this.employees.set(data);

      });

  }

  applyLeave(): void {

    this.api
      .createLeave({
        employeeId:
          Number(
            this.form.employeeId
          ),
        startDate:
          this.form.startDate,
        endDate:
          this.form.endDate,
        reason:
          this.form.reason
      })
      .subscribe(() => {

        alert(
          'Leave Applied Successfully'
        );

        this.form = {
          employeeId: '',
          startDate: '',
          endDate: '',
          reason: ''
        };

        this.fetchLeaves();

      });

  }

  approveLeave(
    id: number
  ): void {

    this.api
      .approveLeave(id)
      .subscribe(() => {

        this.fetchLeaves();

      });

  }

  rejectLeave(
    id: number
  ): void {

    this.api
      .rejectLeave(id)
      .subscribe(() => {

        this.fetchLeaves();

      });

  }

}