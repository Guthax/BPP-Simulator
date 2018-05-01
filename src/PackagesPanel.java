import javax.swing.*;
import java.awt.*;

public class PackagesPanel extends JPanel {
    JButton packagesButton;
    JButton randomPackageButton;
    JTable currentPackagesTable;
    public PackagesPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Packages"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] columnNames = {"Package", "Size"};
        Object[][] data = {{1,2, new Integer(2), new Integer(2)},
                {2,5, new Integer(2), new Integer(2)},
                {3,4, new Integer(2), new Integer(2)},
                {4,5, new Integer(2), new Integer(2)},
                {5,1, new Integer(2), new Integer(2)},
                {6,4, new Integer(2), new Integer(2)}};
        currentPackagesTable = new JTable(data, columnNames);

        //currentPackagesTable.setSize(200,200);

        JLabel numberOfPackagesLabel = new JLabel("100/200");
        packagesButton = new JButton("Add package");
        randomPackageButton = new JButton("Random Package Selection");

        currentPackagesTable.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(10, 10)));
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(currentPackagesTable);
        add(numberOfPackagesLabel);

        //add(currentPackagesTable.getTableHeader(), BorderLayout.AFTER_LINE_ENDS);
        //add(currentPackagesTable, BorderLayout.AFTER_LINE_ENDS);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(packagesButton);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(randomPackageButton);
    }
}
