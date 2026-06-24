import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (
  req,
  next
) => {

  const publicUrls = [
    '/auth/login',
    '/auth/register'
  ];

  const isPublicUrl =
    publicUrls.some(url =>
      req.url.includes(url)
    );

  if (!isPublicUrl) {

    const token =
      typeof localStorage !== 'undefined'
        ? localStorage.getItem('token')
        : null;

    if (token) {

      req = req.clone({
        setHeaders: {
          Authorization:
            `Bearer ${token}`
        }
      });

    }

  }

  return next(req);

};