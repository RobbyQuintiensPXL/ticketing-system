// import {Component, OnInit} from '@angular/core';
// import {FormGroup, FormControl, FormBuilder} from '@angular/forms';
// import {EventService} from '../../services/event-service/event.service';
// import {Event} from '../../entities/event/event';
// import {LocationService} from '../../services/location-service/location.service';
// import {Location} from '../../entities/location/location';
// import {EventTypeService} from '../../services/event-type/event-type.service';
// import {AuthService, User} from '@auth0/auth0-angular';
// import {Observable} from 'rxjs';
//
//
// @Component({
//   selector: 'app-add-event',
//   templateUrl: './add-event.component.html',
//   styleUrls: ['./add-event.component.css']
// })
//

// export class AddEventComponent implements OnInit {
//
//   event: Event;
//   locations: Location[];
//   location: Location;
//   eventTypes: string[];
//   eventType: string;
//   email: string;
//   user?: any;
//   profileJson?: string;
//
//   eventInfoForm = new FormGroup({
//       nameEvent: new FormControl(''),
//       shortDescription: new FormControl(''),
//       fullDescription: new FormControl(''),
//       bannerImage: new FormControl(''),
//       thumbImage: new FormControl(''),
//       location: new FormControl(''),
//       eventType: new FormControl(''),
//       amountOfTickets: new FormControl(''),
//       maxOrderTickets: new FormControl(''),
//     // price
//     // date
//     // time
//     }
//   );
//
//   constructor(private eventService: EventService,
//               private formBuilder: FormBuilder,
//               private locationService: LocationService,
//               private eventTypeService: EventTypeService
//               ) {
//   }
//
//   listLocation(): void {
//     this.locationService.getLocations().subscribe(location => {
//       this.locations = location;
//     });
//   }
//
//   listEventTypes(): void {
//     this.eventTypeService.getEventTypes().subscribe(eventType => {
//       this.eventTypes = eventType;
//     });
//   }
//
//   addEvent() {
//     this.event.eventName = this.eventInfoForm.value.nameEvent;
//     this.event.shortDescription = this.eventInfoForm.value.shortDescription;
//     this.event.description = this.eventInfoForm.value.fullDescription;
//     this.eventService.createEvent(this.event).subscribe(() => {
//     });
//   }
//
//   onSubmit(form: FormGroup) {
//   }
//
//   ngOnInit(): void {
//     this.listLocation();
//     this.listEventTypes();
//     this.eventInfoForm = this.formBuilder.group({
//       nameEvent: [''],
//       shortDescription: [''],
//       fullDescription: [''],
//       amountOfTickets: [''],
//       maxOrderTickets: ['']
//     });
//   }
//
// }


import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {EventTypeService} from '../../services/event-type/event-type.service';
import {LocationService} from '../../services/location-service/location.service';
import {Location} from '../../entities/location/location';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  isLinear = true;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  event: Event;
  locations: Location[];
  location: Location;
  eventTypes: string[];
  eventType: string;
  email: string;

  constructor(private formBuilder: FormBuilder,
              private eventTypeService: EventTypeService,
              private locationService: LocationService) {
  }

  listEventTypes(): void {
    this.eventTypeService.getEventTypes().subscribe(eventType => {
      this.eventTypes = eventType;
    });
  }

  listLocation(): void {
    this.locationService.getLocations().subscribe(location => {
      this.locations = location;
    });
  }

  ngOnInit() {
    this.listEventTypes();
    this.listLocation();
    this.firstFormGroup = this.formBuilder.group({
      nameEvent: ['', Validators.required],
      bannerImage: ['', Validators.required],
      thumbImage: ['', Validators.required],
      eventType: ['', Validators.required],
      location: ['', Validators.required],
    });
    this.secondFormGroup = this.formBuilder.group({
      shortDescription: ['', Validators.required],
      fullDescription: ['', Validators.required],
    });
  }

  submit() {
    console.log(this.firstFormGroup.value);
    console.log(this.secondFormGroup.value);
  }
}
