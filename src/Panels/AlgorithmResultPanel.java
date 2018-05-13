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


        //Adds panel for statistics(number of boxes used)
        ResultStatisticsPanel resultStatisticsPanel = new ResultStatisticsPanel();


        //Adds panel which visualises boxes
        panel = new ResultBoxesPanel();

        //Adds panel to scrollpane so horizontal scrolling is possible
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //scrollPane.setBounds(500, 300, 300, 500);

        //Adds panels
        add(resultStatisticsPanel);
        add(scrollPane);
        scrollPane.revalidate();
    }


}
