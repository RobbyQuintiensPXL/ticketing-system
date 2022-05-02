import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import { EventCardComponent } from './components/event-card/event-card.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EventService} from './services/event-service/event.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {MatGridListModule} from '@angular/material/grid-list';
import { FilterTypeComponent } from './components/filter-type/filter-type.component';
import { EventDetailComponent } from './components/event-detail/event-detail.component';
import {AppRoutes} from './app.routes';
import {AuthHttpInterceptor, AuthModule} from '@auth0/auth0-angular';
import {LoginComponent} from './components/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AddEventComponent } from './components/add-event/add-event.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatStepperModule} from '@angular/material/stepper';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    EventCardComponent,
    FilterTypeComponent,
    EventDetailComponent,
    ProfileComponent,
    AddEventComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatToolbarModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    MatListModule,
    FontAwesomeModule,
    MatGridListModule,
    RouterModule,
    AppRoutes,
    ReactiveFormsModule,
    AuthModule.forRoot({
      domain: 'dev-7mgwq79y.eu.auth0.com',
      clientId: 'Ny5lpiTnQWBa7OKC3GHEquZdupRm4pjt',
      audience: 'https://jevents.be',
      scope: 'openid profile email user office admin',
      httpInterceptor: {
        allowedList: [
          '/*',
          {
            uri: '/search',
            allowAnonymous: true,
          },
          {
            uri: '/add-event',
            tokenOptions: {
              audience: 'https://jevents.be',
              scope: 'office',
              redirect_uri: '/search'
            },
          },
        ],
      },
    }),
    MatFormFieldModule,
    MatStepperModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthHttpInterceptor,
      multi: true,
    },
    EventService],
  bootstrap: [AppComponent]
})
export class AppModule { }
