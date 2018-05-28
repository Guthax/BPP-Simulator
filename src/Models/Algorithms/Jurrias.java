package Models.Algorithms;

import Models.*;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Jurrias extends Algorithm {
    private String name;
    private HashMap<Integer, ArrayList<ArrayList<Integer>>> combinationsOfNumbers;

    private static  List<Integer> packageSizes;
    private static int totalNeedOfSum;

    private static ArrayList<Package> packages;

    private static int desperateCounter;

    private static boolean deletedNumbers;
    public Jurrias()
    {
        super("Jurrias");
        combinationsOfNumbers = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
        desperateCounter = 0;
    }

    @Override
    public ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packagesp) {
        SimulationHandler.simulation.getLog().addEvent(new Event("Started calculating for jurrias.", 0, EventType.Step));
        long startTime = System.nanoTime();

        packages = new ArrayList<>(packagesp);
        ArrayList<Box> result = new ArrayList<Box>();
        int boxSize = SimulationHandler.simulation.getBoxSize();


        //Calculates possible combinations by dividing by the boxsize
        HashMap<Integer, Integer> amountOfcombinations = new HashMap<>();

        int totalPackages = Math.round((float)HelperMethods.sumOfPackageList(packages));

        //Reverses the packages so its descending
        packages.sort(comparing(Package::getSize));
        Collections.reverse(packages);

        //Calculates time it took to sort and adds it to log
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In milliseconds
        SimulationHandler.simulation.getLog().addEvent(new Event("Divided sum of package size", duration, EventType.Step));

        while(totalPackages > 0 && boxSize > 1)
        {
            //Calculates possible combinations by dividing by the boxsize
            startTime = System.nanoTime();

            //If the functions is recursively run 3 or more times
            if(desperateCounter > 3) {

                while (!doesPackageWithSizeExist(boxSize, packages) && boxSize > 1) {
                    boxSize--;
                }
            }


            double amount = Math.floor(Double.parseDouble(Float.toString((float)totalPackages / (float)boxSize)));
            totalPackages -= amount * boxSize;

            if (amount != 0) {
                amountOfcombinations.put(boxSize, (int) (amount));
            }
            boxSize--;

            //Add to log
            endTime = System.nanoTime();

            duration = endTime - startTime;
            duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In milliseconds
            SimulationHandler.simulation.getLog().addEvent(new Event("Added combination("  + amount + " boxes of " + boxSize + ") to collection of combinations", duration, EventType.Step));
        }

       Iterator it = amountOfcombinations.entrySet().iterator();

        //Try to find combination of packages so the calculated amount of combinations is met
        packageSizes = packages.stream().map(p -> p.getSize()).collect(Collectors.toList());
        while (it.hasNext()) {
            startTime = System.nanoTime();
            Map.Entry pair = (Map.Entry)it.next();

            int target = Integer.parseInt(pair.getKey().toString());
            totalNeedOfSum = Integer.parseInt(pair.getValue().toString());
            deletedNumbers = false;
            sum_up((ArrayList<Integer>) packageSizes, target);


            it.remove(); // avoids a ConcurrentModificationException8

            endTime = System.nanoTime();


            //Add to log
            duration = endTime - startTime;
            duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In milliseconds
            SimulationHandler.simulation.getLog().addEvent(new Event("Found a combination of boxes that add up to " + target, duration, EventType.Step));

        }

        it = combinationsOfNumbers.entrySet().iterator();

        //Adds package to box if combination is met
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            int target = Integer.parseInt(pair.getKey().toString());

            for(ArrayList<Integer> alist : (ArrayList<ArrayList<Integer>>)pair.getValue())
            {
                startTime = System.nanoTime();
                Box box = new Box();
                endTime = System.nanoTime();

                duration = endTime - startTime;
                duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In milliseconds
                SimulationHandler.simulation.getLog().addEvent(new Event("Opened new box", duration, EventType.Step));
                for(Integer i : alist)
                {

                    startTime = System.nanoTime();

                    //Gets the package that is needed to fill combination and adds it to the box.
                    Package x  = packages.stream()
                            .filter(s -> s.getSize() == i)
                            .findAny().orElse(null);
                    if(x != null) {
                        packages.remove(x);
                        box.addPackage(x);
                        endTime = System.nanoTime();

                        duration = endTime - startTime;
                        duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In milliseconds
                        SimulationHandler.simulation.getLog().addEvent(new Event("Added package to box", duration, EventType.Step));
                    }
                }
                if(box.getPackages().size() > 0) {
                    //If all packages are placed in the box, then add the box to result
                    result.add(box);
                }
            }

            it.remove(); // avoids a ConcurrentModificationException
        }

        //If there are packages that are not in the box, recursively run this function again.
        while(packages.size() > 0)
        {
            desperateCounter++;
            result.addAll(RunAlgorithm(SimulationHandler.simulation.getBoxSize(), packages));
        }

        if(packages.size() == 0)
        {
            desperateCounter = 0;
        }
        return result;
    }

    private void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
        //Finds combination of packages that add up to target
        int s = 0;
        for (int x: partial) s += x;
        if (s == target) {
            if(totalNeedOfSum == 0)
            {
                return;
            }
            else {
                if (combinationsOfNumbers.containsKey(target)) {
                    totalNeedOfSum--;
                    combinationsOfNumbers.get(target).add(partial);

                } else {
                    totalNeedOfSum--;
                    combinationsOfNumbers.put(target, new ArrayList<>());
                    combinationsOfNumbers.get(target).add(partial);
                }
                if(!deletedNumbers) {
                    for (Integer x : partial) {
                        packageSizes.remove(x);
                        deletedNumbers = true;
                    }
                }
            }
        }

        if (s >= target)
            return;
        for(int i=0;i<numbers.size();i++) {
            ArrayList<Integer> remaining = new ArrayList<Integer>();
            int n = numbers.get(i);
            for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
            ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
            partial_rec.add(n);
            sum_up_recursive(remaining,target,partial_rec);
        }
    }
    private void sum_up(ArrayList<Integer> numbers, int target) {
        sum_up_recursive(numbers,target,new ArrayList<Integer>());
    }

    //Checks if package with a certain size exists in a collection of packages
    private boolean doesPackageWithSizeExist(int size, ArrayList<Package> packages)
    {
        for(Package p : packages)
        {
            if(p.getSize() == size)
            {
                return true;
            }
        }
        return false;

    }
}
