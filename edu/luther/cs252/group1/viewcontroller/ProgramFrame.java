package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;
import edu.luther.cs252.group1.viewcontroller.events.MiddleClickBreakpointEvent;
import edu.luther.cs252.group1.viewcontroller.memoryview.MemoryTable;
import edu.luther.cs252.group1.viewcontroller.memoryview.instructions.InstructionLabelCellRenderer;
import edu.luther.cs252.group1.viewcontroller.memoryview.instructions.InstructionLabelTableModel;
import edu.luther.cs252.group1.viewcontroller.memoryview.singlebyte.SingleByteHexCellRenderer;
import edu.luther.cs252.group1.viewcontroller.memoryview.singlebyte.SingleByteHexTableModel;
import edu.luther.cs252.group1.viewcontroller.memoryview.twobyte.TwoByteHexCellRenderer;
import edu.luther.cs252.group1.viewcontroller.memoryview.twobyte.TwoByteHexTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProgramFrame extends JFrame implements BasicObserver {

    private final SingleByteHexTableModel vm252TableModel;
    private final InstructionLabelTableModel vm252MachineInstructionsModel;
    private final TwoByteHexTableModel vm252TwoByteHexTableModel;

    //
    // Constructor
    //
    public ProgramFrame() {

        //
        // Create Model Object
        //
        VirtualMachine252 vm252 = new VirtualMachine252();


        //
        // Create View-Controller Panels
        //
        //
        // Private Instance Fields
        //
        ProgramMenuBar programMenuBar = new ProgramMenuBar(vm252);
        ProgramButtonPanel programButtonPanel = new ProgramButtonPanel(vm252);
        ProgramStatePanel programStatePanel = new ProgramStatePanel(vm252);
        ProgramInputPanel programInputPanel = new ProgramInputPanel(vm252);

        // Table model which allows the table to represent the VirtualMachine252 (=ob & /=mb= commands)
        vm252TableModel = new SingleByteHexTableModel(vm252, 410, 20);
        // Table model representing VirtualMachine252 as instructions, data, and labels
        // (=MI= command)
        vm252MachineInstructionsModel = new InstructionLabelTableModel(vm252, 8192, 1);
        // Table model displaying the portion of machine memory holding object code as 2-byte data in hex (=OD= & =MD= command)
        vm252TwoByteHexTableModel = new TwoByteHexTableModel(vm252, 410, 10);


        // Create new JTable using VirtualMachineTableModel as the model
        MemoryTable memoryTable = new MemoryTable(
                vm252TableModel,
                new SingleByteHexCellRenderer(vm252, vm252TableModel)
        );
        MemoryTable twoByteHexMemoryTable = new MemoryTable(
                vm252TwoByteHexTableModel,
                new TwoByteHexCellRenderer(vm252, vm252TwoByteHexTableModel)
        );
        MemoryTable machineInstructionMemoryTable = new MemoryTable(
                vm252MachineInstructionsModel,
                new InstructionLabelCellRenderer(vm252, vm252MachineInstructionsModel)
        );


        // Make the memory scrollable
        JScrollPane scrollableMemoryPane = new JScrollPane(memoryTable);
        JScrollPane scrollableMachineInstructionMemoryPane = new JScrollPane(machineInstructionMemoryTable);
        JScrollPane scrollableTwoByteHexMemoryPane = new JScrollPane(twoByteHexMemoryTable);

        // Create breakpoints using middle mouse click
        MiddleClickBreakpointEvent middleClickBreakpointEvent = new MiddleClickBreakpointEvent(vm252, memoryTable);
        memoryTable.addMouseListener(middleClickBreakpointEvent);
        twoByteHexMemoryTable.addMouseListener(middleClickBreakpointEvent);

        memoryTable.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int row = memoryTable.rowAtPoint(e.getPoint());
                int column = memoryTable.columnAtPoint(e.getPoint());
                System.out.println(row + " | " + column);
            }
        });

        //
        // Attach observers to check for changes
        //
        vm252.attach(programMenuBar);
        vm252.attach(programButtonPanel);
        vm252.attach(programStatePanel);
        vm252.attach(programInputPanel);
        vm252.attach(this);

        //
        // Utilizing BorderLayout to position elements across the debugger GUI
        //

        getContentPane().add(BorderLayout.NORTH, programMenuBar);
        getContentPane().add(BorderLayout.WEST, programButtonPanel);
        getContentPane().add(BorderLayout.EAST, programStatePanel);
        getContentPane().add(BorderLayout.SOUTH, programInputPanel);


        // Use tabbed pane for different memory views
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("MB/OB", scrollableMemoryPane);
        tabbedPane.addTab("MD/OD", scrollableTwoByteHexMemoryPane); tabbedPane.addTab("MI/OI", scrollableMachineInstructionMemoryPane); //FIXME

        tabbedPane.setForeground(Color.red);
        getContentPane().setBackground(Color.white);

        getContentPane().add(BorderLayout.CENTER, tabbedPane);
    }

    @Override
    public void update() {
        // Keep the vm252TableModel up-to-date when the data changes
        vm252TableModel.fireTableDataChanged();
        vm252MachineInstructionsModel.fireTableDataChanged();
        vm252TwoByteHexTableModel.fireTableDataChanged();
    }
}
