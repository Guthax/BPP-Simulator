package Panels;

import Models.HelperClasses.SimulationHandler;

import javax.swing.*;
import java.awt.*;

public class ResultStatisticsPanel extends JPanel {
    JLabel numberOfBoxes;
    public ResultStatisticsPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Statistics"));
        this.setMaximumSize(new Dimension(600,50));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel numberOfBoxesLabel = new JLabel("Number of boxes used: ");
        numberOfBoxes = new JLabel(Integer.toString(SimulationHandler.simulation.getPackages().size()));

        add(numberOfBoxesLabel);
        add(numberOfBoxes);

    }
}
