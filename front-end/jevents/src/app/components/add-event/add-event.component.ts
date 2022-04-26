import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, FormBuilder} from '@angular/forms';
import {EventService} from '../../services/event.service';
import {Event} from '../../entities/event/event';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  event: Event;

  eventInfoForm = new FormGroup({
    nameEvent: new FormControl(''),
    shortDescription: new FormControl(''),
    fullDescription: new FormControl(''),
    // Banner Image
    // Thumbnail Image
    // Location + Add Location
    // eventType
    amountOfTickets: new FormControl(''),
    maxOrderTickets: new FormControl(''),
    }
  );

  constructor(private eventService: EventService, private formBuilder: FormBuilder) {
  }

  addEvent(){
    this.event.eventName = this.eventInfoForm.value.nameEvent;
    this.event.shortDescription = this.eventInfoForm.value.shortDescription;
    this.event.description = this.eventInfoForm.value.fullDescription;
    this.eventService.createEvent(this.event).subscribe(() => {
    });
  }

  onSubmit(form: FormGroup){
  }

  ngOnInit(): void {
    this.eventInfoForm = this.formBuilder.group({
      nameEvent: [''],
      shortDescription: [''],
      fullDescription: [''],
      amountOfTickets: [''],
      maxOrderTickets: ['']
    });
  }

}
