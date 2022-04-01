package be.jevent.eventservice.model;

public enum EventType {

    CONCERT("Concert"), NIGHTLIFE("Nightlife"), SPORTS("Sports"),
    GAMING("Gaming"), CULTURE("Culture");

    private final String type;

    private EventType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

}
