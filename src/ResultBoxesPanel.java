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


        int x = 10;
        int y = 10;

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

        setPreferredSize(new Dimension(x, 200));
        revalidate();



    }
}