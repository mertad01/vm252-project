package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;

public class ProgramInputPanel extends JPanel implements BasicObserver {

    private final VirtualMachine252 vm252;
    private final JLabel nextInstructionLabel;

    //
    // Constructor
    //
    public ProgramInputPanel(VirtualMachine252 vm252) {

        this.vm252 = vm252;

        //
        // Create Input/Output Menu and Text Field
        //
        JMenu nextInstructionMenu = new JMenu("Next: ");
        nextInstructionLabel = new JLabel(vm252.getNextInstruction());

        //
        // Set help tooltips
        //
        nextInstructionLabel.setToolTipText("View the next instruction that will be ran");

        add(nextInstructionMenu);
        add(nextInstructionLabel);

    }

    @Override
    public void update() {
        nextInstructionLabel.setText(vm252.getNextInstruction());
    }
}
