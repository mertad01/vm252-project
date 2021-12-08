package edu.luther.cs252.group1.viewcontroller.memoryview;

import javax.swing.*;
import javax.swing.table.TableModel;

public class MemoryTable extends JTable {

    //
    // Constructor
    //
    // Purpose: Set the resizing and reordering to false for all memory tables
    //
    public MemoryTable(TableModel vm252TableModel, MemoryTableCellRenderer defaultCellRenderer) {
        super(vm252TableModel);
        getTableHeader().setResizingAllowed(false);
        getTableHeader().setReorderingAllowed(false);
        setDefaultRenderer(getColumnClass(0), defaultCellRenderer);
    }
}
