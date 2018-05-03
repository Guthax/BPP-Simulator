package Models;

import Models.HelperClasses.HelperMethods;

import java.util.ArrayList;

public class Box {
    private ArrayList<Package> packages;

    public Box()
    {
        packages = new ArrayList<Package>();
    }

    public ArrayList<Package> getPackages()
    {
        return this.packages;
    }

    public void addPackage(Package p)
    {
        packages.add(p);
    }

    public int getSumOfPackages()
    {
        return HelperMethods.sumOfPackageList(this.packages);
    }
}
