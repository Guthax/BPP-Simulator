package Panels;

import Models.Algorithm;
import Models.Algorithms.FirstFit;
import Models.Algorithms.Jurrias;
import Models.HelperClasses.SimulationHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

public class AlgorithmSelectionPanel extends JPanel {
    ButtonGroup group;
    JRadioButton firstFitRadioButton;
    JRadioButton jurriasButton;
    public AlgorithmSelectionPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Algorithms"));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMaximumSize(new Dimension(500,50));
        add(Box.createRigidArea(new Dimension(0, 10)));


        //Gets all algorithms
        ArrayList<Algorithm> algorithms = Algorithm.initalizeAlgorthms();

        //Created group
        group = new ButtonGroup();

        //Adds first fit radionbutton to group
        firstFitRadioButton = new JRadioButton("First fit");
        firstFitRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Sets button to selected and sets algorithm for simulation
                SimulationHandler.simulation.setAlgorithm(new FirstFit());
                firstFitRadioButton.setSelected(true);
            }
        });
        //Adds first fit radionbutton to group and set algorithm for simulation
        group.add(firstFitRadioButton);
        add(firstFitRadioButton);
        SimulationHandler.simulation.setAlgorithm(new FirstFit());

        //Get all other algorithms and creates radiobuttons and adds them to group
        for(Algorithm algorithm : algorithms)
        {
            if(algorithm.getClass() != Jurrias.class && algorithm.getClass() != FirstFit.class) {
                JRadioButton x = new JRadioButton(algorithm.getName());
                x.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Sets button to selected and sets algorithm for simulation
                        SimulationHandler.simulation.setAlgorithm(algorithm);
                        x.setSelected(true);
                    }
                });
                group.add(x);
                add(x);
            }
        }
        //Adds jurrias button to panel and group
        jurriasButton = new JRadioButton("Jurrias");
        jurriasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationHandler.simulation.setAlgorithm(new Jurrias());
                jurriasButton.setSelected(true);
            }
        });

        firstFitRadioButton.setSelected(true);

        //First fit radiobutton and jurrias button are added outside the for loop because they need to be selected by the program instead of the user
        group.add(jurriasButton);
        add(jurriasButton);

    };


    public void updateJurriasButtonAccordingToPackages()
    {

        //Updates the radiobuttons if the amount of packages inside the simulation is > 10, this is because jurrias takes too long for > 10 packages
        if (SimulationHandler.simulation.getPackages().size() > 10) {

                jurriasButton.setSelected(false);
                jurriasButton.setEnabled(false);
                firstFitRadioButton.setSelected(true);
                SimulationHandler.simulation.setAlgorithm(new FirstFit());



        }
        else
        {
            jurriasButton.setEnabled(true);
        }

    }
}
