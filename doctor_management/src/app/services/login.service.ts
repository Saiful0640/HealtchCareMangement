import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = 'http://localhost:8080/api/v1/login';

  constructor(private http: HttpClient) { }

  loginService1(username: string, password:string):Observable<any>{
    return this.http.post<any>(`${this.loginUrl}`,{username,password});
  }
}
