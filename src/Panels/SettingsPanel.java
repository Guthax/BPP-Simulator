package Panels;

import Models.Simulation;
import sun.misc.JavaSecurityProtectionDomainAccess;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Settings"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        PackagesPanel panel = new PackagesPanel();


        add(panel);

        add(Box.createRigidArea(new Dimension(10, 30)));

        //Adds setting panels
        BoxSelectionPanel boxSelectionPanel = new BoxSelectionPanel();
        add(boxSelectionPanel);


        AlgorithmSelectionPanel algorithmSelectionPanel = new AlgorithmSelectionPanel();
        add(algorithmSelectionPanel);

        SettingsButtonPanel settingsButtonPanel = new SettingsButtonPanel();
        add(settingsButtonPanel);


    }
}
