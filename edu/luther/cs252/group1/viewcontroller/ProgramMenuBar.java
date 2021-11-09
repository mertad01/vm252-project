package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;

public class ProgramMenuBar extends JMenuBar implements BasicObserver {

    private VirtualMachine252 vm252;
    private JTextField fileNameField;
    private JTextField runDelayField;

    //
    // Constructor
    //
    public ProgramMenuBar(VirtualMachine252 vm252) {

        this.vm252 = vm252;

        //
        // Create menu items
        //
        JMenu loadMenu = new JMenu("Load");
        JMenu runMenu = new JMenu("Run");
        JMenu pauseMenu = new JMenu("Pause");
        JMenu stopMenu = new JMenu("Stop");
        JMenu fileNameLabel = new JMenu("Enter File Name:");

        //
        // TextField Takes up to 30 characters for a given file name input.
        //
        fileNameField = new JTextField(100);
        runDelayField = new JTextField("Delay", 10);

        //
        // Set help tooltips
        //
        loadMenu.setToolTipText("Load a vm252 object file");
        runMenu.setToolTipText("Run program until breakpoint reached");
        pauseMenu.setToolTipText("Pause the execution of the program");
        stopMenu.setToolTipText("Stop the execution of the program");
        fileNameLabel.setToolTipText("Enter a name to load a vm252 object file");
        runDelayField.setToolTipText("Delay executing each instruction of the program");


        //
        // Add components to the menu bar
        //
        add(loadMenu);
        add(fileNameLabel);
        add(fileNameField);
        add(runMenu);
        add(runDelayField);
        add(pauseMenu);
        add(stopMenu);
        JMenuItem m11 = new JMenuItem("Open");
        loadMenu.add(m11);
    }

    @Override
    public void update() {
        // TODO: implement when model is integrated
    }
}
