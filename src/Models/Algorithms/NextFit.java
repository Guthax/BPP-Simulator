package Models.Algorithms;

import Models.*;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NextFit extends Algorithm {
    public NextFit()
    {
        super("Next fit");
    }

    @Override
    public ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packages) {


        SimulationHandler.simulation.getLog().addEvent(new Event("Started calculating for next fit.", 0, EventType.Step));

        long startTime = System.nanoTime();

        //Creates list and first box
        ArrayList<Box> result = new ArrayList<Box>();
        result.add(new Box());
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In milliseconds
        SimulationHandler.simulation.getLog().addEvent(new Event("Created first box", duration, EventType.Step));

        for(int i = 0; i < packages.size(); i++)
        {
            startTime = System.nanoTime();
            //If its not the first package then check if its fits in the last opened box
            int boxIndex;
            if(i == 0) {
                boxIndex = 0;
            }
            else
            {
                boxIndex = (i - 1);
            }

            //If it fits then add the package to the last opened box
            if(((HelperMethods.sumOfPackageList(result.get(result.size() - 1).getPackages()) + packages.get(i).getSize()) <= SimulationHandler.simulation.getBoxSize()))
            {
                result.get(result.size() - 1).addPackage(packages.get(i));
                endTime = System.nanoTime();

                duration = endTime - startTime;
                duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In microseconds

                SimulationHandler.simulation.getLog().addEvent(
                        new Event("Put package of size " + packages.get(i).getSize() + " in box " + (result.size() - 1)
                        , duration, EventType.Step));
            }
            else
            {
                //If it doesnt fit, open a new box and add the package.
                Box b = new Box();
                b.addPackage(packages.get(i));
                result.add(b);

                endTime = System.nanoTime();
                duration = endTime - startTime;
                duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In microseconds

                SimulationHandler.simulation.getLog().addEvent(
                        new Event("Put package of size " + packages.get(i).getSize() + " in new box "
                                , duration, EventType.Step));
            }

        }
        return result;
    }


}
