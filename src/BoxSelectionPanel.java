import javax.swing.*;
import java.awt.*;

public class BoxSelectionPanel extends JPanel {
    JSpinner boxLengthSelectSpinner;
    public BoxSelectionPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        boxLengthSelectSpinner = new JSpinner();
        setMaximumSize(new Dimension(500,50));

        boxLengthSelectSpinner.setSize(50,20);
        JLabel label = new JLabel("Box size");
        add(Box.createRigidArea(new Dimension(0, 10)));

        add(label);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(boxLengthSelectSpinner);

    }
}
