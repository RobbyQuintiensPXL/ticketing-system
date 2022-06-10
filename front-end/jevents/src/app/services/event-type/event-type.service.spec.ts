import {TestBed} from '@angular/core/testing';

import {EventTypeService} from './event-type.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {LocationService} from '../location-service/location.service';
import {throwError} from 'rxjs';

describe('EventTypeService', () => {
  let service: EventTypeService;
  let httpTestingController: HttpTestingController;
  let mockedEventTypes: string[];
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LocationService]
    });
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EventTypeService]
    });
  });

  describe('EventTypeService Tests', () => {
    beforeEach(() => {
      service = TestBed.inject(EventTypeService);
      httpTestingController = TestBed.inject(HttpTestingController);
      mockedEventTypes = ['type1', 'type2', 'type3'];
    });

    it('should be created', () => {
      expect(service).toBeTruthy();
    });

    it('should return all eventtypes', () => {
      service.getEventTypes().subscribe(types => {
        expect(types).toEqual(mockedEventTypes);

        const request = httpTestingController.expectOne('/event/events/types');

        request.flush([mockedEventTypes]);

        httpTestingController.verify();
      });
    });

    it('should throw an error if no eventtypes found', () => {
      const errorResponse = new Error('No EventTypes Found');
      spyOn(service, 'getEventTypes').and.returnValue(throwError(errorResponse));
    });
  });
});
