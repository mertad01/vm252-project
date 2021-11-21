package edu.luther.cs252.group1.viewcontroller.MemoryView;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.model.vm252utilities.VM252Utilities;
import edu.luther.cs252.group1.observation.Observer;

import javax.swing.table.AbstractTableModel;

public class VirtualMachineTableModel extends AbstractTableModel{

    VirtualMachine252 vm252;
    byte[] memory;

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
    public VirtualMachineTableModel(VirtualMachine252 vm252) {
        this.vm252 = vm252;
        this.memory = vm252.getMemory();
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
        return rowIndex != 409 || columnIndex < 12;
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
    //     410
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    @Override
    public int getRowCount() {
        // Standard number of rows for vm252 memory to fill
        return 410;
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
    //     20
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    @Override
    public int getColumnCount() {
        // Standard number of columns for vm252 memory to fill
        return 20;
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
            // Display hex string representation of appropriate memory address
            String outputHexString = intToHexString(memory[(rowIndex * 20) + columnIndex] & 0xFF);
            // Return the output string if length is less than one, pad with a zero otherwise
            if (outputHexString.length() > 1)
                return outputHexString;
            else
                return "0" + outputHexString;
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
        short address = (short) ((rowIndex * 20) + columnIndex);
        // aValue string as an integer
        int dataValue = hexStringToInteger((String) aValue);

        // Only update if the new value is within the range for a byte (prevent accidental truncation)
        if (dataValue < 0x100)
            memory[address] = (byte) dataValue;

        // Print the new value to the console
        System.out.println(
                intToHexString(VM252Utilities.fetchBytePair(memory, address)[0] & 0xFF)
        );
    }

    //
    // Private Instance Method String intToHexString(int originalInteger)
    //
    // Purpose:
    //     Convert an integer to an upper case hex string
    //
    // Formals:
    //     originalInteger (in) - any integer value
    //
    // Pre-conditions:
    //     none
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     Upper case string for the hex value of originalInteger
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    private static String intToHexString(int originalInteger) {
        return Integer.toHexString(originalInteger).toUpperCase();
    }


    //
    // Private Instance Method int hexStringToInteger(String hexString)
    //
    // Purpose:
    //     Convert a string representing a hex value to the corresponding integer value
    //
    // Formals:
    //     hexString (in) - hex value as a string
    //
    // Pre-conditions:
    //     hexString is a valid hex number, values must be 0-9, A-F
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     Integer representation of hexString
    //
    // Worst-case asymptotic runtime:
    //     O(n) (length of the hexString)
    //
    private static int hexStringToInteger(String hexString) {
        // Resulting value to be returned
        int resultInteger = 0;
        // Location of value within hexString
        int index = 0;
        int size = hexString.length();

        // Add each character from the hex string to the resultInteger
        // Invariant: index <= length of string,
        for (char character : hexString.toCharArray()) {
            // Convert letters and numbers to the value they represent for their location within the hexString
            //   Ex: EC -> E0 + 0C
            switch (Character.toUpperCase(character)) {
                case 'A' -> resultInteger += 10 * (Math.pow(16, size - (index + 1)));
                case 'B' -> resultInteger += 11 * (Math.pow(16, size - (index + 1)));
                case 'C' -> resultInteger += 12 * (Math.pow(16, size - (index + 1)));
                case 'D' -> resultInteger += 13 * (Math.pow(16, size - (index + 1)));
                case 'E' -> resultInteger += 14 * (Math.pow(16, size - (index + 1)));
                case 'F' -> resultInteger += 15 * (Math.pow(16, size - (index + 1)));
                default -> resultInteger += Integer.parseInt(String.valueOf(character)) * (Math.pow(16, size - (index + 1)));
            }
            ++index;
        }

        return resultInteger;
    }
}
