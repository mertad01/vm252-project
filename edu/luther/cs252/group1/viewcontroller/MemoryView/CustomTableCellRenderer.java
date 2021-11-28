package edu.luther.cs252.group1.viewcontroller.MemoryView;


import edu.luther.cs252.group1.model.VirtualMachine252;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    VirtualMachine252 vm252;

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
    public CustomTableCellRenderer(VirtualMachine252 vm252) {
        super();
        this.vm252 = vm252;
        // Center align
        this.setHorizontalAlignment(0);
        //this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component currentCell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // If current cell being rendered matches program counter location indicate with a yello background
        if (((row * 20) + column) == vm252.getProgramCounter()) {
            System.out.println(vm252.getMemory()[(row * 20) + column] & 0xFF);
            currentCell.setBackground(Color.YELLOW);
        } else
            currentCell.setBackground(Color.WHITE);
        return currentCell;
    }
}
