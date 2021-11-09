package edu.luther.cs252.group1.viewcontroller;

import javax.swing.*;

public class ProgramMenuBar extends JMenuBar {

    public ProgramMenuBar() {
        //
        // Create menu items
        //
        JMenu loadMenu = new JMenu("Load");
//        JMenu editMenu = new JMenu("Edit"); // What purpose would this serve? -adam
        JMenu runMenu = new JMenu("Run");
        JMenu pauseMenu = new JMenu("Pause");
        JMenu stopMenu = new JMenu("Stop");
        JMenu fileNameLabel = new JMenu("Enter File Name:");

        //
        // TextField Takes up to 30 characters for a given file name input.
        //
        JTextField fileNameField = new JTextField(100);
        JTextField runDelayField = new JTextField("Delay", 10);

        //
        // Set help tooltips
        //
        loadMenu.setToolTipText("Load a vm252 object file");
//        editMenu.setToolTipText("???"); // what purpose does this serve? -adam
        runMenu.setToolTipText("Run program until breakpoint reached");
        pauseMenu.setToolTipText("Pause the execution of the program");
        stopMenu.setToolTipText("Stop the execution of the program");
        fileNameLabel.setToolTipText("Enter a name to load a vm252 object file");
        runDelayField.setToolTipText("Delay executing each instruction of the program");


        add(loadMenu);
//        add(editMenu);
        add(fileNameLabel);
        add(fileNameField);
        add(runMenu);
        add(runDelayField);
        add(pauseMenu);
        add(stopMenu);
        JMenuItem m11 = new JMenuItem("Open");
        loadMenu.add(m11);
    }

}
