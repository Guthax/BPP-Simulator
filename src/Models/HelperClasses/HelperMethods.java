package Models.HelperClasses;

import Models.Package;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class HelperMethods {
    public static int getRandom(int min, int max){
        // return (int) (Math.random()*max);  //incorrect always return zero
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

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
