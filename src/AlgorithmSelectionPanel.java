import javax.swing.*;
import java.awt.*;

public class AlgorithmSelectionPanel extends JPanel {
    public AlgorithmSelectionPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMaximumSize(new Dimension(500,50));
        add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel algorithmLabel = new JLabel("Algorithms");
        add(algorithmLabel);

        JRadioButton button1 = new JRadioButton("First fit");

        JRadioButton button2 = new JRadioButton("Next fit");

        JRadioButton button3 = new JRadioButton("Jurrias");
        add(button1);
        add(button2);
        add(button3);
    }
}
