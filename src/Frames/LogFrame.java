package Frames;

import Models.Event;
import Models.EventType;
import Models.HelperClasses.SimulationHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LogFrame extends JFrame implements ActionListener {
    JButton exportLog;
    String totalLog;
    public LogFrame()
    {
        setTitle("Log");
        setSize(600,400);
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.X_AXIS)
        );
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        totalLog = "";

        exportLog = new JButton("Export");
        exportLog.addActionListener(this);
        JTextArea field = new JTextArea();
        field.setText("");
        JScrollPane pane = new JScrollPane(field);

        field.setEditable(false);

        totalLog = SimulationHandler.simulation.getLog().GetExportString();
        field.setText(totalLog);
        add(pane);
        add(exportLog);
    }

    public void clearLog()
    {
        this.totalLog = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Opens file chooser so its possible to choose where the log results will be stored.
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                //Convert filepath to File object
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                PrintWriter printWriter = new PrintWriter(fileWriter);
                //Print the log results to the file
                printWriter.print(totalLog);
                //Close the filewriter
                printWriter.close();
            } catch(java.io.IOException ex) {
                System.out.println("Error message");
            }
        }

    }
}
