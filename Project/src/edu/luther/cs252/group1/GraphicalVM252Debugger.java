package edu.luther.cs252.group1;

import java.awt.*;
import javax.swing.*;


public class GraphicalVM252Debugger
{

    public static void main(String [] commandLineArguments)
    {

        EventQueue.invokeLater(
                () ->
                {

                    ProgramFrame frame = new ProgramFrame();

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);

                }
        );

    }

}


class ProgramFrame extends JFrame
{

    private static final int OUR_DEFAULT_WIDTH = 400;
    private static final int OUR_DEFAULT_HEIGHT = 200;

    private JPanel programPanel;

    private JPanel programPanel()
    {

        return programPanel;

    }

    private void setProgramPanel(JPanel other)
    {

        programPanel = other;

    }

    public ProgramFrame()
    {

        setTitle("Buttons");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        JLabel accumulatorLabel = new JLabel("ACC=", JLabel.RIGHT);
        JLabel programCounterLabel = new JLabel("PC=", JLabel.RIGHT);
        JLabel nextInstructionLabel = new JLabel("Next Instruction=", JLabel.RIGHT);
        JLabel nextInstructionHolderLabel = new JLabel("JUMP main", JLabel.RIGHT);
        JLabel instructionExecutionDelayLabel = new JLabel("Delay=", JLabel.RIGHT);

        JTextField accumulatorTextField = new JTextField("0", 5);
        JTextField programCounterTextField = new JTextField("0", 5);
        JTextField instructionExecutionDelayTextField = new JTextField("0", 5);

        JButton reinitializePCButton = new JButton("Z");
        JButton executeNextInstructionButton = new JButton("N");
        JButton runMachineButton = new JButton("Run");
        JButton pauseMachineButton = new JButton("Pause");

        JTable memoryTable = new JTable(20, 20);
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

        for (int row = 4; row < 20; ++ row) {
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

        setProgramPanel(new JPanel());

        programPanel().add(accumulatorLabel);
        programPanel().add(accumulatorTextField);

        programPanel().add(programCounterLabel);
        programPanel().add(programCounterTextField);

        programPanel().add(nextInstructionLabel);
        programPanel().add(nextInstructionHolderLabel);

        programPanel().add(reinitializePCButton);
        programPanel().add(executeNextInstructionButton);
        programPanel().add(runMachineButton);
        programPanel().add(pauseMachineButton);

        programPanel().add(instructionExecutionDelayLabel);
        programPanel().add(instructionExecutionDelayTextField);

        programPanel().add(memoryTable);

        add(programPanel());


    }



}

