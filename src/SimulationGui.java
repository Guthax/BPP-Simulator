import javax.swing.*;
import java.awt.*;

public class SimulationGui extends JFrame
{

    PackagesPanel packagesPanel;
    public SimulationGui()
    {
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
}
