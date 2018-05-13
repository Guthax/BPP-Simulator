package Models;

import Models.HelperClasses.HelperMethods;

import java.util.ArrayList;

public class Box {
    //Packages inside box
    private ArrayList<Package> packages;

    //Intializes packages array
    public Box()
    {
        packages = new ArrayList<Package>();
    }

    //Gets packages inside box
    public ArrayList<Package> getPackages()
    {
        return this.packages;
    }

    //Adds package to box
    public void addPackage(Package p)
    {
        packages.add(p);
    }

    //Gets sum of all packages inside box
    public int getSumOfPackages()
    {
        return HelperMethods.sumOfPackageList(this.packages);
    }
}
