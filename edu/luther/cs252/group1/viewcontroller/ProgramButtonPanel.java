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
        JButton helpButton = new JButton("h");
        JButton nextInstructionButton = new JButton("n ");

        //
        // Set help tooltips
        //
        breakpointAddButton.setToolTipText("Add a breakpoint at selected location");
        helpButton.setToolTipText("Spawn a help dialog explaining the application");
        nextInstructionButton.setToolTipText("Run Next Program Instruction");

        //
        // We create a vertical spacer that separates elements for every 45 pixels
        // We utilise the box class that creates invisible components in spaces
        // with the help of createRigidArea as the Box filler
        //

        leftControlBox.add(Box.createRigidArea(new Dimension(0, 45)));
        leftControlBox.add(breakpointAddButton);
        leftControlBox.add(Box.createRigidArea(new Dimension(0, 45)));
        leftControlBox.add(helpButton);
        leftControlBox.add(Box.createRigidArea(new Dimension(0, 45)));
        leftControlBox.add(nextInstructionButton);
        add(leftControlBox);

        // Add help dialog which explains how to see what each command/component does
        helpButton.addActionListener(actionEvent ->
                JOptionPane.showMessageDialog(
                        getRootPane(),
                        "Help:\nTo receive help for the program you can hover over a component and read the tooltip"
                )
        );
    }

    @Override
    public void update() {
    }
}
