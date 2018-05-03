package Models;

import java.util.ArrayList;

public class Log {
    private  ArrayList<Event> events;

    public Log()
    {
        events = new ArrayList<Event>();
    }

    public void addEvent(Event event)
    {
        events.add(event);
    }

    public ArrayList<Event> getEvents()
    {
        return this.events;
    }
}
