import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from '@auth0/auth0-angular';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  publicString: Observable<string>;

  constructor(private auth: AuthService, private http: HttpClient) {
  }

  loginWithRedirect(): void {
    this.auth.loginWithRedirect();
  }

  request(): void {
    this.publicString = this.http.get<string>('http://localhost:9080/test');
  }

  ngOnInit(): void {
  }

}
