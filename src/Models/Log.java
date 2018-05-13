package Models;

import Models.HelperClasses.SimulationHandler;

import java.util.ArrayList;

public class Log {
    private  ArrayList<Event> events;

    //Initializes event array
    public Log()
    {
        events = new ArrayList<Event>();
    }

    //Adds event to log
    public void addEvent(Event event)
    {
        events.add(event);
    }

    //Returns all events
    public ArrayList<Event> getEvents()
    {
        return this.events;
    }

    public String GetExportString()
    {
        String result =  "";


        String results = "";
        String steps = "";

        //Adds all events from current log to results string for textbox
        for(Event event : SimulationHandler.simulation.getLog().getEvents())
        {
            //Fills string according to event type
            if(event.getType() == EventType.Result)
            {
                results += event.getDescription();
                if(event.getTime() != 0)
                {
                    results +=": Time: " + event.getTime() + " microseconds" + System.lineSeparator();
                }
                else
                {
                    results += System.lineSeparator();
                }
            }
            if(event.getType() == EventType.Step)
            {
                steps += event.getDescription();
                if(event.getTime() != 0)
                {
                    steps +=": Time: " + event.getTime() + " microseconds " + System.lineSeparator();
                }
                else
                {
                    steps += System.lineSeparator();
                }
            }

            if(event.getTime() != 0)
            {

            }

        }
        result = "RESULT" + System.lineSeparator() + "----------------------------- " + System.lineSeparator()
                + results + System.lineSeparator() +   "----------------------------- " + System.lineSeparator()
                + "STEPS" + System.lineSeparator() +   "----------------------------- " + System.lineSeparator() + steps;

        return result;
    }
}
