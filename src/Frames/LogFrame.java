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

        String results = "";
        String steps = "";

        for(Event event : SimulationHandler.simulation.getLog().getEvents())
        {
            if(event.getType() == EventType.Result)
            {
                results += event.getDescription();
                if(event.getTime() != 0)
                {
                    results +=": Time: " + event.getTime() + " microseconds" + System.lineSeparator();
                }
                else
                {
                    results += System.lineSeparator();
                }
            }
            if(event.getType() == EventType.Step)
            {
                steps += event.getDescription();
                if(event.getTime() != 0)
                {
                    steps +=": Time: " + event.getTime() + " microseconds " + System.lineSeparator();
                }
                else
                {
                    steps += System.lineSeparator();
                }
            }

            if(event.getTime() != 0)
            {

            }

        }
        totalLog = "RESULT" + System.lineSeparator() + "----------------------------- " + System.lineSeparator()
                + results + System.lineSeparator() +   "----------------------------- " + System.lineSeparator()
                + "STEPS" + System.lineSeparator() +   "----------------------------- " + System.lineSeparator() + steps;
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
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(totalLog);
                printWriter.close();
            } catch(java.io.IOException ex) {
                System.out.println("Error message");
            }
        }

    }
}
