package be.jevent.eventservice.service;

import be.jevent.eventservice.createresource.CreateLocationResource;
import be.jevent.eventservice.dto.LocationDTO;
import be.jevent.eventservice.dto.TicketOfficeDTO;
import be.jevent.eventservice.exception.LocationException;
import be.jevent.eventservice.exception.TicketOfficeException;
import be.jevent.eventservice.model.Location;
import be.jevent.eventservice.model.TicketOffice;
import be.jevent.eventservice.repository.LocationRepository;
import be.jevent.eventservice.repository.TicketOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TicketOfficeRepository ticketOfficeRepository;

    @Autowired
    private MessageSource messageSource;

    public List<LocationDTO> getAllLocations(){
        List<LocationDTO> locationDTOList = locationRepository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
        if(locationDTOList.isEmpty()){
            throw new LocationException("No locations found");
        }
        return locationDTOList;
    }

    public List<LocationDTO> getLocationsByTicketOffice(Long id){
        List<LocationDTO> locationDTOList = locationRepository.findAllByTicketOffice_Id(id).stream().map(LocationDTO::new).collect(Collectors.toList());
        if(locationDTOList.isEmpty()){
            throw new LocationException("No locations found for id " + id);
        }
        return locationDTOList;
    }

    public List<LocationDTO> getLocationsByTicketOfficeEmail(String email){
        List<LocationDTO> locationDTOList = locationRepository.findAllByTicketOffice_Email(email).stream().map(LocationDTO::new).collect(Collectors.toList());
        if(locationDTOList.isEmpty()){
            throw new LocationException("No locations found");
        }
        return locationDTOList;
    }

    public String createLocation(CreateLocationResource locationResource, Locale locale, Long id){
        Optional<TicketOffice> ticketOffice = ticketOfficeRepository.findById(id);
        if(ticketOffice.isEmpty()){
            throw new TicketOfficeException("Ticket office not found");
        }

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
        location.setTicketOffice(ticketOffice.get());

        locationRepository.save(location);

        return responseMessage;
    }
}
