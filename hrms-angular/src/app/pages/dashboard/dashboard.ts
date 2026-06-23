import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { Sidebar } from '../../components/sidebar/sidebar';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    Sidebar
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  private api = inject(ApiService);
  private router = inject(Router);

  employees: any[] = [];
  attendance: any[] = [];
  leaves: any[] = [];
  payrolls: any[] = [];

  loading = false;
  error = '';

  ngOnInit(): void {

    const token = localStorage.getItem('token');

    if (!token) {
      this.router.navigate(['/']);
      return;
    }

    this.loadDashboard();
  }

  loadDashboard() {

    this.loading = true;

    this.api.getEmployees().subscribe({
      next: (data) => this.employees = data,
      error: (err) => console.error(err)
    });

    this.api.getAttendance().subscribe({
      next: (data) => this.attendance = data,
      error: (err) => console.error(err)
    });

    this.api.getLeaves().subscribe({
      next: (data) => this.leaves = data,
      error: (err) => console.error(err)
    });

    this.api.getPayroll().subscribe({
      next: (data) => {
        this.payrolls = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
      }
    });
  }

  goTo(url: string) {
    this.router.navigate([url]);
  }
}