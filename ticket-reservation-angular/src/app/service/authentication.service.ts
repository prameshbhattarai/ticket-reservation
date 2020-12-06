import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  public currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  login(username, password) {
    return this.http.post<any>(`http://localhost:8080/login`, { username, password },
      {
        observe: 'response'
      }
      ).pipe(
        map((response) => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('token', response.headers.get('Authorization'));
        localStorage.setItem('currentUser', JSON.stringify(response.body));
        this.currentUserSubject.next(response.body);
        return response;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
    this.currentUserSubject.next(null);
  }
}
