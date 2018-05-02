package Models;

import Models.Algorithms.BestFit;
import Models.Algorithms.FirstFit;
import Models.Algorithms.Jurrias;
import Models.Algorithms.NextFit;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Algorithm {
    private String name;

    public Algorithm(String name)
    {
        this.name = name;

    }
    public abstract ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packages);

    public static ArrayList<Algorithm> initalizeAlgorthms()
    {
        ArrayList<Algorithm> algorithms = new ArrayList<Algorithm>();

        FirstFit firstFit = new FirstFit();
        NextFit nextFit = new NextFit();
        BestFit bestFit = new BestFit();
        Jurrias jurrias = new Jurrias();
        algorithms.add(firstFit);
        algorithms.add(nextFit);
        algorithms.add(bestFit);
        algorithms.add(jurrias);

        return algorithms;
    }

    public String getName()
    {
        return name;
    }
}
