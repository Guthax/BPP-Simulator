import Models.HelperClasses.SimulationHandler;
import Models.Simulation;
import Panels.AlgorithmResultPanel;
import Panels.PackagesPanel;
import Panels.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationGui extends JFrame implements ActionListener
{

    JButton startSimulationButton;
    JButton showLogButton;

    PackagesPanel packagesPanel;
    public SimulationGui()
    {
        SimulationHandler.simulation = new Simulation();
        setSize(1200,500);
        setTitle("Simulation Bin Packing Problem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,2,10,10));
        setResizable(false);

        SettingsPanel panel = new SettingsPanel();
        add(panel);

        AlgorithmResultPanel algorithmResultPanel = new AlgorithmResultPanel();

        add(algorithmResultPanel);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startSimulationButton)
        {
            SimulationHandler.simulation.start();

        }
    }
}
