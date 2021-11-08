
//
// Title: VM252 debugger interface
// Authors: Michael Musa, Kritib Bhattarai, Adam Mertzenich
// Date: 11/09/21
//

//Import Swing and awt packages

import javax.swing.*;
import java.awt.*;

class gui {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ProgramFrame frame = new ProgramFrame();
            frame.setTitle("VM252 Debugger");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 720);
            frame.setVisible(true);
        });
    }
}

class ProgramFrame extends JFrame {

    private ProgramMenuBar programMenuBar;
    private ProgramButtonPanel programButtonPanel;
    private ProgramStatePanel programStatePanel;
    private ProgramInputPanel programInputPanel;
    private JTable memoryTable;

    public ProgramFrame() {
        programMenuBar = new ProgramMenuBar();
        programButtonPanel = new ProgramButtonPanel();
        programStatePanel = new ProgramStatePanel();
        programInputPanel = new ProgramInputPanel();
        memoryTable = new JTable(20, 20);

        //
        // Creating a visualization of what memory contents will look
        // like when our program is running.
        //
        // Currently, we are using JTable for this, which would allow
        // editing and viewing the memory in the same location, but may
        // switch to using different objects in order to implement the
        // "ba" breakpoint feature (checkboxes, text fields, etc).
        //

        memoryTable.setValueAt("80", 0, 0);
        memoryTable.setValueAt("10", 0, 1);
        memoryTable.setValueAt("00", 0, 2);
        memoryTable.setValueAt("00", 0, 3);
        memoryTable.setValueAt("00", 0, 4);
        memoryTable.setValueAt("01", 0, 5);
        memoryTable.setValueAt("00", 0, 6);
        memoryTable.setValueAt("02", 0, 7);
        memoryTable.setValueAt("00", 0, 8);
        memoryTable.setValueAt("00", 0, 9);
        memoryTable.setValueAt("00", 0, 10);
        memoryTable.setValueAt("00", 0, 11);
        memoryTable.setValueAt("00", 0, 12);
        memoryTable.setValueAt("00", 0, 13);
        memoryTable.setValueAt("00", 0, 14);
        memoryTable.setValueAt("00", 0, 15);
        memoryTable.setValueAt("f0", 0, 16);
        memoryTable.setValueAt("20", 0, 17);
        memoryTable.setValueAt("02", 0, 18);
        memoryTable.setValueAt("00", 0, 19);
        memoryTable.setValueAt("02", 1, 0);
        memoryTable.setValueAt("60", 1, 1);
        memoryTable.setValueAt("04", 1, 2);
        memoryTable.setValueAt("c0", 1, 3);
        memoryTable.setValueAt("1f", 1, 4);
        memoryTable.setValueAt("00", 1, 5);
        memoryTable.setValueAt("08", 1, 6);
        memoryTable.setValueAt("20", 1, 7);
        memoryTable.setValueAt("08", 1, 8);
        memoryTable.setValueAt("80", 1, 9);
        memoryTable.setValueAt("47", 1, 10);
        memoryTable.setValueAt("00", 1, 11);
        memoryTable.setValueAt("06", 1, 12);
        memoryTable.setValueAt("20", 1, 13);
        memoryTable.setValueAt("0a", 1, 14);
        memoryTable.setValueAt("00", 1, 15);
        memoryTable.setValueAt("04", 1, 16);
        memoryTable.setValueAt("20", 1, 17);
        memoryTable.setValueAt("0c", 1, 18);
        memoryTable.setValueAt("00", 1, 19);
        memoryTable.setValueAt("0a", 2, 0);
        memoryTable.setValueAt("a0", 2, 1);
        memoryTable.setValueAt("39", 2, 2);
        memoryTable.setValueAt("00", 2, 3);
        memoryTable.setValueAt("0e", 2, 4);
        memoryTable.setValueAt("40", 2, 5);
        memoryTable.setValueAt("0c", 2, 6);
        memoryTable.setValueAt("20", 2, 7);
        memoryTable.setValueAt("0e", 2, 8);
        memoryTable.setValueAt("ef", 2, 9);
        memoryTable.setValueAt("ff", 2, 10);
        memoryTable.setValueAt("40", 2, 11);
        memoryTable.setValueAt("0a", 2, 12);
        memoryTable.setValueAt("20", 2, 13);
        memoryTable.setValueAt("0a", 2, 14);
        memoryTable.setValueAt("80", 2, 15);
        memoryTable.setValueAt("27", 2, 16);
        memoryTable.setValueAt("00", 2, 17);
        memoryTable.setValueAt("06", 2, 18);
        memoryTable.setValueAt("f4", 2, 19);
        memoryTable.setValueAt("00", 3, 0);
        memoryTable.setValueAt("04", 3, 1);
        memoryTable.setValueAt("f4", 3, 2);
        memoryTable.setValueAt("00", 3, 3);
        memoryTable.setValueAt("02", 3, 4);
        memoryTable.setValueAt("60", 3, 5);
        memoryTable.setValueAt("0e", 3, 6);
        memoryTable.setValueAt("a0", 3, 7);
        memoryTable.setValueAt("47", 3, 8);
        memoryTable.setValueAt("80", 3, 9);
        memoryTable.setValueAt("1f", 3, 10);
        memoryTable.setValueAt("00", 3, 11);
        memoryTable.setValueAt("0e", 3, 12);
        memoryTable.setValueAt("f4", 3, 13);
        memoryTable.setValueAt("fc", 3, 14);
        memoryTable.setValueAt("00", 3, 15);
        memoryTable.setValueAt("00", 3, 16);
        memoryTable.setValueAt("00", 3, 17);
        memoryTable.setValueAt("00", 3, 18);
        memoryTable.setValueAt("00", 3, 19);

        for (int row = 4; row < 20; ++row) {
            memoryTable.setValueAt("00", row, 0);
            memoryTable.setValueAt("00", row, 1);
            memoryTable.setValueAt("00", row, 2);
            memoryTable.setValueAt("00", row, 3);
            memoryTable.setValueAt("00", row, 4);
            memoryTable.setValueAt("00", row, 5);
            memoryTable.setValueAt("00", row, 6);
            memoryTable.setValueAt("00", row, 7);
            memoryTable.setValueAt("00", row, 8);
            memoryTable.setValueAt("00", row, 9);
            memoryTable.setValueAt("00", row, 10);
            memoryTable.setValueAt("00", row, 11);
            memoryTable.setValueAt("00", row, 12);
            memoryTable.setValueAt("00", row, 13);
            memoryTable.setValueAt("00", row, 14);
            memoryTable.setValueAt("00", row, 15);
            memoryTable.setValueAt("00", row, 16);
            memoryTable.setValueAt("00", row, 17);
            memoryTable.setValueAt("00", row, 18);
            memoryTable.setValueAt("00", row, 19);
        }

        //
        // Utilizing BorderLayout to position elements across the debugger GUI
        //

        getContentPane().add(BorderLayout.NORTH, programMenuBar);
        getContentPane().add(BorderLayout.CENTER, memoryTable);
        getContentPane().add(BorderLayout.WEST, programButtonPanel);
        getContentPane().add(BorderLayout.EAST, programStatePanel);
        getContentPane().add(BorderLayout.SOUTH, programInputPanel);
    }

}

