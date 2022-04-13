import { Component, OnInit } from '@angular/core';
import {Event} from '../../entities/event/event';
import {EventService} from '../../services/event.service';
import {faSearchLocation} from '@fortawesome/free-solid-svg-icons';
import {faCalendarAlt} from '@fortawesome/free-regular-svg-icons';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-event-card',
  templateUrl: './event-card.component.html',
  styleUrls: ['./event-card.component.css']
})
export class EventCardComponent implements OnInit {
  event: Event;
  events: Event[];
  faSearchLocation = faSearchLocation;
  faCalenderAlt = faCalendarAlt;

  constructor(private eventService: EventService) { }

  getEvents(): void {
    this.eventService.getEvents().subscribe(event => {
      console.log(event);
      this.events = event;
    });
  }

  getEventById(id: number): Observable<Event>{
    return this.eventService.getEventById(id);
  }

  ngOnInit(): void {
    this.getEvents();
  }

}
