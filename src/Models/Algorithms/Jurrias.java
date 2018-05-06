package Models.Algorithms;

import Models.Algorithm;
import Models.Box;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Jurrias extends Algorithm {
    private String name;
    private HashMap<Integer, ArrayList<ArrayList<Integer>>> combinationsOfNumbers;

    private  List<Integer> packageSizes;
    private static int totalNeedOfSum;

    private static ArrayList<Package> packages;
    public Jurrias()
    {
        super("Jurrias");
        combinationsOfNumbers = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
    }

    @Override
    public ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packagesp) {
        packages = new ArrayList<>(packagesp);
        ArrayList<Box> result = new ArrayList<Box>();
        int boxSize = SimulationHandler.simulation.getBoxSize();


        HashMap<Integer, Integer> amountOfcombinations = new HashMap<>();

        int totalPackages = Math.round((float)HelperMethods.sumOfPackageList(packages));

        packages.sort(Comparator.comparing(Package::getSize));
        Collections.reverse(packages);

        while(totalPackages > 0)
        {
            while (!doesPackageWithSizeExist(boxSize, packages))
            {
                boxSize--;
            }
            double amount = Math.floor(Double.parseDouble(Float.toString((float)totalPackages / (float)boxSize)));
            totalPackages -= amount * boxSize;

            if(amount != 0) {
                amountOfcombinations.put(boxSize, (int) (amount));
            }
            boxSize--;
        }

        Iterator it = amountOfcombinations.entrySet().iterator();

        packageSizes = packages.stream().map(p -> p.getSize()).collect(Collectors.toList());
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            int target = Integer.parseInt(pair.getKey().toString());
            totalNeedOfSum = Integer.parseInt(pair.getValue().toString());

            sum_up((ArrayList<Integer>) packageSizes, target, totalNeedOfSum);

            //res.add(sum_up(new ArrayList<Integer>(Arrays.asList(numbers)),Integer.parseInt(pair.getKey().toString())));

            it.remove(); // avoids a ConcurrentModificationException8
        }

        it = combinationsOfNumbers.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            int target = Integer.parseInt(pair.getKey().toString());

            for(ArrayList<Integer> alist : (ArrayList<ArrayList<Integer>>)pair.getValue())
            {
                Box box = new Box();
                for(Integer i : alist)
                {
                    Package x  = packages.stream()
                            .filter(s -> s.getSize() == i)
                            .findAny().orElse(null);
                    if(x != null) {
                        packages.remove(x);
                        box.addPackage(x);
                    }
                }
                if(box.getPackages().size() > 0) {
                    result.add(box);
                }
                //Optional<Package> x  = packages.stream()
                //        .filter(s -> s.getSize() == 2)
                //        .findAny();
               // box.addPackage(packages.);
            }
            //res.add(sum_up(new ArrayList<Integer>(Arrays.asList(numbers)),Integer.parseInt(pair.getKey().toString())));

            it.remove(); // avoids a ConcurrentModificationException
        }

        while(packages.size() > 0)
        {
            result.addAll(RunAlgorithm(SimulationHandler.simulation.getBoxSize(), packages));
        }

        return result;
    }

    private void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
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
                packageSizes.remove(partial);
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
    private void sum_up(ArrayList<Integer> numbers, int target, int totalNeedOfSum) {
        sum_up_recursive(numbers,target,new ArrayList<Integer>());
    }

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
