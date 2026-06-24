import {
  Component,
  OnInit,
  inject,
  signal,
  computed
} from '@angular/core';

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

  employees = signal<any[]>([]);

  attendance = signal<any[]>([]);

  leaves = signal<any[]>([]);

  payrolls = signal<any[]>([]);

  loading = signal(false);

  error = signal('');

  totalEmployees = computed(() =>

    this.employees().length

  );

  totalAttendance = computed(() =>

    this.attendance().length

  );

  totalLeaves = computed(() =>

    this.leaves().length

  );

  totalPayrolls = computed(() =>

    this.payrolls().length

  );

  ngOnInit(): void {

    const token =
      typeof localStorage !== 'undefined'
        ? localStorage.getItem('token')
        : null;

    if (!token) {

      this.router.navigate(['/']);

      return;

    }

    this.loadDashboard();

  }

  loadDashboard(): void {

    this.loading.set(true);

    this.api
      .getEmployees()
      .subscribe(data => {

        this.employees.set(data);

      });

    this.api
      .getAttendance()
      .subscribe(data => {

        this.attendance.set(data);

      });

    this.api
      .getLeaves()
      .subscribe(data => {

        this.leaves.set(data);

      });

    this.api
      .getPayroll()
      .subscribe(data => {

        this.payrolls.set(data);

        this.loading.set(false);

      });

  }

  goTo(
    url: string
  ): void {

    this.router.navigate([url]);

  }

}