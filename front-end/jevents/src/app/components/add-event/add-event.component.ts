import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {EventTypeService} from '../../services/event-type/event-type.service';
import {LocationService} from '../../services/location-service/location.service';
import {Location} from '../../entities/location/location';
import {EventService} from '../../services/event-service/event.service';
import {Event} from '../../entities/event/event';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  isLinear = true;
  // myForm: FormGroup = new FormGroup({
  //   firstFormGroup: new FormGroup({
  //     nameEvent: new FormControl([null, Validators.required]),
  //     bannerImage: new FormControl([null, Validators.required]),
  //     thumbImage: new FormControl([null, Validators.required]),
  //     eventType: new FormControl([null, Validators.required]),
  //     location: new FormControl([null, Validators.required]),
  //   }),
  //   secondFormGroup: new FormGroup({
  //     shortDescription: new FormControl([null, Validators.required]),
  //     fullDescription: new FormControl([null, Validators.required]),
  //   }),
  //   thirdFormGroup: new FormGroup({})
  // });
  event: Event;
  locations: Location[];
  location: Location;
  eventTypes: string[];
  eventType: string;
  email: string;
  eventInfoGroup: FormGroup;
  eventDescriptionGroup: FormGroup;
  eventTicketGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private eventTypeService: EventTypeService,
              private locationService: LocationService,
              private eventService: EventService) {
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

  createEvent(): void {
    this.event.eventName = this.eventInfoGroup.value.nameEvent;
    this.event.eventType = this.eventInfoGroup.value.eventType;
    this.event.banner = this.eventInfoGroup.value.bannerImage;
    this.event.thumb = this.eventInfoGroup.value.thumbImage;
    this.event.location = this.eventInfoGroup.value.locations;
    this.event.shortDescription = this.eventDescriptionGroup.value.shortDescription;
    this.event.description = this.eventDescriptionGroup.value.fullDescription;
    this.event.price = this.eventTicketGroup.value.price;
    this.event.eventDate = this.eventTicketGroup.value.date;
    this.event.eventTime = this.eventTicketGroup.value.time;
  }

  ngOnInit() {
    this.eventInfoGroup = this.formBuilder.group({
      nameEvent: [null, Validators.required],
      bannerImage: [null, Validators.required],
      thumbImage: [null, Validators.required],
      eventType: [null, Validators.required],
      location: [null, Validators.required],
    });
    this.eventDescriptionGroup = this.formBuilder.group({
      shortDescription: [null, Validators.required],
      fullDescription: [null, Validators.required],
    });
    this.eventTicketGroup = this.formBuilder.group({
      amountOfTickets: [null, Validators.required],
      maxOrderTickets: [null, Validators.required],
      price: [null, Validators.required],
      date: [null, Validators.required],
      time: [null, Validators.required],
      earlyBirdDate: [null],
      earlyBirdPrice: [null],
    });
    this.listEventTypes();
    this.listLocation();
  }

  submit() {
    this.createEvent();
    console.log(this.event);
    this.eventService.createEvent(this.event);
  }

  onFileChange(event) {
    const reader = new FileReader();
    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.eventInfoGroup.patchValue({
          fileSource: reader.result
        });
      };
    }
  }
}
