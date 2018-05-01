package Models.Algorithms;

import Models.Algorithm;
import Models.Box;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;

import java.util.ArrayList;

public class NextFit extends Algorithm {
    private String naam;

    public NextFit()
    {
        this.naam = "Next fit";
    }

    @Override
    public ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packages) {
        ArrayList<Box> result = new ArrayList<Box>();
        result.add(new Box());
        int counter = 0;
        while(packages.size() > 0)
        {
            int boxIndex = counter - 1;
            if(counter <= 0) {
                boxIndex = counter;
            }
            if((HelperMethods.sumOfPackageList(result.get(boxIndex).getPackages()) + packages.get(0).getSize()) <= SimulationHandler.simulation.getBoxSize())
            {
                result.get(boxIndex).addPackage(packages.get(0));
            }
            else
            {
                Box b = new Box();
                b.addPackage(packages.get(0));
                result.add(b);
            }
            packages.remove(0);
        }
        return result;
    }


}
