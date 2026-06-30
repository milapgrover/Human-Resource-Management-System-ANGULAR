import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  const publicUrls = ['/auth/login', '/auth/register'];

  const isPublicUrl = publicUrls.some((url) => req.url.includes(url));

  if (!isPublicUrl) {
    const token =
      typeof localStorage !== 'undefined'
        ? localStorage.getItem('token')
        : null;
    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    } else {
      router.navigate(['/']);
    }
  }
  return next(req);
};
