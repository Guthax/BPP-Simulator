package Models;

public class Event {
    private String description;
    private long time;
    private EventType type;

    public Event(String description, long time, EventType type)
    {
        this.description = description;
        this.time = time;
        this.type = type;
    }

    public String getDescription()
    {
        return this.description;
    }

    public Long getTime()
    {
        return this.time;
    }

    public EventType getType() {
        return type;
    }
}
