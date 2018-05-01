package Panels;

import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.PackagesTable;
import Models.HelperClasses.SimulationHandler;
import Models.Package;
import Models.Simulation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PackagesPanel extends JPanel implements ActionListener {
    JButton packagesButton;
    JButton randomPackageButton;
    JTable currentPackagesTable;

    JLabel numberOfPackages;
    public PackagesPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Packages"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] columnNames = {"Package", "Size"};


        currentPackagesTable = new JTable(new DefaultTableModel(new Object[]{"Package", "Size"}, 1) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });


        DefaultTableModel model = (DefaultTableModel) currentPackagesTable.getModel();
        int counter = 0;
        for(Package packagge : SimulationHandler.simulation.getPackages())
        {
            model.addRow(new Object[]{counter, packagge.getSize()});
            counter++;
        }

        model.removeRow(0);
        //currentPackagesTable.setSize(200,200);

        numberOfPackages = new JLabel(SimulationHandler.simulation.getPackages().size() + "/100");
        packagesButton = new JButton("Add package");
        randomPackageButton = new JButton("Random Package Selection");
        randomPackageButton.addActionListener(this);

        currentPackagesTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(Box.createRigidArea(new Dimension(10, 10)));

        add(new JScrollPane(currentPackagesTable));
        //add(currentPackagesTable);
        add(numberOfPackages);

        //add(currentPackagesTable.getTableHeader(), BorderLayout.AFTER_LINE_ENDS);
        //add(currentPackagesTable, BorderLayout.AFTER_LINE_ENDS);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(packagesButton);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(randomPackageButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == randomPackageButton)
        {
            SimulationHandler.simulation.clearPackages();
            int  n = HelperMethods.getRandom(100);
            for(int i = 0; i < n; i++)
            {
                int n2 = HelperMethods.getRandom(SimulationHandler.simulation.getBoxSize());
                SimulationHandler.simulation.addPackage(new Package(n2));
            }

            DefaultTableModel model = (DefaultTableModel) currentPackagesTable.getModel();
            model.fireTableDataChanged();
            model.setRowCount(0);
            int counter = 0;
            for(Package packagge : SimulationHandler.simulation.getPackages())
            {
                model.addRow(new Object[]{counter, packagge.getSize()});
                counter++;
            }
            numberOfPackages.setText(SimulationHandler.simulation.getPackages().size() + "/100");
        }
    }
}
