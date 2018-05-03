package Panels;

import Models.HelperClasses.HelperMethods;
import Models.HelperClasses.PackagesTable;
import Models.HelperClasses.SimulationHandler;
import Models.Package;
import Models.Simulation;
import javafx.beans.property.SimpleListProperty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PackagesPanel extends JPanel implements ActionListener {
    JButton packagesButton;
    JButton randomPackageButton;
    JButton clearPackages;

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
                return false;
            }
        });


        numberOfPackages = new JLabel("");
        updateTableAndLabelWithSimulationPackages();

        packagesButton = new JButton("Add package");
        packagesButton.addActionListener(this);
        randomPackageButton = new JButton("Random Package Selection");
        randomPackageButton.addActionListener(this);
        clearPackages = new JButton("Clear package list");
        clearPackages.addActionListener(this);

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
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(clearPackages);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == randomPackageButton)
        {
            SimulationHandler.simulation.RandomizePackages();

            updateTableAndLabelWithSimulationPackages();
        }

        if(e.getSource() == packagesButton)
        {
            SpinnerNumberModel sModel = new SpinnerNumberModel(1, 1, SimulationHandler.simulation.getBoxSize(), 1);
            JSpinner spinner = new JSpinner(sModel);
            //JOptionPane.showMessageDialog(null, spinner);
            int option = JOptionPane.showOptionDialog(null, spinner, "Add new package", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.CANCEL_OPTION)
            {

                // user hit cancel
            } else if (option == JOptionPane.OK_OPTION)
            {
                SimulationHandler.simulation.addPackage(new Package(Integer.parseInt(spinner.getValue().toString())));
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
                // user entered a number
            }
        }

        if(e.getSource() == clearPackages)
        {
            SimulationHandler.simulation.clearPackages();
            updateTableAndLabelWithSimulationPackages();
        }
    }

    public void updateTableAndLabelWithSimulationPackages()
    {
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
