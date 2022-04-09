import {Time} from '@angular/common';

export class Event {

  id: number;
  eventName: string;
  eventType: string;
  location: string;
  eventDate: Date;
  eventTime: Time;
  fullDescription: string;
  shortDescription: string;

}
