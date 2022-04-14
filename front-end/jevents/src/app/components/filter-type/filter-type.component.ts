import {Component, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Event} from '../../entities/event/event';
import {EventService} from '../../services/event.service';

@Component({
  selector: 'app-filter-type',
  templateUrl: './filter-type.component.html',
  styleUrls: ['./filter-type.component.css']
})
export class FilterTypeComponent implements OnInit {

  @Output() type: string;

  constructor() {
  }

  ngOnInit(): void {
  }

}
