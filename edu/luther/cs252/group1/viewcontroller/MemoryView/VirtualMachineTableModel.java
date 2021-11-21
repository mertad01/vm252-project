package edu.luther.cs252.group1.viewcontroller.MemoryView;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.model.vm252utilities.VM252Utilities;
import edu.luther.cs252.group1.observation.Observer;

import javax.swing.table.AbstractTableModel;

public class VirtualMachineTableModel extends AbstractTableModel implements Observer {

    VirtualMachine252 vm252;
    byte[] memory;

    public VirtualMachineTableModel(VirtualMachine252 vm252) {
        this.vm252 = vm252;
        this.memory = vm252.getMemory();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Only allow editing of cells which contain vm252 memory
        return rowIndex != 409 || columnIndex < 12;
    }

    @Override
    public int getRowCount() {
        // Standard number of rows for vm252 memory to fill
        return 410;
    }

    @Override
    public int getColumnCount() {
        // Standard number of columns for vm252 memory to fill
        return 20;
    }

    @Override
    public Object getValueAt(int row, int column) {
        try {
            // Display hex string representation of appropriate memory address
            String outputHexString = intToHexString(memory[(row * 20) + column] & 0xFF);
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        short address = (short) ((rowIndex * 20) + columnIndex);
        int dataValue = hexStringToInteger((String) aValue);
        // Only update if the new value is within the range for a byte (prevent accidental truncation)
        if (dataValue < 0x100)
            memory[address] = (byte) dataValue;
        //fireTableCellUpdated(rowIndex, columnIndex);
        System.out.println(
                fetchMemoryAddressHexValue(address)
        );
    }

    private String fetchMemoryAddressHexValue(short address) {
        return intToHexString(VM252Utilities.fetchBytePair(memory, address)[0] & 0xFF);
    }

    private static String intToHexString(int integer) {
        return Integer.toHexString(integer).toUpperCase();
    }

    private static int hexStringToInteger(String hexString) {
        int resultInteger = 0;
        int index = 0;
        int size = hexString.length();

        for (char character : hexString.toCharArray()) {
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

    @Override
    public void update() {

    }
}
