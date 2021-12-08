package edu.luther.cs252.group1.viewcontroller.memoryview.instructions;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.model.vm252utilities.VM252Utilities;
import edu.luther.cs252.group1.viewcontroller.memoryview.MemoryTableModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static edu.luther.cs252.group1.model.vm252utilities.VM252Utilities.*;

public class InstructionLabelTableModel extends MemoryTableModel {

    protected final VirtualMachine252 vm252;
    protected final byte[] memory;

    // Row and Column counts for the table model
    protected final int rowCount;
    protected final int columnCount;

    //
    // Constructor
    //

    //
    // Public Constructor VirtualMachineTableModel(VirtualMachine252 vm252)
    //
    // Purpose:
    //     Model the VirtualMachine252 data so that a table can use and interact with it
    //
    // Formals:
    //     vm252 (in) - instance of a VirtualMachine252 object
    //
    // Pre-conditions:
    //     vm252 contains valid memory
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     none
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    public InstructionLabelTableModel(VirtualMachine252 vm252, int rowCount, int columnCount) {
        this.vm252 = vm252;
        this.memory = vm252.getMemory();
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }


    //
    // Public Instance Method boolean isCellEditable(int rowIndex, int columnIndex)
    //
    // Purpose:
    //     Allow only the cells which contain vm252 data to be editable
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
    //     true if rowIndex,columnIndex is editable, false otherwise
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Only allow editing of cells which contain vm252 memory
        return false;
    }

    //
    // Public Instance Method int getRowCount()
    //
    // Purpose:
    //     Number of rows used by the model to represent the vm252 memory
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
    //     rowCount
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    @Override
    public int getRowCount() {
        // Standard number of rows for vm252 memory to fill
        return rowCount;
    }

    //
    // Public Instance Method int getColumnCount()
    //
    // Purpose:
    //     Number of columns used by the model to represent the vm252 memory
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
    //     columnCount
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    @Override
    public int getColumnCount() {
        // Standard number of columns for vm252 memory to fill
        return columnCount;
    }

    //
    // Public Instance Method Object getValueAt(int rowIndex, int columnIndex)
    //
    // Purpose:
    //     View the vm252 memory at a specific location in the form of a hex string
    //
    // Formals:
    //     rowIndex (in) - which row the cell is in
    //     columnIndex (in) - which column the cell is in
    //
    // Pre-conditions:
    //     none
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     Unsigned hex value in the form of a string, padded with zero to ensure it always has two digit length
    //     Null if the cell is not within the bounds of the vm252 memory
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        try {
            if (memoryLabelHashMap.get((rowIndex * columnCount) + columnIndex) != null) {
                if (memoryByteContentHashMap.get((rowIndex * columnCount) + columnIndex))
                    return memoryLabelHashMap.get((rowIndex * columnCount) + columnIndex) + ": " + VM252Utilities.fetchIntegerValue(memory, (short) ((rowIndex * columnCount) + columnIndex));
            }

            // Display hex string representation of appropriate memory address
            String outputHexString = vm252.getInstruction((short) ((rowIndex * columnCount) + columnIndex));
            // Return the output string if length is less than one, pad with a zero otherwise
            if (Objects.equals(outputHexString, "UNKNOWN")) {
                return null;
            }

            return outputHexString;
        } catch(ArrayIndexOutOfBoundsException exception) {
            // Cells out of vm252 memory bounds are displayed empty
            return null;
        }
    }

    //
    // Public Instance Method void setValueAt(Object aValue, int rowIndex, int columnIndex)
    //
    // Purpose:
    //     Modify the vm252 memory represented within a cell at where rowIndex and columnIndex intersect with
    //     a new unsigned hex value (aValue)
    //
    // Formals:
    //     aValue (in) - string object representing an unsigned hex value
    //     rowIndex (in) - which row the cell is in
    //     columnIndex (in) - which column the cell is in
    //
    // Pre-conditions:
    //     rowIndex,columnIndex cell exists, aValue is a string
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     none
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Location of the cell within the vm252 memory
        short address = (short) ((rowIndex * columnCount) + columnIndex);
        // aValue string as an integer
        try {
            int dataValue = hexStringToInteger((String) aValue);


            // Only update if the new value is within the range for a byte (prevent accidental truncation)
            if (dataValue < 0x100)
                memory[address] = (byte) dataValue;
        }
        // Catch number formatting errors (such as trying to supply a signed/unsupported integer)
        catch (NumberFormatException exception) {
            // Do Nothing
        }
    }

}
