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
        //Starts simulation, resets log and generates code
        if(e.getSource() == startSimulationButton)
        {
            SimulationHandler.simulation.generateCode();
            SimulationHandler.simulation.reset();
            //Adds start events
            SimulationHandler.simulation.getLog().addEvent(
                    new Event("Simulation code: " + SimulationHandler.simulation.getCode()
                            , 0, EventType.Result));
            SimulationHandler.simulation.getLog().addEvent(
                    new Event("Algorithm: " + SimulationHandler.simulation.getAlgorithm().getName()
                            , 0, EventType.Result));

            //Before another simulation can be started, the log screen must be disposed
            if(logFrame != null)
            {
                logFrame.dispose();
            }
            //Measure total execution time
            long startTime = System.nanoTime();
            SimulationHandler.simulation.start();
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            duration = TimeUnit.MICROSECONDS.convert(duration, TimeUnit.NANOSECONDS);//In microsecondsonds

            //Adds complete event
            SimulationHandler.simulation.getLog().addEvent(
                    new Event("Completed simulation"
                            , duration, EventType.Result));

            getParent().getParent().repaint();

            //Updates number of boxes used label
            ResultStatisticsPanel.numberOfBoxes.setText(Integer.toString(SimulationHandler.simulation.getBoxes().size()));

        }

        if(e.getSource() == showLogButton)
        {
            //Opens log frame
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
