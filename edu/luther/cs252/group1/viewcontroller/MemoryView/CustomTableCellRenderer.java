package edu.luther.cs252.group1.viewcontroller.MemoryView;

import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

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
    public CustomTableCellRenderer() {
        super();
        // Center align
        this.setHorizontalAlignment(0);
        //this.setBackground(Color.LIGHT_GRAY);
    }
}
