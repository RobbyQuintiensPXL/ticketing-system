package be.jevent.eventservice.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ticket_offices")
public class TicketOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organisation;

    @OneToMany(mappedBy = "ticketOffice")
    private Set<Location> locations;

    public TicketOffice(){
        //Empty Constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
}
