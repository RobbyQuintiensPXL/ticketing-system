import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from '@auth0/auth0-angular';
import {DOCUMENT} from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  publicString: Observable<string>;

  constructor(public auth: AuthService, private http: HttpClient, @Inject(DOCUMENT) private doc: Document) {
  }

  loginWithRedirect(): void {
    this.auth.loginWithRedirect();
  }

  logout() {
    this.auth.logout({ returnTo: this.doc.location.origin });
  }

  request(): void {
    this.publicString = this.http.get<string>('http://localhost:9080/test');
  }

  ngOnInit(): void {
  }

}
