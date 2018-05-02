package Panels;

import Models.HelperClasses.SimulationHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BoxSelectionPanel extends JPanel {
    JSpinner boxLengthSelectSpinner;
    public BoxSelectionPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Box size"));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        boxLengthSelectSpinner = new JSpinner();
        setMaximumSize(new Dimension(500,50));

        boxLengthSelectSpinner.setSize(50,20);
        add(Box.createRigidArea(new Dimension(10, 10)));
        boxLengthSelectSpinner.setValue(SimulationHandler.simulation.getBoxSize());

        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                SimulationHandler.simulation.setBoxSize(Integer.parseInt(boxLengthSelectSpinner.getValue().toString()));
            }
        };

        boxLengthSelectSpinner.addChangeListener(listener);
        add(boxLengthSelectSpinner);

    }
}