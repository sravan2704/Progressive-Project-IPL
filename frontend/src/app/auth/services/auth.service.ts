import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map, Observable } from "rxjs";
import { User } from "../../ipl/types/User";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn:'root'
})

export class AuthService {


  private loginUrl  = `${environment.apiUrl}`
  constructor(private http: HttpClient) {}

  login(user: Partial<User>): Observable<{ [key: string]: string }> {
    
    return this.http.post<{token:string}>(`${this.loginUrl}/user/login`,user);
  }

  getToken() : string {
    return localStorage.getItem('token') || '';
  }

  getRole() : string {
    return localStorage.getItem('role') || '';
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.loginUrl}/user`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.loginUrl}/user/register`,user);
  }
}
