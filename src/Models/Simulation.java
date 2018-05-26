package Models;

import Models.Algorithms.NextFit;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Simulation {
    //Code of simulation
    private String code;
    //Box size to be simulated
    private int boxSize;
    //Result of boxes filled with packages
    private ArrayList<Box> boxes;
    //Packages to be simulated
    private  ArrayList<Package> packages;
    //Is simulation started
    private boolean started;
    //Algorithm to be used
    private Algorithm algorithm;
    //Log to be filled during simulation
    private Log log;

    public Simulation()
    {
        //Initialize values
        boxSize = 10;
        started = false;
        packages = new ArrayList<Package>();
        boxes = new ArrayList<Box>();

        //Set start values according to research
        /*
        packages.add(new Package(4));
        packages.add(new Package(8));
        packages.add(new Package(5));
        packages.add(new Package(1));
        packages.add(new Package(7));
        packages.add(new Package(6));
        packages.add(new Package(1));
        packages.add(new Package(4));
        packages.add(new Package(2));
        packages.add(new Package(2));
        */
        packages.add(new Package(5));
        packages.add(new Package(3));
        packages.add(new Package(2));
        packages.add(new Package(2));
        packages.add(new Package(3));
        packages.add(new Package(2));
        log = new Log();
        generateCode();


    }

    //Resets the log, this clears all events
    public void reset()
    {
        log = new Log();

    }

    //Gets the code of the simulation
    public String getCode()
    {
        return this.code;
    }

    //Generates code for simulation according to date time stamp
    public void generateCode()
    {
        this.code = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    //Starts the simulation and runs algorithm
    public void start()
    {


        this.boxes = this.algorithm.RunAlgorithm(this.boxSize, this.packages);
        started = true;

    }

    //Returns box size
    public int getBoxSize()
    {
        return this.boxSize;
    }

    //Sets box size
    public void setBoxSize(int boxSize) {
        this.boxSize = boxSize;
    }

    //Gets all boxes
    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    //Adds package
    public void addPackage(Package pack)
    {
        this.packages.add(pack);
    }

    //Gets all packages that need to be simulated
    public ArrayList<Package> getPackages() {
        return packages;
    }

    //Gets if simulation is started
    public boolean isStarted()
    {
        return started;
    }

    //Clears all packages
    public void clearPackages()
    {
        packages.clear();
    }

    //Gets algorithm to be used
    public Algorithm getAlgorithm()
    {
        return this.algorithm;
    }

    //Setes algorithm to be used
    public void setAlgorithm(Algorithm algorithm)
    {
        this.algorithm = algorithm;
    }

    //Gets log that is being kept by
    public Log getLog()
    {
        return log;
    }

    //Randomizes packages that need to be simulated
    public void RandomizePackages()
    {
        clearPackages();
        int  n = HelperMethods.getRandom(1,100);
        for(int i = 0; i < n; i++)
        {
            int n2 = HelperMethods.getRandom(1,getBoxSize());
            addPackage(new Package(n2));
        }

    }
}

