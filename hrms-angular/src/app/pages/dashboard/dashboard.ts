import { Component, OnInit, inject, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api';
import { Navbar } from '../../components/navbar/navbar';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, Navbar],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  private api = inject(ApiService);
  private router = inject(Router);

  employees = signal<any[]>([]);
  attendance = signal<any[]>([]);
  leaves = signal<any[]>([]);
  payrolls = signal<any[]>([]);

  loading = signal(false);
  error = signal(false);

  totalAttendance = computed(() => this.attendance().length);
  totalEmployees = computed(() => this.employees().length);
  totalLeaves = computed(() => this.leaves().length);
  totalPayrolls = computed(() => this.payrolls().length);

  ngOnInit(): void {
    const token = localStorage.getItem('token');

    if (!token) {
      this.router.navigate(['/']);
      return;
    }
    this.loadDashboard();
  }

  isAdmin(): boolean {
    return localStorage.getItem('role') === 'ADMIN';
  }

  goTo(url: string): void {
    this.router.navigate([url]);
  }

  loadDashboard(): void {
    this.loading.set(true);

    if (this.isAdmin()) {
      this.api.getEmployees().subscribe({
        next: (data) => {
          this.employees.set(data);
        },
        error: (err) => {
          console.error(err);
        },
      });

      this.api.getPayroll().subscribe({
        next: (data) => {
          this.payrolls.set(data);
        },
        error: (err) => {
          console.error(err);
        },
      });
    }


    this.api.getAttendance().subscribe({
      next: (data) => {
        this.attendance.set(data);
      },
      error: (err) => {
        console.error(err);
      },
    });

    this.api.getLeaves().subscribe({
      next: (data) => {
        this.leaves.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        console.error(err);
        this.loading.set(false);
      },
    });
  }
}