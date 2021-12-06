package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ProgramButtonPanel extends JPanel implements BasicObserver {


	private final VirtualMachine252 vm252;
	private byte[] memory;
	private boolean[] breakpoints;
    //
    // Constructor
    //
    public ProgramButtonPanel(VirtualMachine252 vm252) {
	    this.vm252=vm252;
	    this.memory=vm252.getMemory();
	    this.breakpoints=vm252.getBreakpoints();

        //
        // We create a vertical box to contain buttons and have them displayed in blocks
        // (one after another) using the Box class (aligned left)
        //

        Box leftControlBox = Box.createVerticalBox();
	//jlabel for breakpoint textfield
        JLabel breakpoint = new JLabel("Breakpoint: ");
	//buttons and textfields
        JButton breakpointClearButton = new JButton("Clear BP");
        JButton nextInstructionButton = new JButton("N");
	    JTextField breakpointlocation= new JTextField(2);
        JButton newTable = new JButton("New Table");

        //
        // Set help tooltips
        //
        breakpoint.setToolTipText("Add a breakpoint at selected location");
        breakpointClearButton.setToolTipText("Clear breakpoints in all location");
        nextInstructionButton.setToolTipText("Run Next Program Instruction");

        //
        // We create a vertical spacer that separates elements for every 45 pixels
        // We utilise the box class that creates invisible components in spaces
        // with the help of createRigidArea as the Box filler
        //
        
        leftControlBox.add(breakpoint);
        leftControlBox.add(breakpointlocation);
        leftControlBox.add(Box.createRigidArea(new Dimension(0, 25)));
        leftControlBox.add(breakpointClearButton);
        leftControlBox.add(Box.createRigidArea(new Dimension(0, 25)));
        leftControlBox.add(nextInstructionButton);
        leftControlBox.add(Box.createRigidArea(new Dimension(0, 25)));
        leftControlBox.add(newTable);
        add(leftControlBox);

        newTable.addActionListener(actionEvent -> {
            // do work
            System.out.println(Arrays.toString(getRootPane().getContentPane().getComponents()));
            System.out.println(getRootPane().getContentPane().getComponents()[1]);
            getRootPane().updateUI();
        });

        // Run the next instruction when the button is pressed
        nextInstructionButton.addActionListener(
                actionEvent -> {
                    if (!vm252.isLastInstructionCausedHalt()) {
                        vm252.runNextInstruction();
                        if (vm252.isPreviousInstructionHitBreakpoint())
                            JOptionPane.showMessageDialog(getRootPane(),"Breakpoint at: " + vm252.getProgramCounter());
                    } else {
                        JOptionPane.showMessageDialog(getRootPane(), "REACHED STOP OPCODE");
                    }
                }
        );
	//read the memory location from breakpoint textfield and then assign true to the breakpoint boolean at that location.

        breakpointlocation.addActionListener(
                actionEvent -> {
                    breakpoints[Short.parseShort(breakpointlocation.getText())]=true;
                    vm252.announceChange();
		});
	//
	//clear all the breakpoints by setting all the breakpoints boolean false
        breakpointClearButton.addActionListener(
                actionEvent -> {
                    Arrays.fill(breakpoints, false);
                    vm252.announceChange();
                }
        );
    }

    @Override
    public void update() {
    }
}
