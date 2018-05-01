import Models.Algorithms.NextFit;
import Models.Box;
import Models.HelperClasses.SimulationHandler;
import Models.Simulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    static Simulation simulation;
    public static void main(String[] args)
    {


        Simulation simulation = new Simulation();

        SimulationGui gui = new SimulationGui();
        gui.setVisible(true);

        NextFit f = new NextFit();
        ArrayList<Box> result =  f.RunAlgorithm(SimulationHandler.simulation.getBoxSize(), SimulationHandler.simulation.getPackages());
        System.out.println("gedaan");
        /*
        JFrame frame = new JFrame();
        Panels.ResultBoxesPanel panel = new Panels.ResultBoxesPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 300, 50);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        scrollPane.revalidate();
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);*/

    }
}
