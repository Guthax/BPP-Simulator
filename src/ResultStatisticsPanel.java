import javax.swing.*;
import java.awt.*;

public class ResultStatisticsPanel extends JPanel {
    JLabel numberOfBoxes;
    public ResultStatisticsPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Statistics"));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel numberOfBoxesLabel = new JLabel("Number of boxes used: ");
        numberOfBoxes = new JLabel("5");

        add(numberOfBoxesLabel);
        add(numberOfBoxes);

    }
}
