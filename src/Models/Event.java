package Models;

public class Event {
    private String description;
    private long time;
    private EventType type;

    //Initializes attributes
    public Event(String description, long time, EventType type)
    {
        this.description = description;
        this.time = time;
        this.type = type;
    }

    //Returns event description
    public String getDescription()
    {
        return this.description;
    }

    //Returns event time
    public Long getTime()
    {
        return this.time;
    }

    //Returns event type
    public EventType getType() {
        return type;
    }
}
