package edu.luther.cs252.group1;

import javax.swing.*;

class ProgramStatePanel extends JPanel {

    public ProgramStatePanel() {

        Box machineStateBox = Box.createVerticalBox();

        JMenu programCounterMenu = new JMenu("PC: ");
        JMenu accumulatorMenu = new JMenu("ACC: ");
        JMenu nextInstructionMenu = new JMenu("Next: ");

        JTextField programCounterField = new JTextField(4);
        JTextField accumulatorField = new JTextField(4);
        JTextField nextInstructionField = new JTextField(4);

        //
        // Set help tooltips
        //
        programCounterField.setToolTipText("View or edit the program counter");
        accumulatorField.setToolTipText("View or edit the accumulator");
        nextInstructionField.setToolTipText("View the next instruction that will be ran");

        machineStateBox.add(programCounterMenu);
        machineStateBox.add(programCounterField);
        machineStateBox.add(accumulatorMenu);
        machineStateBox.add(accumulatorField);
        machineStateBox.add(nextInstructionMenu);
        machineStateBox.add(nextInstructionField);

        //
        // Initialize all machine states to 0 for visualization
        //

        programCounterField.setText("0");
        accumulatorField.setText("0");
        // TODO: update and display the next instruction (for the "s" command)
        nextInstructionField.setText("INPUT");

        // Add the machine state box to the panel
        add(machineStateBox);
    }

}
