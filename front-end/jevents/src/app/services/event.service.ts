import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, Subscription, throwError} from 'rxjs';
import {Event} from '../entities/event/event';
import {catchError, map} from 'rxjs/operators';
import {AuthService} from '@auth0/auth0-angular';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private readonly eventUrl: string;
  events: Event[];
  event: Event;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
    this.eventUrl = '/event/events';
  }

  public getEventById(id: number): Observable<Event> {
    const endpoint =  this.eventUrl + '/' + id;
    return this.http.get<Event>(endpoint);
  }

  public getEventsByType(type: string): Observable<Event[]> {
    const endpoint = this.eventUrl + '/search?type=' + type;
    return this.http.get<Event[]>(endpoint);
  }

  public getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.eventUrl).pipe(
      map(events => events.map(eventJson => new Event(eventJson))),
      catchError(error => {
        return throwError('No Events Found');
      })
    );
  }

  public createEvent(event: Event): Observable<Event>{
    const endpoint = this.eventUrl + '/post';
    const body = JSON.stringify(event);
    return this.http.post<Event>(endpoint, body, this.httpOptions);
  }
}
