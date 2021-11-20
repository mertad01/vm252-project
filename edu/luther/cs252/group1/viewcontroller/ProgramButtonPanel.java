package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;
import java.awt.*;

public class ProgramButtonPanel extends JPanel implements BasicObserver {

    private VirtualMachine252 vm252;


    //
    // Constructor
    //
    public ProgramButtonPanel(VirtualMachine252 vm252) {

        this.vm252 = vm252;

        //
        // We create a vertical box to contain buttons and have them displayed in blocks
        // (one after another) using the Box class (aligned left)
        //

        Box leftControlBox = Box.createVerticalBox();
        JButton breakpointAddButton = new JButton("ba");
        JButton nextInstructionButton = new JButton("n ");

        //
        // Set help tooltips
        //
        breakpointAddButton.setToolTipText("Add a breakpoint at selected location");
        nextInstructionButton.setToolTipText("Run Next Program Instruction");

        //
        // We create a vertical spacer that separates elements for every 45 pixels
        // We utilise the box class that creates invisible components in spaces
        // with the help of createRigidArea as the Box filler
        //
        
        leftControlBox.add(breakpointAddButton);
        leftControlBox.add(Box.createRigidArea(new Dimension(0, 45)));
        leftControlBox.add(nextInstructionButton);
        add(leftControlBox);

        // Run the next instruction when the button is pressed
        nextInstructionButton.addActionListener(
                actionEvent -> {
                    if (!vm252.isLastInstructionCausedHalt()) {
                        vm252.runNextInstruction();
                    } else {
                        JOptionPane.showMessageDialog(null, "REACHED STOP OPCODE");
                    }
                }
        );
    }

    @Override
    public void update() {
    }
}
