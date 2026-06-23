import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css'
})
export class Sidebar implements OnInit {

  user: any = {};

 menuItems = [
  {
    id: 1,
    name: 'Dashboard',
    path: '/dashboard',
    icon: 'bi bi-house-fill'
  },
  {
    id: 2,
    name: 'Employees',
    path: '/employees',
    icon: 'bi bi-people-fill'
  },
  {
    id: 3,
    name: 'Attendance',
    path: '/attendance',
    icon: 'bi bi-calendar-check-fill'
  },
  {
    id: 4,
    name: 'Leaves',
    path: '/leaves',
    icon: 'bi bi-airplane-fill'
  },
  {
    id: 5,
    name: 'Payroll',
    path: '/payroll',
    icon: 'bi bi-cash-stack'
  }
];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user') || '{}');
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.router.navigate(['/']);
  }
}