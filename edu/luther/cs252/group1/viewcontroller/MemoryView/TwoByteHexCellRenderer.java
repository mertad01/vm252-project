package edu.luther.cs252.group1.viewcontroller.MemoryView;


import edu.luther.cs252.group1.model.VirtualMachine252;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TwoByteHexCellRenderer extends DefaultTableCellRenderer {

    private VirtualMachine252 vm252;
    private TwoByteHexTableModel vm252TwoByteHexTableModel;

    //
    // Constructor
    //

    //
    // Public Constructor CustomTableCellRenderer()
    //
    // Purpose:
    //     Cell renderer to have cell text be center aligned
    //
    // Formals:
    //     none
    //
    // Pre-conditions:
    //     none
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     Void
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    public TwoByteHexCellRenderer(VirtualMachine252 vm252, TwoByteHexTableModel vm252TwoByteHexTableModel) {
        super();
        this.vm252 = vm252;
        this.vm252TwoByteHexTableModel = vm252TwoByteHexTableModel;
        // Center align
        this.setHorizontalAlignment(0);
        //this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component currentCell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // If current cell being rendered matches program counter location indicate with a yellow background
        try {
            int address = ((column * 2) + (row * 20));
            if (address == vm252.getProgramCounter() || address+1 == vm252.getProgramCounter()) {
                currentCell.setBackground(Color.YELLOW);
            } else
                currentCell.setBackground(Color.WHITE);

            if (vm252.getBreakpoints()[((row * vm252TwoByteHexTableModel.getColumnCount()) + column)])
                currentCell.setBackground(Color.PINK);
        } catch (ArrayIndexOutOfBoundsException exception) {
            // Do nothing
        }
        return currentCell;
    }
}