class ProgramMenuBar extends JMenuBar {

    public ProgramMenuBar() {
        JMenu loadMenu = new JMenu("Load");
        JMenu editMenu = new JMenu("Edit");
        JMenu runMenu = new JMenu("Run");
        JMenu pauseMenu = new JMenu("Pause");
        JMenu stopMenu = new JMenu("Stop");
        JMenu fileNameLabel = new JMenu("Enter File Name:");

        //
        // Set help tooltips
        //
        loadMenu.setToolTipText("Load a vm252 object file");
        editMenu.setToolTipText("???"); // TODO
        runMenu.setToolTipText("Run program until breakpoint reached");
        pauseMenu.setToolTipText("Pause the execution of the program");
        stopMenu.setToolTipText("Stop the execution of the program");
        fileNameLabel.setToolTipText("Enter a name to load a vm252 object file");

        //
        // TextField Takes up to 30 characters for a given file name input.
        //
        JTextField fileNameField = new JTextField(30);

        add(loadMenu);
        add(editMenu);
        add(fileNameLabel);
        add(fileNameField);
        add(runMenu);
        add(pauseMenu);
        add(stopMenu);
        JMenuItem m11 = new JMenuItem("Open");
        loadMenu.add(m11);
    }

}

class ProgramButtonPanel extends JPanel {

    public ProgramButtonPanel() {

        //
        // We create a vertical box to contain buttons and have them displayed in blocks
        // (one after another) using the Box class (aligned left)
        //

        Box left = Box.createVerticalBox();
        JButton alterProgramCounterButton = new JButton("ap");
        JButton breakpointAddButton = new JButton("ba");
        JButton helpButton = new JButton("h");
        JButton displayBytesButton = new JButton("mb");
        JButton nextInstructionButton = new JButton("n ");
        JButton displayObjectBytesButton = new JButton("ob");

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

        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(alterProgramCounterButton);
        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(breakpointAddButton);
        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(helpButton);
        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(displayBytesButton);
        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(nextInstructionButton);
        left.add(Box.createRigidArea(new Dimension(0, 45)));
        left.add(displayObjectBytesButton);
        add(left);
    }

}

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

class ProgramInputPanel extends JPanel {

    public ProgramInputPanel() {
        JMenu inputOutputMenu = new JMenu("Input/Output: ");
        JTextField inputOutputField = new JTextField(50);

        //
        // Set help tooltips
        //
        inputOutputField.setToolTipText("Enter input or receive output here");

        add(inputOutputMenu);
        add(inputOutputField);
    }

}
