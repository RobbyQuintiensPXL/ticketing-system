import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, Subscription, throwError} from 'rxjs';
import {Event} from '../entities/event/event';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private readonly eventUrl: string;
  events: Event[];
  event: Event;

  constructor(private http: HttpClient) {
    this.eventUrl = 'http://localhost:9080/event/events';
  }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  public getEventById(id: number): Observable<Event> {
    const endpoint =  this.eventUrl + '/' + id;
    return this.http.get<Event>(endpoint);
  }

  public getEventsByType(type: string) {
    const endpoint = this.eventUrl + '/search?type=' + type;
    return this.http.get<Event[]>(endpoint);
  }

  public getEvents() {
    return this.http.get<Event[]>(this.eventUrl).pipe(
      map(events => events.map(eventJson => new Event(eventJson))),
      catchError(error => {
        return throwError('Capital not found!');
      })
    );
  }
}
