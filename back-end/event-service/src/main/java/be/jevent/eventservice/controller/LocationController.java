package be.jevent.eventservice.controller;

import be.jevent.eventservice.createresource.CreateLocationResource;
import be.jevent.eventservice.dto.LocationDTO;
import be.jevent.eventservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations(){
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @PostMapping(value = "{id}/add_location")
    public ResponseEntity<String> createLocation(@RequestBody @Valid CreateLocationResource locationResource,
                                                 @PathVariable("id") Long id,
                                                 @RequestHeader(value = "Accept-Language", required = false) Locale locale){
        return new ResponseEntity<>(locationService.createLocation(locationResource, locale, id), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<List<LocationDTO>> getLocationsByTicketOffice(@PathVariable("id") Long id){
        return new ResponseEntity<>(locationService.getLocationsByTicketOffice(id), HttpStatus.OK);
    }

}
