package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.model.vm252utilities.VM252Utilities;
import edu.luther.cs252.group1.observation.Observer;

import javax.swing.*;

public class MemoryTable extends JTable implements Observer {

    VirtualMachine252 vm252;

    //
    // Constructor
    //
    public MemoryTable(VirtualMachine252 vm252) {
        super(8192, 20);
        this.vm252 = vm252;

        System.out.println("---");
        System.out.println(fetchMemoryAddressHexValue((short) 0));
        System.out.println(fetchMemoryAddressHexValue((short) 1));
        System.out.println(fetchMemoryAddressHexValue((short) 16));

        // Generate an empty table
        for (int row = 0; row < 8192; ++row) {
            for (int column = 0; column < 20; ++column) {
                this.setValueAt("00", row, column);
            }
        }

        updateTable();
    }

    private void updateTable() {
        for (short idx = 0; idx < vm252.getMemory().length; ++idx) {
            this.setValueAt(fetchMemoryAddressHexValue(idx), idx / 20, idx % 20);
        }
    }

    private String fetchMemoryAddressHexValue(short address) {
        return intToHexString(VM252Utilities.fetchBytePair(vm252.getMemory(), address)[0] & 0xFF);
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
        // TODO
        updateTable();
    }
}
