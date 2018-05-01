package Panels;

import Models.HelperClasses.SimulationHandler;

import javax.swing.*;
import java.awt.*;

public class AlgorithmResultPanel extends JPanel {
    static boolean simulationStarted;
    ResultBoxesPanel panel;
    public AlgorithmResultPanel()
    {
        setSize(200,200);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        ResultStatisticsPanel resultStatisticsPanel = new ResultStatisticsPanel();


        panel = new ResultBoxesPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //scrollPane.setBounds(500, 300, 300, 500);
        add(resultStatisticsPanel);
        add(scrollPane);
        scrollPane.revalidate();
    }


}
