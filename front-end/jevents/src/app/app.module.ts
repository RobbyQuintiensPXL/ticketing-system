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
import {EventService} from './services/event.service';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {MatGridListModule} from '@angular/material/grid-list';
import { FilterTypeComponent } from './components/filter-type/filter-type.component';
import { EventDetailComponent } from './components/event-detail/event-detail.component';
import {AppRoutes} from './app.routes';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    EventCardComponent,
    FilterTypeComponent,
    EventDetailComponent
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
    FontAwesomeModule,
    MatGridListModule,
    RouterModule,
    AppRoutes
  ],
  providers: [EventService],
  bootstrap: [AppComponent]
})
export class AppModule { }
