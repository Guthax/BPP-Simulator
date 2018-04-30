import javax.swing.*;
import java.awt.*;

public class BoxSelectionPanel extends JPanel {
    JSpinner boxLengthSelectSpinner;
    public BoxSelectionPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(200,50);
        setPreferredSize(new Dimension(200,50));
        boxLengthSelectSpinner = new JSpinner();
        add(boxLengthSelectSpinner);

    }
}
