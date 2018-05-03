package Models;

import Models.Algorithms.NextFit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Simulation {
    private String code;
    private int boxSize;
    private ArrayList<Box> boxes;
    private  ArrayList<Package> packages;
    private boolean started;
    private Algorithm algorithm;
    private Log log;

    public Simulation()
    {
        boxSize = 5;
        started = false;
        packages = new ArrayList<Package>();
        boxes = new ArrayList<Box>();
        packages.add(new Package(2));
        packages.add(new Package(5));
        packages.add(new Package(4));
        packages.add(new Package(1));
        log = new Log();
        generateCode();


    }

    public void reset()
    {
        log = new Log();

    }

    public String getCode()
    {
        return this.code;
    }
    public void generateCode()
    {
        this.code = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public void start()
    {

        ArrayList<Package> backup = new ArrayList<Package>();
        backup = this.packages;

        this.boxes = this.algorithm.RunAlgorithm(this.boxSize, this.packages);
        started = true;

        this.packages = backup;

    }

    public int getBoxSize()
    {
        return this.boxSize;
    }

    public void setBoxSize(int boxSize) {
        this.boxSize = boxSize;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void addPackage(Package pack)
    {
        this.packages.add(pack);
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void clearPackages()
    {
        packages.clear();
    }

    public Algorithm getAlgorithm()
    {
        return this.algorithm;
    }
    public void setAlgorithm(Algorithm algorithm)
    {
        this.algorithm = algorithm;
    }

    public Log getLog()
    {
        return log;
    }
}

