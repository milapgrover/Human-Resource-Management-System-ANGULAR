import { Component, OnInit, inject } from '@angular/core';
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

  leaves: any[] = [];
  employees: any[] = [];

  form = {
    employeeId: '',
    startDate: '',
    endDate: '',
    reason: ''
  };

  ngOnInit(): void {
    this.fetchLeaves();
    this.fetchEmployees();
  }

  fetchLeaves() {
    this.api.getLeaves().subscribe({
      next: (data) => this.leaves = data,
      error: (err) => console.error(err)
    });
  }

  fetchEmployees() {
    this.api.getEmployees().subscribe({
      next: (data) => this.employees = data,
      error: (err) => console.error(err)
    });
  }

  applyLeave() {

    this.api.createLeave({
      employeeId: Number(this.form.employeeId),
      startDate: this.form.startDate,
      endDate: this.form.endDate,
      reason: this.form.reason
    }).subscribe({
      next: () => {

        alert('Leave Applied Successfully');

        this.form = {
          employeeId: '',
          startDate: '',
          endDate: '',
          reason: ''
        };

        this.fetchLeaves();
      },
      error: (err) => {
        console.error(err);
        alert('Failed To Apply Leave');
      }
    });
  }

  approveLeave(id: number) {
    this.api.approveLeave(id).subscribe({
      next: () => this.fetchLeaves(),
      error: (err) => console.error(err)
    });
  }

  rejectLeave(id: number) {
    this.api.rejectLeave(id).subscribe({
      next: () => this.fetchLeaves(),
      error: (err) => console.error(err)
    });
  }

  approvedCount() {
    return this.leaves.filter(
      l => l.status === 'APPROVED'
    ).length;
  }

  pendingCount() {
    return this.leaves.filter(
      l => l.status === 'PENDING'
    ).length;
  }
}