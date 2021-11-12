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
        StateInputAction inputAction = new StateInputAction();
        programCounterField.addActionListener(inputAction);
        accumulatorField.addActionListener(inputAction);
    }

    //
    // Public Accessors
    //
    public VirtualMachine252 getVm252() {
        return vm252;
    }

    //
    // Public Mutators
    //
    public void setVm252(VirtualMachine252 vm252) {
        this.vm252 = vm252;
    }

    //
    // Listeners
    //
    private class StateInputAction implements ActionListener {

        public StateInputAction() {

        }

        @Override
        // TODO: preface
        public void actionPerformed(ActionEvent actionEvent) {
            // Set the programCounter and accumulator of the VM252 to be what has been inputted
            vm252.setProgramCounter(Short.parseShort(programCounterField.getText()));
            vm252.setAccumulator(Short.parseShort(accumulatorField.getText()));
        }
    }

    @Override
    public void update() {
        programCounterField.setText(String.valueOf(vm252.getProgramCounter()));
        accumulatorField.setText(String.valueOf(vm252.getAccumulator()));
        nextInstructionField.setText("todo");
//        System.out.println("foo");
    }
}
