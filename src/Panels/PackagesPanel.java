package Panels;

import Models.HelperClasses.SimulationHandler;
import Models.Package;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        //Updates table with packages
        numberOfPackages = new JLabel("");
        updateTableAndLabelWithSimulationPackages();

        //Intializes buttons
        packagesButton = new JButton("Add package");
        packagesButton.addActionListener(this);
        randomPackageButton = new JButton("Random Package Selection");
        randomPackageButton.addActionListener(this);
        clearPackages = new JButton("Clear package list");
        clearPackages.addActionListener(this);

        currentPackagesTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(Box.createRigidArea(new Dimension(10, 10)));

        //Adds scrolplane to table so scrolling is possible
        add(new JScrollPane(currentPackagesTable));
        add(numberOfPackages);

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
            //Opens dialog for new package
            SpinnerNumberModel sModel = new SpinnerNumberModel(1, 1, SimulationHandler.simulation.getBoxSize(), 1);
            JSpinner spinner = new JSpinner(sModel);
            int option = JOptionPane.showOptionDialog(null, spinner, "Add new package", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.OK_OPTION)
            {
                //Adds package to simulation packages
                SimulationHandler.simulation.addPackage(new Package(Integer.parseInt(spinner.getValue().toString())));
                //Updates table
                updateTableAndLabelWithSimulationPackages();
            }
        }

        if(e.getSource() == clearPackages)
        {
            //Clears packages
            SimulationHandler.simulation.clearPackages();
            //Updates table
            updateTableAndLabelWithSimulationPackages();
        }
    }

    public void updateTableAndLabelWithSimulationPackages()
    {
        DefaultTableModel model = (DefaultTableModel) currentPackagesTable.getModel();
        model.fireTableDataChanged();
        model.setRowCount(0);
        int counter = 0;
        //Get packages and update table
        for(Package packagge : SimulationHandler.simulation.getPackages())
        {
            model.addRow(new Object[]{counter, packagge.getSize()});
            counter++;
        }
        //Updates label underneath table
        numberOfPackages.setText(SimulationHandler.simulation.getPackages().size() + "/100");

        //If  packages > 0 then call method to disable jurrias radiobutton
        if(getParent() != null) {
            AlgorithmSelectionPanel x = ((AlgorithmSelectionPanel) getParent().getComponents()[3]);
            if (x != null) {
                x.updateJurriasButtonAccordingToPackages();
            }
        }
    }
}
