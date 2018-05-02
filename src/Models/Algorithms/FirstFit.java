package Models.Algorithms;

import Models.Algorithm;
import Models.Box;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;

import java.util.ArrayList;

public class FirstFit extends Algorithm {

    public FirstFit()
    {
        super("First fit");
    }
    @Override
    public ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packages) {
        ArrayList<Box> result = new ArrayList<Box>();
        result.add(new Box());

        for(int i = 0; i < packages.size(); i++)
        {
            boolean doesFit = false;
            for(Box box : result)
            {
                if(!doesFit) {
                    if (packages.get(i).getSize() + HelperMethods.sumOfPackageList(box.getPackages()) <= SimulationHandler.simulation.getBoxSize()) {
                        box.addPackage(packages.get(i));
                        doesFit = true;
                    }
                }
            }
            if(!doesFit)
            {
               Box b = new Box();
               b.addPackage(packages.get(i));
               result.add(b);

            }
        }
        return result;
    }
}
