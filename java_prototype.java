
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
        JTextArea ta = new JTextArea();

        // this is where positioning is done on the page
        // But when we implement as classes we need to use Box_layout which is a better
        // approach
        frame.getContentPane().add(BorderLayout.NORTH, menu_item);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.getContentPane().add(BorderLayout.WEST, buttonPanel);
        frame.getContentPane().add(BorderLayout.EAST, pc_acc);
        frame.setVisible(true);

        // TO Do
        // Convert elements of this skeleton to classes
        // Use Zaring's model-view-control architecture

    }
}