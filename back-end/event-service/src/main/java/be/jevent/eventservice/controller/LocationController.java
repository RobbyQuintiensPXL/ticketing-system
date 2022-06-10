package be.jevent.eventservice.controller;

import be.jevent.eventservice.createresource.CreateLocationResource;
import be.jevent.eventservice.dto.LocationDTO;
import be.jevent.eventservice.filter.UserNameFilter;
import be.jevent.eventservice.model.Location;
import be.jevent.eventservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<List<String>> getAllCities() {
        return new ResponseEntity<>(locationService.getAllLocationCities(), HttpStatus.OK);
    }

    @PostMapping(value = "/add_location")
    public ResponseEntity<Void> createLocation(@RequestHeader HttpHeaders token,
                                               @RequestBody @Valid CreateLocationResource locationResource) {
        UserNameFilter filter = new UserNameFilter();
        String ticketOffice = filter.getTicketOffice(token);
        locationService.createLocation(locationResource, ticketOffice);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(locationService.getLocationById(id), HttpStatus.OK);
    }

    @GetMapping(value = "office")
    public ResponseEntity<List<LocationDTO>> getLocationsByTicketOffice(@RequestHeader HttpHeaders token) {
        UserNameFilter filter = new UserNameFilter();
        String ticketOffice = filter.getTicketOffice(token);

        return new ResponseEntity<>(locationService.getLocationsByTicketOffice(ticketOffice), HttpStatus.OK);
    }
}
