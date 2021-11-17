package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProgramMenuBar extends JMenuBar implements BasicObserver {
    private AtomicBoolean paused;
    private VirtualMachine252 vm252;
    private JTextField fileNameField;
    private JTextField runDelayField;
    private Thread threadObject;

    //
    // Constructor
    //
    public ProgramMenuBar(VirtualMachine252 vm252) {

        this.vm252 = vm252;

        //
        // Create menu items
        //

        paused = new AtomicBoolean(false);
        JMenu helpMenu = new JMenu("Help");
        JMenu loadMenu = new JMenu("File");
        JMenu runMenu = new JMenu("Run");
        JMenu stopMenu = new JMenu("Stop");
        JMenu fileNameLabel = new JMenu("File Name:");

        //
        // TextField, fileNameField's, width in columns is 100.
        //
        fileNameField = new JTextField(100);
        runDelayField = new JTextField("Delay", 10);

        //
        // Set the textfield as read-only
        //

        fileNameField.setEditable(false);

        runDelayField = new JTextField("Delay", 10);

        //
        // Set help tooltips
        //

        helpMenu.setToolTipText("Spawn a help dialog explaining the application");
        loadMenu.setToolTipText("Load a vm252 object file");
        runMenu.setToolTipText("Run program until breakpoint reached");
        stopMenu.setToolTipText("Stop the execution of the program");
        fileNameLabel.setToolTipText("The name of the loaded vm252 object file");
        runDelayField.setToolTipText("Delay executing each instruction of the program");


        //
        // Add components to the menu bar
        //

        //
        // Adding the Help menu item separated into quick tips and a full commandlist
        // that appear on button click
        //

        add(helpMenu);
        JMenuItem quick_tips = new JMenuItem("Quick Tip");
        JMenuItem command_list = new JMenuItem("Command List");
        helpMenu.add(quick_tips);
        helpMenu.add(command_list);
        quick_tips.addActionListener(actionEvent -> JOptionPane.showMessageDialog(getRootPane(),
                "To receive fast help for the program you can hover over a component and read the tooltip."
                        + "Otherwise you can view the full command list for all program controls and inputs"));
        command_list.addActionListener(actionEvent -> JOptionPane.showMessageDialog(getRootPane(),
                "n button: Run Next Program Instruction \n\n"
                        + "Load: Load a vm252 object file \n\nRun: Run program until breakpoint reached \n\n"
                        + "Pause: Pause the execution of the program \n\nStop: Stop the execution of the program \n\n"
                        + "File Name: The name of the loaded vm252 object file \n\n"
                        + "Delay: Delay executing each instruction of the program \n\n"
                        + "Input/Output: Enter input or receive output here \n\nPC: View or edit the program counter \n\n"
                        + "ACC: View or edit the accumulator \n\nNext: View the next instruction that will be ran"));


        //
        // Adding the load menu item with subsidiary open button to open files on a
        // user's machine using JFileChooser
        //

        add(loadMenu);
        JMenuItem fileMenuItem = new JMenuItem("Open");
        loadMenu.add(fileMenuItem);
        JFileChooser vm252FileChooser = new JFileChooser();

        //
        // Filter the search to look for ".vm252obj" files
        //

        FileNameExtensionFilter vm252ExtensionFilter = new FileNameExtensionFilter(
                "VM252 Object File", "vm252obj"
        );
        vm252FileChooser.setFileFilter(vm252ExtensionFilter);


        // Component for taking a file as input from the user using a dialog box
        fileMenuItem.addActionListener(
                actionEvent -> {
                    int returnVal = vm252FileChooser.showOpenDialog(fileMenuItem);
                    System.out.println(returnVal);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String file_name = vm252FileChooser.getSelectedFile().toString();

                        //
                        // Retrieve the filename of the vm252obj file selected by the user and display it in our
                        //      read-only field, fileNameField
                        //

                        fileNameField.setText(file_name);

                        //
                        // Load the vm252obj file
                        //

                        vm252.loadFile(file_name);
                    }
                }
        );


        //
        // Add components to the menu bar
        //
        add(fileNameLabel);
        add(fileNameField);
        add(runMenu);
        add(runDelayField);
        add(stopMenu);

        JMenuItem runItem = new JMenuItem("Run");
        runItem.addActionListener(
                actionEvent -> {
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            while (!vm252.isLastInstructionCausedHalt()) {

                                if (paused.get()) {
                                    synchronized (threadObject) {
                                        // Pause
                                        try {
                                            threadObject.wait();
                                        } catch (InterruptedException e) {
                                        }
                                    }
                                }
                                try {
                                    long delayValue = (Long.parseLong(runDelayField.getText())) * 1000;
                                    try {
                                        System.out.println("sleeping");
                                        Thread.sleep(delayValue);
                                        System.out.println("slept for " + delayValue);
                                    } catch (InterruptedException ex) {
                                        System.out.println("no delay inserted");
                                    }
                                } catch (NumberFormatException e) {
                                }

                                // Write to text area
                                vm252.runNextInstruction();
                            }


                        }
                    };
                    threadObject = new Thread(runnable);
                    threadObject.start();
                }
        );
        runMenu.add(runItem);
        JMenuItem pauseItem = new JMenuItem("Pause");
        pauseItem.addActionListener(
                actionEvent -> {
                    if (!paused.get()) {
                        pauseItem.setText("Start");
                        paused.set(true);
                    } else {
                        pauseItem.setText("Pause");
                        paused.set(false);

                        // Resume
                        synchronized (threadObject) {
                            threadObject.notify();
                        }
                    }
                }
        );
        runMenu.add(pauseItem);
    }

    @Override
    public void update() {
        // TODO: implement when model is integrated
    }


}
