import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ResultBoxesPanel extends JPanel {
    public ResultBoxesPanel() {

        setLayout(new GridLayout());
        setPreferredSize(new Dimension(700, 600));
    }

    public void paint(Graphics g) {
        //Test for 3 boxes


        int x = 0;
        int y = 0;

        int boxwidth = 150;
        int boxheight = 200;
        int packagewidth = 100;
        for (int i = 0; i < 30; i++) {
            g.setColor(Color.black);
            g.drawRect(x, y, boxwidth, boxheight);

            g.setColor(Color.green);
            g.fillRect(x + 3, y + 5, boxwidth - 5, boxheight - 30);
            x += 160;
            //y+= 205;
        }


    }
}