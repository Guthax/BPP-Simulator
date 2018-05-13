package Models.HelperClasses;

import Models.Package;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class HelperMethods {
    //Gets random number between min and max
    public static int getRandom(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    //Takes a list of packages and returns the sum of all the packages in it
    public static int sumOfPackageList(ArrayList<Package> packages)
    {
        int sum = 0;
        for(int i = 0; i < packages.size(); i++)
        {
            sum = sum + packages.get(i).getSize();
        }
        return sum;
    }



}
