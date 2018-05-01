package Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Algorithm {
    private String name;

    public Algorithm()
    {

    }
    public abstract ArrayList<Box> RunAlgorithm(int boxsize, ArrayList<Package> packages);
}
