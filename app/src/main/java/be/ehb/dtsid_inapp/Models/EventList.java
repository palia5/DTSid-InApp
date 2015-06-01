package be.ehb.dtsid_inapp.Models;

import java.util.List;

public class EventList
{
    //Variables
    private List<Event> events;

    //Constructor
    public EventList(List<Event> events)
    {
        this.events = events;
    }

    //Getters and setters
    public List<Event> getEvents()
    {
        return events;
    }
    public void setEvents(List<Event> events)
    {
        this.events = events;
    }
}
