
//Michael Musa
//Import Swing and awt packages
import javax.swing.*;
import java.awt.*;

class gui {
    public static void main(String[] args) {
        EventQueue.invokeLater(
                () ->
                {
                    ProgramFrame frame = new ProgramFrame();
                    frame.setTitle("VM252 Debugger");

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(1280, 720);
                    frame.setVisible(true);
                }
        );
    }
}

class ProgramFrame extends JFrame {

    private ProgramMenuBar programMenuBar = new ProgramMenuBar();
    private ProgramButtonPanel programButtonPanel = new ProgramButtonPanel();
    private ProgramStatePanel programStatePanel = new ProgramStatePanel();
    private JTable memoryTable = new JTable(20, 20);

    public ProgramFrame() {
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

        for (int row = 4; row < 20; ++row) {
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

        // Text Area at the Center
        // A visualization of what memory contents will look
        // like when our program is running


        // this is where positioning is done on the page
        // But when we implement as classes we need to use Box_layout which is a better
        // approach
        getContentPane().add(BorderLayout.NORTH, programMenuBar);
        getContentPane().add(BorderLayout.CENTER, memoryTable);
        getContentPane().add(BorderLayout.WEST, programButtonPanel);
        getContentPane().add(BorderLayout.EAST, programStatePanel);
    }

}

class ProgramMenuBar extends JMenuBar {

    public ProgramMenuBar() {
        JMenu m1 = new JMenu("Load");
        JMenu m2 = new JMenu("Edit");
        JMenu m3 = new JMenu("Run");
        JMenu m4 = new JMenu("Pause");
        JMenu m5 = new JMenu("Stop");
        JMenu label = new JMenu("Enter File Name:");
        // Takes up to 30 characters for file name.
        JTextField tf = new JTextField(30);
        add(m1);
        add(m2);
        add(label);
        add(tf);
        add(m3);
        add(m4);
        add(m5);
        JMenuItem m11 = new JMenuItem("Open");
        m1.add(m11);
    }

}

class ProgramButtonPanel extends JPanel {

    public ProgramButtonPanel() {
        JButton ap = new JButton("ap");
        JButton ba = new JButton("ba");
        JButton h = new JButton("h");
        JButton mb = new JButton("mb");
        JButton n = new JButton("n");
        JButton ob = new JButton("ob");
        add(ap);
        add(ba);
        add(h);
        add(mb);
        add(n);
        add(ob);
    }

}

class ProgramStatePanel extends JPanel {

    public ProgramStatePanel() {
        JButton PC = new JButton("PC: ");
        JTextField tf_pc = new JTextField(4);
        JButton ACC = new JButton("ACC: ");
        JTextField tf_acc = new JTextField(4);
        add(PC);
        add(tf_pc);
        add(ACC);
        add(tf_acc);
    }

}