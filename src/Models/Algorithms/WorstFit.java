package Models.Algorithms;

import Models.*;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WorstFit extends Algorithm {
    public WorstFit()
    {
        super("Worst fit");
    }

    @Override
    public ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packages) {

        //Create list and first box
        SimulationHandler.simulation.getLog().addEvent(new Event("Started calculating for worst fit.", 0, EventType.Step));

        long startTime = System.nanoTime();
        ArrayList<Box> result = new ArrayList<Box>();
        result.add(new Box());

        long endTime = System.nanoTime();

        long duration = TimeUnit.MICROSECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);//In milliseconds
        SimulationHandler.simulation.getLog().addEvent(new Event("Created first box", duration, EventType.Step));

        //Sort packages
        packages.sort(Comparator.comparing(Package::getSize));

        //Reverse so its in decending order
        Collections.reverse(packages);

        for(int i = 0; i < packages.size(); i++)
        {
            //Sort all opened boxes

            startTime = System.nanoTime();
            result.sort(Comparator.comparing(Box::getSumOfPackages));
            //Reverse so its in descending order
            Collections.reverse(result);
            endTime = System.nanoTime();
            duration = TimeUnit.MICROSECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);//In

            SimulationHandler.simulation.getLog().addEvent(new Event("Sorting boxes", duration, EventType.Step));

            boolean doesFit = false;

            //If it fits, add to box
            for(Box box : result)
            {

                startTime = System.nanoTime();
                if(((box.getSumOfPackages() + packages.get(i).getSize()) <= SimulationHandler.simulation.getBoxSize() && !doesFit))
                {
                    box.addPackage(packages.get(i));
                    doesFit = true;
                    endTime = System.nanoTime();
                    duration = TimeUnit.MICROSECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);//In

                    SimulationHandler.simulation.getLog().addEvent(
                            new Event("Put package of size " + packages.get(i).getSize() + " in box " + (result.size() - 1)
                                    , duration, EventType.Step));
                }
            }
            if(!doesFit)
            {
                startTime = System.nanoTime();
                //If it doesnt fit, open new box and add to that new box
                Box b = new Box();
                endTime = System.nanoTime();
                duration = TimeUnit.MICROSECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);//In

                SimulationHandler.simulation.getLog().addEvent(
                        new Event("Created new box"
                                , duration, EventType.Step));

                b.addPackage(packages.get(i));
                result.add(b);
                endTime = System.nanoTime();
                duration = TimeUnit.MICROSECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);//In

                SimulationHandler.simulation.getLog().addEvent(
                        new Event("Put package of size " + packages.get(i).getSize() + " in box " + (result.size() - 1)
                                , duration, EventType.Step));

            }


        }
        return result;
    }
}
