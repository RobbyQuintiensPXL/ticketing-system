import { Injectable } from '@angular/core';
import {Location} from '../../entities/location/location';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Event} from '../../entities/event/event';
import {catchError, map} from 'rxjs/operators';
import {AuthService} from '@auth0/auth0-angular';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private readonly locationUrl: string;
  locations: Location[];
  location: Location;

  constructor(private http: HttpClient) {
    this.locationUrl = '/event/locations';
  }

  public getLocations(): Observable<Location[]> {
    return this.http.get<Location[]>(this.locationUrl).pipe(
      map(locations => locations.map(eventJson => new Location(eventJson))),
      catchError(error => {
        return throwError('No Locations Found');
      })
    );
  }

  public getLocationsByTicketOffice(email: string): Observable<Location[]> {
    return this.http.get<Location[]>(this.locationUrl + '/' + email).pipe(
      map(locations => locations.map(eventJson => new Location(eventJson))),
      catchError(error => {
        return throwError('No Locations Found');
      })
    );
  }


}
