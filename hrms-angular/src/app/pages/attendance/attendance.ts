import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Sidebar } from '../../components/sidebar/sidebar';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-attendance',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    Sidebar
  ],
  templateUrl: './attendance.html',
  styleUrl: './attendance.css'
})
export class Attendance implements OnInit {

  private api = inject(ApiService);

  attendance: any[] = [];
  employeeId = '';

  ngOnInit(): void {
    this.fetchAttendance();
  }

  fetchAttendance() {
    this.api.getAttendance().subscribe({
      next: (data) => this.attendance = data,
      error: (err) => console.error(err)
    });
  }

  checkIn() {

    if (!this.employeeId) {
      alert('Enter Employee ID');
      return;
    }

    this.api.checkIn({
      employeeId: Number(this.employeeId)
    }).subscribe({
      next: () => {

        alert('Check In Successful');

        this.employeeId = '';

        this.fetchAttendance();
      },
      error: (err) => {
        console.error(err);
        alert('Check In Failed');
      }
    });
  }

  checkOut(attendanceId: number) {

    this.api.checkOut({
      attendanceId: attendanceId
    }).subscribe({
      next: () => {

        alert('Check Out Successful');

        this.fetchAttendance();
      },
      error: (err) => {
        console.error(err);
        alert('Check Out Failed');
      }
    });
  }
}