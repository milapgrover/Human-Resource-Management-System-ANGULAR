import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/login/login').then(m => m.Login)
  },
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./pages/dashboard/dashboard').then(m => m.Dashboard)
  },
  {
    path: 'employees',
    loadComponent: () =>
      import('./pages/employees/employees').then(m => m.Employees)
  },
  {
    path: 'attendance',
    loadComponent: () =>
      import('./pages/attendance/attendance').then(m => m.Attendance)
  },
  {
    path: 'leaves',
    loadComponent: () =>
      import('./pages/leaves/leaves').then(m => m.Leaves)
  },
  {
    path: 'payroll',
    loadComponent: () =>
      import('./pages/payroll/payroll').then(m => m.Payroll)
  },
  {
    path: '**',
    redirectTo: ''
  }
];