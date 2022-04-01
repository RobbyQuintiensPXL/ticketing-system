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

    @PostMapping
    public ResponseEntity<String> createLocation(@RequestBody @Valid CreateLocationResource locationResource,
                                                 @RequestHeader(value = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(locationService.createLocation(locationResource, locale));
    }

}
