package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramStatePanel extends JPanel implements BasicObserver {

    //
    // Private Instance Fields
    //
    private VirtualMachine252 vm252;

    private JTextField programCounterField;
    private JTextField accumulatorField;
    private JTextField nextInstructionField;

    //
    // Constructor
    //
    public ProgramStatePanel(VirtualMachine252 vm252) {
        this.vm252 = vm252;

        Box machineStateBox = Box.createVerticalBox();

        JMenu programCounterMenu = new JMenu("PC: ");
        JMenu accumulatorMenu = new JMenu("ACC: ");
        JMenu nextInstructionMenu = new JMenu("Next: ");

        programCounterField = new JTextField(4);
        accumulatorField = new JTextField(4);
        nextInstructionField = new JTextField(4);

        //
        // Set help tooltips
        //
        programCounterField.setToolTipText("View or edit the program counter");
        accumulatorField.setToolTipText("View or edit the accumulator");
        nextInstructionField.setToolTipText("View the next instruction that will be ran");

        //
        // Add menus and fields to the machine state box
        //
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


        // Listen for changes to the programCounter and accumulator fields
        programCounterField.addActionListener(
                actionEvent -> {
                    vm252.setProgramCounter(Short.parseShort(programCounterField.getText()));
                    // Allow user to turn continue interacting after reaching STOP if they change programCounter
//                    vm252.toggleLastInstructionCausedHalt();
//                    vm252.toggleSuppressPcIncrement();
                }
        );
        accumulatorField.addActionListener(
                actionEvent -> {
                    vm252.setAccumulator(Short.parseShort(accumulatorField.getText()));
                    // Allow user to turn continue interacting after reaching STOP if they change accumulator
//                    vm252.toggleLastInstructionCausedHalt();
//                    vm252.toggleSuppressPcIncrement();
                }
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
        nextInstructionField.setText("todo");
    }
}
