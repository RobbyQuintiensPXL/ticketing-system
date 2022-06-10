import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {LocationService} from '../../services/location-service/location.service';

@Component({
  selector: 'app-filter-location',
  templateUrl: './filter-location.component.html',
  styleUrls: ['./filter-location.component.css']
})
export class FilterLocationComponent implements OnInit {
  @Output() locationOutputEvent = new EventEmitter<any>();
  @Input() accepted?: boolean;
  locations: string[];
  location: string;
  selectedLocation: any;

  constructor(private locationService: LocationService,
              private router: Router) {
  }

  getParamsLocation(accepted?: any) {
    const params: any = {};
    if (accepted != null) {
      params.accepted = this.accepted;
    }
    return params;
  }

  getLocation(event) {
    this.locationOutputEvent.emit(event.target.value);
  }

  listAllLocations(): void {
    this.locationService.getCities(this.getParamsLocation(this.accepted)).subscribe(location => {
      this.locations = location;
    });
  }

  locationSelected(event: any) {
    this.selectedLocation = event.target.value;
    this.router.navigate(['../search'], {queryParams: {city: this.selectedLocation}});
  }


  ngOnInit(): void {
    this.listAllLocations();
  }

}
