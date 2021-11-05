
//Michael Musa
//Import Swing and awt packages
import javax.swing.*;
import java.awt.*;

class gui {
    public static void main(String args[]) {

        // Creating the Frame
        JFrame frame = new JFrame("Gui Minimal Framework");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);

        // Creating the MenuBar and adding components as seen in prototype
        JMenuBar menu_item = new JMenuBar();
        JMenu m1 = new JMenu("Load");
        JMenu m2 = new JMenu("Edit");
        JMenu m3 = new JMenu("Run");
        JMenu m4 = new JMenu("Pause");
        JMenu m5 = new JMenu("Stop");
        JMenu label = new JMenu("Enter File Name:");
        // Takes up to 30 characters for file name.
        JTextField tf = new JTextField(30);
        menu_item.add(m1);
        menu_item.add(m2);
        menu_item.add(label);
        menu_item.add(tf);
        menu_item.add(m3);
        menu_item.add(m4);
        menu_item.add(m5);
        JMenuItem m11 = new JMenuItem("Open");
        m1.add(m11);

        JPanel buttonPanel = new JPanel();

        // Add buttons to buttonPanel as seen in prototype
        JButton ap = new JButton("ap");
        JButton ba = new JButton("ba");
        JButton h = new JButton("h");
        JButton mb = new JButton("mb");
        JButton n = new JButton("n");
        JButton ob = new JButton("ob");
        buttonPanel.add(ap);
        buttonPanel.add(ba);
        buttonPanel.add(h);
        buttonPanel.add(mb);
        buttonPanel.add(n);
        buttonPanel.add(ob);

        JPanel pc_acc = new JPanel();

        // Add buttons to buttonPanel
        JButton PC = new JButton("PC: ");
        JTextField tf_pc = new JTextField(4);
        JButton ACC = new JButton("ACC: ");
        JTextField tf_acc = new JTextField(4);
        pc_acc.add(PC);
        pc_acc.add(tf_pc);
        pc_acc.add(ACC);
        pc_acc.add(tf_acc);

        // Text Area at the Center
//        JTextArea ta = new JTextArea();
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

        // this is where positioning is done on the page
        // But when we implement as classes we need to use Box_layout which is a better
        // approach
        frame.getContentPane().add(BorderLayout.NORTH, menu_item);
        frame.getContentPane().add(BorderLayout.CENTER, memoryTable);
        frame.getContentPane().add(BorderLayout.WEST, buttonPanel);
        frame.getContentPane().add(BorderLayout.EAST, pc_acc);
        frame.setVisible(true);

        // TO Do
        // Convert elements of this skeleton to classes
        // Use Zaring's model-view-control architecture

    }
}