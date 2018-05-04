package Models.Algorithms;

import Models.Algorithm;
import Models.Box;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.util.*;

public class Jurrias extends Algorithm {
    private String name;
    static HashMap<Integer, ArrayList<ArrayList<Integer>>> combinationsOfNumbers;

    public Jurrias()
    {
        super("Jurrias");
    }

    @Override
    public ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packages) {
        ArrayList<Box> result = new ArrayList<Box>();
        int boxSize = SimulationHandler.simulation.getBoxSize();


        HashMap<Integer, Integer> combinations = new HashMap<>();

        int totalPackages = Math.round((float)HelperMethods.sumOfPackageList(packages));

        packages.sort(Comparator.comparing(Package::getSize));
        Collections.reverse(packages);

        while(totalPackages > 0)
        {
            double amount = Math.floor(Double.parseDouble(Float.toString((float)totalPackages / (float)boxSize)));
            totalPackages -= amount * boxSize;

            if(amount != 0) {
                combinations.put(boxSize, (int) (amount));
            }
            boxSize--;
        }

        Iterator it = combinations.entrySet().iterator();
        ArrayList<HashMap<Integer, ArrayList<ArrayList<Integer>>>> res = new ArrayList<HashMap<Integer, ArrayList<ArrayList<Integer>>>>();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            //res.add(sum_up(new ArrayList<Integer>(Arrays.asList(numbers)),Integer.parseInt(pair.getKey().toString())));

            it.remove(); // avoids a ConcurrentModificationException
        }

        Integer[] numbers = {3,9,8,4,5,7,10};
        int target = 5;
        sum_up(new ArrayList<Integer>(Arrays.asList(numbers)),target);
        return result;
    }

    static void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
        int s = 0;
        for (int x: partial) s += x;
        if (s == target) {
            System.out.println("sum(" + Arrays.toString(partial.toArray()) + ")=" + target);
            if(combinationsOfNumbers.containsKey(target))
            {
                combinationsOfNumbers.get(target).add(partial);
            }
            else
            {
                combinationsOfNumbers.put(target, new ArrayList<>());
                combinationsOfNumbers.get(target).add(partial);
            }
        }
        if (s >= target) {
            //return;
        }
        for(int i=0;i<numbers.size();i++) {
            ArrayList<Integer> remaining = new ArrayList<Integer>();
            int n = numbers.get(i);
            for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
            ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
            partial_rec.add(n);
            sum_up_recursive(remaining,target,partial_rec);
        }

        System.out.println("Complete");
    }
    static void sum_up(ArrayList<Integer> numbers, int target) {
        sum_up_recursive(numbers,target,new ArrayList<Integer>());
    }


}
