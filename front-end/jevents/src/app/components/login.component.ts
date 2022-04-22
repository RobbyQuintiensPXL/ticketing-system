import {Observable} from 'rxjs';
import {AuthService} from '@auth0/auth0-angular';
import {HttpClient} from '@angular/common/http';
import {OnInit} from '@angular/core';

export class LoginComponent implements OnInit {

  constructor(private auth: AuthService, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.auth.loginWithRedirect();
  }
}
