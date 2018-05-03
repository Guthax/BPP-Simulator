package Panels;

import Frames.LogFrame;
import Models.Event;
import Models.EventType;
import Models.HelperClasses.SimulationHandler;
import Models.Simulation;
import com.sun.javafx.collections.NonIterableChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class SettingsButtonPanel extends JPanel implements ActionListener {
    JButton startSimulationButton;
    JButton showLogButton;
    LogFrame logFrame;
    public SettingsButtonPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMaximumSize(new Dimension(500,50));
        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        startSimulationButton.addActionListener(this);
        showLogButton = new JButton("Show Log");
        showLogButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        showLogButton.addActionListener(this);
        add(startSimulationButton);
        add(showLogButton);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startSimulationButton)
        {
            SimulationHandler.simulation.generateCode();
            SimulationHandler.simulation.reset();
            SimulationHandler.simulation.getLog().addEvent(
                    new Event("Simulation code: " + SimulationHandler.simulation.getCode()
                            , 0, EventType.Result));
            SimulationHandler.simulation.getLog().addEvent(
                    new Event("Algorithm: " + SimulationHandler.simulation.getAlgorithm().getName()
                            , 0, EventType.Result));

            if(logFrame != null)
            {
                logFrame.dispose();
            }
            long startTime = System.nanoTime();
            SimulationHandler.simulation.start();
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In microsecondsonds

            SimulationHandler.simulation.getLog().addEvent(
                    new Event("Completed simulation"
                            , duration, EventType.Result));

            getParent().getParent().repaint();

            ResultStatisticsPanel.numberOfBoxes.setText(Integer.toString(SimulationHandler.simulation.getBoxes().size()));

        }

        if(e.getSource() == showLogButton)
        {
            if(logFrame == null) {
                logFrame = new LogFrame();
                logFrame.setVisible(true);
            }
            else
            {
                if(logFrame.isDisplayable() == false) {
                    logFrame = new LogFrame();
                    logFrame.setVisible(true);
                }
            }
        }
    }
}
