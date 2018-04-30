import javax.swing.*;
import java.awt.*;

public class SettingsButtonPanel extends JPanel {
    JButton startSimulationButton;
    JButton showLogButton;
    public SettingsButtonPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMaximumSize(new Dimension(500,50));
        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        showLogButton = new JButton("Show Log");
        showLogButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(startSimulationButton);
        add(showLogButton);
        
    }
}
