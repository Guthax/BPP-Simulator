package Panels;

import Models.HelperClasses.SimulationHandler;
import Models.Simulation;
import com.sun.javafx.collections.NonIterableChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsButtonPanel extends JPanel implements ActionListener {
    JButton startSimulationButton;
    JButton showLogButton;

    public SettingsButtonPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMaximumSize(new Dimension(500,50));
        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        startSimulationButton.addActionListener(this);
        showLogButton = new JButton("Show Log");
        showLogButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(startSimulationButton);
        add(showLogButton);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startSimulationButton)
        {
            SimulationHandler.simulation.start();
            getParent().getParent().repaint();

            ResultStatisticsPanel.numberOfBoxes.setText(Integer.toString(SimulationHandler.simulation.getBoxes().size()));

        }
    }
}
