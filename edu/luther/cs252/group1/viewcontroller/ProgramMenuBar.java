package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        
        JMenu helpMenu = new JMenu("Help");
        JMenu loadMenu = new JMenu("File");
        JMenu runMenu = new JMenu("Run");
        JMenu pauseMenu = new JMenu("Pause");
        JMenu stopMenu = new JMenu("Stop");
        JMenu fileNameLabel = new JMenu("File Name:");

        //
        // TextField Takes up to 30 characters for a given file name input.
        //
        fileNameField = new JTextField(100);
        
        // Set the textfield as read-only
        
        fileNameField.setEditable(false);
        
        runDelayField = new JTextField("Delay", 10);

        //
        // Set help tooltips
        //
        
        helpMenu.setToolTipText("Spawn a help dialog explaining the application");
        loadMenu.setToolTipText("Load a vm252 object file");
        runMenu.setToolTipText("Run program until breakpoint reached");
        pauseMenu.setToolTipText("Pause the execution of the program");
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
                "To receive fast help for the program you can hover over a component and read the tooltip. Otherwise you can view the full command list for all program controls and inputs"));
        command_list.addActionListener(actionEvent -> JOptionPane.showMessageDialog(getRootPane(),
                "n button: Run Next Program Instruction \n\nLoad: Load a vm252 object file \n\nRun: Run program until breakpoint reached \n\nPause: Pause the execution of the program \n\nStop: Stop the execution of the program \n\nFile Name: The name of the loaded vm252 object file \n\nDelay: Delay executing each instruction of the program \n\nInput/Output: Enter input or receive output here \n\nPC: View or edit the program counter \n\nACC: View or edit the accumulator \n\nNext: View the next instruction that will be ran"));
        
        add(loadMenu);
        add(fileNameLabel);
        add(fileNameField);
        add(runMenu);
        add(runDelayField);
        add(pauseMenu);
        add(stopMenu);

        JFileChooser vm252FileChooser = new JFileChooser();
        FileNameExtensionFilter vm252ExtensionFilter = new FileNameExtensionFilter(
                "VM252 Object File", "vm252obj"
        );
        vm252FileChooser.setFileFilter(vm252ExtensionFilter);

        JMenuItem fileMenuItem = new JMenuItem("Open");
        fileMenuItem.addActionListener(
                actionEvent -> {
                    int returnVal = vm252FileChooser.showOpenDialog(fileMenuItem);
                    System.out.println(returnVal);
                    if (returnVal == JFileChooser.APPROVE_OPTION)
                        vm252.loadFile(vm252FileChooser.getSelectedFile().toString());
                }
        );
        loadMenu.add(fileMenuItem);
    }

    @Override
    public void update() {
        // TODO: implement when model is integrated
    }
}
