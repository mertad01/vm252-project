package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;

public class ProgramInputPanel extends JPanel implements BasicObserver {

    private VirtualMachine252 vm252;
    private JTextField inputOutputField;

    public ProgramInputPanel(VirtualMachine252 vm252) {

        this.vm252 = vm252;

        JMenu inputOutputMenu = new JMenu("Input/Output: ");
        inputOutputField = new JTextField(50);

        //
        // Set help tooltips
        //
        inputOutputField.setToolTipText("Enter input or receive output here");

        add(inputOutputMenu);
        add(inputOutputField);
    }

    @Override
    public void update() {
        // TODO: Integrate with model
    }
}
