import {
  Component,
  OnInit,
  inject,
  signal
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';
import { Navbar } from '../../components/navbar/navbar';

@Component({
  selector: 'app-attendance',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    Navbar
],
  templateUrl: './attendance.html',
  styleUrl: './attendance.css'
})
export class Attendance implements OnInit{
  private api = inject(ApiService)
  attendance = signal<any[]>([]);
  employeeId = signal('');
  ngOnInit(): void {
    this.fetchAttendance();
  }
  fetchAttendance() : void
  {
    this.api.getAttendance().subscribe(data =>
    {
      this.attendance.set(data)
    }
    )
  }
  checkIn() : void
  {
    if(!this.employeeId())
    {
      alert('Enter Employee ID');
      return;
    }
    this.api.checkIn({
    employeeId : Number(this.employeeId())}).subscribe(() =>
    {
      alert('Check In Successful')
    
    this.employeeId.set("")
    this.fetchAttendance();
    })
  }
  checkOut(attendanceId : number) : void
  {
    this.api.checkOut({attendanceId}).subscribe(()=>{alert('Check Out Successful');
      this.fetchAttendance();
  })
  }
}