package Panels;

import Models.Box;
import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.SimulationHandler;
import Models.Package;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ResultBoxesPanel extends JPanel {
    Map<Integer, Color> colors = new HashMap<Integer, Color>();

    public ResultBoxesPanel() {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Boxes"));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(5000, 300));
    }

    public void paintComponent(Graphics g) {


        super.paintComponent(g);

        if(colors.size() < SimulationHandler.simulation.getBoxSize())
        {
            fillColorsWithRandomValuesAccordingToBoxSize();
        }

        if(SimulationHandler.simulation.isStarted())
        {

            int x = 10;
            int y = 20;

            int boxwidth = 150;
            int boxheight = 350;
            int packagewidth = 100;

            for(Box box : SimulationHandler.simulation.getBoxes())
            {
                g.setColor(Color.black);
                g.drawRect(x, y, boxwidth, boxheight);


                int packageWidth = boxwidth - 5;

                int previousPackageHeight = 0;
                for(Package p : box.getPackages())
                {
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
                    g.setColor(colors.get(p.getSize()));
                    int packageHeight = Math.round(boxheight * ((float)p.getSize()/(float)SimulationHandler.simulation.getBoxSize())) - 3;
                    g.fillRect(x + 3, y+2, packageWidth, packageHeight);
                    g.setColor(Color.blue);
                    //g.drawRect(x + 3, y + previousPackageHeight, packageWidth, packageHeight);
                    if(Math.round((float)p.getSize() / (float)SimulationHandler.simulation.getBoxSize() * 100) > 10) {
                        //g.drawString((Integer.toString(p.getSize())), x + 65, y + previousPackageHeight + (packageHeight / 2));
                    }
                    g.drawString((Integer.toString(p.getSize())), x + 65, y + (packageHeight / 2));
                    y+= packageHeight;
                }
                y = 20;
                x += 160;
            }

            setPreferredSize(new Dimension(x, 200));
            revalidate();
        }
    }

    private void fillColorsWithRandomValuesAccordingToBoxSize()
    {
        //Gets a random RGB value for every packagesize
        for(int i = 0; i < SimulationHandler.simulation.getBoxSize() + 1; i++)
        {

            int red = HelperMethods.getRandom(0, 255);
            int green = HelperMethods.getRandom(0, 255);
            int blue = HelperMethods.getRandom(0, 255);

            while(Color.getHSBColor(red,green,blue) == Color.black)
            {
                red = HelperMethods.getRandom(0, 255);
                green = HelperMethods.getRandom(0, 255);
                blue = HelperMethods.getRandom(0, 255);
            }

            colors.put(i, Color.getHSBColor(red,green,blue));
        }

    }
}