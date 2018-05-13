package Panels;

import Models.HelperClasses.SimulationHandler;
import com.sun.xml.internal.ws.api.Component;

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
        //Set spinner to max box size to 20
        SpinnerNumberModel sModel = new SpinnerNumberModel(1, 1, 20, 1);
        boxLengthSelectSpinner = new JSpinner(sModel);
        setMaximumSize(new Dimension(500,50));

        boxLengthSelectSpinner.setSize(50,20);
        add(Box.createRigidArea(new Dimension(10, 10)));
        boxLengthSelectSpinner.setValue(SimulationHandler.simulation.getBoxSize());

        //Checks if no packages are bigger than inserted box size, if this is the case then randomize packages
        //This is so packages are not bigger than the box
        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(SimulationHandler.simulation.getBoxSize() > Integer.parseInt(boxLengthSelectSpinner.getValue().toString()))
                {
                    SimulationHandler.simulation.setBoxSize(Integer.parseInt(boxLengthSelectSpinner.getValue().toString()));
                    SimulationHandler.simulation.RandomizePackages();

                    java.awt.Component[] components = getParent().getComponents();
                    PackagesPanel x = (PackagesPanel)components[0];
                    x.updateTableAndLabelWithSimulationPackages();
                }
                SimulationHandler.simulation.setBoxSize(Integer.parseInt(boxLengthSelectSpinner.getValue().toString()));
            }
        };

        boxLengthSelectSpinner.addChangeListener(listener);
        add(boxLengthSelectSpinner);

    }
}
