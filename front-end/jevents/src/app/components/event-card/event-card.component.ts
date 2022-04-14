import {Component, Input, OnInit} from '@angular/core';
import {Event} from '../../entities/event/event';
import {EventService} from '../../services/event.service';
import {faSearchLocation} from '@fortawesome/free-solid-svg-icons';
import {faCalendarAlt} from '@fortawesome/free-regular-svg-icons';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';

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
  @Input() type!: string;

  constructor(private activatedRoute: ActivatedRoute,
              private eventService: EventService,
              private router: Router) {
    this.activatedRoute.paramMap.subscribe(params => {
      this.type = params.get('type');
    });
  }

  getEvents(): void {
    this.eventService.getEvents().subscribe(event => {
      console.log(event);
      this.events = event;
    });
  }

  getEventsByType(type: string): void {
    this.eventService.getEventsByType(type).subscribe( event => {
      this.events = event;
    });
  }

  ngOnInit(): void {
   // this.getEvents();
    this.getEventsByType(this.type);
  }

}
