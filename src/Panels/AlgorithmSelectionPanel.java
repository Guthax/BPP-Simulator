package Panels;

import Models.Algorithm;
import Models.HelperClasses.SimulationHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AlgorithmSelectionPanel extends JPanel {
    public AlgorithmSelectionPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Algorithms"));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMaximumSize(new Dimension(500,50));
        add(Box.createRigidArea(new Dimension(0, 10)));


        ArrayList<Algorithm> algorithms = Algorithm.initalizeAlgorthms();

        ButtonGroup group = new ButtonGroup();

        for(Algorithm algorithm : algorithms)
        {
            JRadioButton x = new JRadioButton(algorithm.getName());
            x.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SimulationHandler.simulation.setAlgorithm(algorithm);
                    x.setSelected(true);
                }
            });
            group.add(x);
            add(x);
        }
        /*
        JRadioButton button1 = new JRadioButton("Next fit");

        JRadioButton button2 = new JRadioButton("First fit");

        JRadioButton button3 = new JRadioButton("Jurrias");

        group.add(button1);
        group.add(button2);
        group.add(button3);

        button1.setSelected(true);
        add(button1);
        add(button2);
        add(button3);
        */
    }
}
