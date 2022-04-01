package be.jevent.eventservice.service;

import be.jevent.eventservice.createresource.CreateLocationResource;
import be.jevent.eventservice.dto.LocationDTO;
import be.jevent.eventservice.exception.LocationException;
import be.jevent.eventservice.model.Location;
import be.jevent.eventservice.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MessageSource messageSource;

    public List<LocationDTO> getAllLocations(){
        List<LocationDTO> locationDTOList = locationRepository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
        if(locationDTOList.isEmpty()){
            throw new LocationException("No locations found");
        }
        return locationDTOList;
    }

    public String createLocation(CreateLocationResource locationResource, Locale locale){
        String responseMessage;
        responseMessage = String.format(messageSource.getMessage(
                "location.create.message", null, locale),
                locationResource.toString());
        Location location = new Location();
        location.setBuildingName(locationResource.getBuildingName());
        location.setZipCode(locationResource.getZipCode());
        location.setCity(locationResource.getCity());
        location.setAddress(locationResource.getAddress());
        location.setCountry(locationResource.getCountry());

        locationRepository.save(location);

        return responseMessage;
    }
}
