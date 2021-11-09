package edu.luther.cs252.group1;

import javax.swing.*;
import java.awt.*;

class ProgramButtonPanel extends JPanel {

    public ProgramButtonPanel() {

        //
        // We create a vertical box to contain buttons and have them displayed in blocks
        // (one after another) using the Box class (aligned left)
        //

        Box left = Box.createVerticalBox();
//        JButton alterProgramCounterButton = new JButton("ap"); // I don't think this is needed any longer -adam
        JButton breakpointAddButton = new JButton("ba");
        JButton helpButton = new JButton("h");
//        JButton displayBytesButton = new JButton("mb"); // Not needed, we are using a graphical element for this
        JButton nextInstructionButton = new JButton("n ");
//        JButton displayObjectBytesButton = new JButton("ob"); // Not needed, we are using a graphical element for this

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

//        left.add(Box.createRigidArea(new Dimension(0, 45)));
//        left.add(alterProgramCounterButton); // I don't think this is needed any longer -adam
        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(breakpointAddButton);
        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(helpButton);
//        left.add(Box.createRigidArea(new Dimension(0, 45)));
//        left.add(displayBytesButton); // Not needed, we are using a graphical element for this
        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(nextInstructionButton);
//        left.add(Box.createRigidArea(new Dimension(0, 45)));
//        left.add(displayObjectBytesButton); // Not needed, we are using a graphical element for this
        add(left);

        // Add help dialog which explains how to see what each command/component does
        helpButton.addActionListener(actionEvent ->
                JOptionPane.showMessageDialog(
                        getRootPane(),
                        "Help:\nTo receive help for the program you can hover over a component and read the tooltip"
                )
        );
    }

}
