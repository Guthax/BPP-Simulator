package Models;

import Models.Algorithms.NextFit;

import java.util.ArrayList;

public class Simulation {
    private int boxSize;
    private ArrayList<Box> boxes;
    private  ArrayList<Package> packages;
    private boolean started;
    private Algorithm algorithm;

    public Simulation()
    {
        boxSize = 5;
        started = false;
        packages = new ArrayList<Package>();
        packages.add(new Package(2));
        packages.add(new Package(5));
        packages.add(new Package(4));
        packages.add(new Package(1));
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

    public void setAlgorithm(Algorithm algorithm)
    {
        this.algorithm = algorithm;
    }
}

