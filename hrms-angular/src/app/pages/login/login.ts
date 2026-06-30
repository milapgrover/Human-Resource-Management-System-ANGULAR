import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private router = inject(Router);
  private api = inject(ApiService);

  email = '';
  password = '';
  loading = false;

  login(): void {
    if (!this.email || !this.password) {
      alert('Please enter Email and Password');
      return;
    }

    this.loading = true;

    this.api
      .login({
        email: this.email,
        password: this.password,
      })
      .subscribe({
        next : (response: any) => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role);
          localStorage.setItem('employeeId', response.employeeId.toString());
          localStorage.setItem('firstName', response.firstName);

          alert('Login Successful');

          this.router.navigate(['/dashboard']);
          this.loading = false;
        },

        error: (error) => {
          console.error(error);
          alert('Invalid Email or Password');
          this.loading = false;
        },
      });
  }

  handleKeyPress(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.login();
    }
  }
}
