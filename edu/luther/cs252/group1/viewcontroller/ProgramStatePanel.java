package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;

public class ProgramStatePanel extends JPanel implements BasicObserver {

    //
    // Private Instance Fields
    //
    private final VirtualMachine252 vm252;

    private final JTextField programCounterField;
    private final JTextField accumulatorField;

    //
    // Constructor
    //
    public ProgramStatePanel(VirtualMachine252 vm252) {
        this.vm252 = vm252;

        Box machineStateBox = Box.createVerticalBox();

        JMenu programCounterMenu = new JMenu("PC: ");
        JMenu accumulatorMenu = new JMenu("ACC: ");

        programCounterField = new JTextField(4);
        accumulatorField = new JTextField(4);

        //
        // Set help tooltips
        //
        programCounterField.setToolTipText("View or edit the program counter");
        accumulatorField.setToolTipText("View or edit the accumulator");

        //
        // Add menus and fields to the machine state box
        //
        machineStateBox.add(programCounterMenu);
        machineStateBox.add(programCounterField);
        machineStateBox.add(accumulatorMenu);
        machineStateBox.add(accumulatorField);

        //
        // Initialize all machine states to 0 for visualization
        //

        programCounterField.setText("0");
        accumulatorField.setText("0");
        // TODO: update and display the next instruction (for the "s" command)

        // Add the machine state box to the panel
        add(machineStateBox);


        // Listen for changes to the programCounter and accumulator fields
        programCounterField.addActionListener(
                actionEvent -> vm252.setProgramCounter(Short.parseShort(programCounterField.getText()))
        );
        accumulatorField.addActionListener(
                actionEvent -> vm252.setAccumulator(Short.parseShort(accumulatorField.getText()))
        );

    }

    //
    // Public Accessors
    //

    //
    // Public Mutators
    //


    @Override
    public void update() {
        // Update the programCounter and accumulator whenever the model changes
        programCounterField.setText(String.valueOf(vm252.getProgramCounter()));
        accumulatorField.setText(String.valueOf(vm252.getAccumulator()));

    }
}
