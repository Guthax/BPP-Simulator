import javax.swing.*;
import java.awt.*;

public class AlgorithmResultPanel extends JPanel {
    public AlgorithmResultPanel()
    {
        setSize(200,200);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ResultBoxesPanel panel = new ResultBoxesPanel();

        panel.setMaximumSize(new Dimension(600,200));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(500, 300, 300, 500);


        add(scrollPane);
    }

}
