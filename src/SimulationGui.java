import javax.swing.*;
import java.awt.*;

public class SimulationGui extends JFrame
{

    PackagesPanel packagesPanel;
    public SimulationGui()
    {
        setSize(1200,800);
        setTitle("Simulation Bin Packing Problem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,2));

        SettingsPanel panel = new SettingsPanel();
        add(panel);
        //packagesPanel = new PackagesPanel();
        //packagesPanel.setSize(200,200);
        //add(packagesPanel, BorderLayout.WEST);

        //BoxSelectionPanel boxSelectionPanel = new BoxSelectionPanel();
        //boxSelectionPanel.setSize(200,200);
        //add(boxSelectionPanel,BorderLayout.WEST,0);
    }
}
