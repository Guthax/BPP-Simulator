package Panels;

import Models.Box;
import Models.HelperClasses.SimulationHandler;
import Models.Package;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ResultBoxesPanel extends JPanel {
    public ResultBoxesPanel() {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Boxes"));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(5000, 300));
        //revalidate();
    }

    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        //Test for 3 boxes

        if(SimulationHandler.simulation.isStarted())
        {

            int x = 10;
            int y = 20;

            int boxwidth = 150;
            int boxheight = 200;
            int packagewidth = 100;

            for(Box box : SimulationHandler.simulation.getBoxes())
            {
                g.setColor(Color.black);
                g.drawRect(x, y, boxwidth, boxheight);

                g.setColor(Color.green);
                for(Package p : box.getPackages())
                {
                    int packageWidth = boxwidth - 5;
                    int packageHeight = boxheight/(SimulationHandler.simulation.getBoxSize()) * p.getSize();
                    g.fillRect(x + 3, y + 5, boxwidth - 5, packageHeight);
                    g.setColor(Color.black);
                    g.drawString((Integer.toString(p.getSize())), x+50, y + (packageHeight/2));
                    g.setColor(Color.blue);
                }
                x += 160;
            }
            /*
            for (int i = 0; i < 30; i++) {
                g.setColor(Color.black);
                g.drawRect(x, y, boxwidth, boxheight);


                g.fillRect(x + 3, y + 5, boxwidth - 5, boxheight - 30);
                x += 160;
                //y+= 205;
            }
*/
            setPreferredSize(new Dimension(x, 200));
            revalidate();
        }
    }
}