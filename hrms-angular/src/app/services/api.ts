import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private http = inject(HttpClient);

  private baseUrl = 'http://localhost:8080';

  // Auth

  login(data: any) {
    return this.http.post(
      `${this.baseUrl}/auth/login`,
      data
    );
  }

  register(data: any) {
    return this.http.post(
      `${this.baseUrl}/auth/register`,
      data
    );
  }


// Payroll 

  getPayroll() {
    return this.http.get<any[]>(
      `${this.baseUrl}/payroll`
    );
  }

  createPayroll(data: any) {
    return this.http.post(
      `${this.baseUrl}/payroll`,
      data
    );
  }

  // Leave 

  getLeaves() {
  return this.http.get<any[]>(
    `${this.baseUrl}/leaves`
  );
}

createLeave(data: any) {
  return this.http.post(
    `${this.baseUrl}/leaves`,
    data
  );
}

approveLeave(id: number) {
  return this.http.put(
    `${this.baseUrl}/leaves/approve/${id}`,
    {}
  );
}

rejectLeave(id: number) {
  return this.http.put(
    `${this.baseUrl}/leaves/reject/${id}`,
    {}
  );
}

// Employee 

getEmployees() {
  return this.http.get<any[]>(
    `${this.baseUrl}/employees`
  );
}

createEmployee(data: any) {
  return this.http.post(
    `${this.baseUrl}/employees`,
    data
  );
}

deleteEmployee(id: number) {
  return this.http.delete(
    `${this.baseUrl}/employees/${id}`
  );
}


// Attendance 


getAttendance() {
  return this.http.get<any[]>(
    `${this.baseUrl}/attendance`
  );
}

checkIn(data: any) {
  return this.http.post(
    `${this.baseUrl}/attendance/checkin`,
    data
  );
}

checkOut(data: any) {
  return this.http.post(
    `${this.baseUrl}/attendance/checkout`,
    data
  );
}
}