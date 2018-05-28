package Models.Algorithms;

import Models.*;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FirstFit extends Algorithm {

    public FirstFit()
    {
        super("First fit");
    }
    @Override
    public ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packages) {
        SimulationHandler.simulation.getLog().addEvent(new Event("Started calculating for first fit.", 0, EventType.Step));

        long startTime = System.nanoTime();
        ArrayList<Box> result = new ArrayList<Box>();
        result.add(new Box());

        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In milliseconds
        SimulationHandler.simulation.getLog().addEvent(new Event("Created first box", duration, EventType.Step));

        for(int i = 0; i < packages.size(); i++)
        {
            startTime = System.nanoTime();
            boolean doesFit = false;
            for(Box box : result)
            {
                if(!doesFit) {
                    if (packages.get(i).getSize() + HelperMethods.sumOfPackageList(box.getPackages()) <= SimulationHandler.simulation.getBoxSize()) {
                        box.addPackage(packages.get(i));
                        doesFit = true;
                        endTime = System.nanoTime();
                        duration = endTime - startTime;
                        duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In microseconds
                    }
                }
            }
            if(!doesFit)
            {
               Box b = new Box();
               b.addPackage(packages.get(i));
               result.add(b);

               endTime = System.nanoTime();
               duration = endTime - startTime;
               duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In microseconds


            }
            SimulationHandler.simulation.getLog().addEvent(
                    new Event("Put package of size " + packages.get(i).getSize() + " in box " + (result.size() - 1)
                            , duration, EventType.Step));
        }
        return result;
    }
}
