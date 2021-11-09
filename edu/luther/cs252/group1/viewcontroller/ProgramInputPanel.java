package edu.luther.cs252.group1.viewcontroller;

import javax.swing.*;

public class ProgramInputPanel extends JPanel {

    public ProgramInputPanel() {
        JMenu inputOutputMenu = new JMenu("Input/Output: ");
        JTextField inputOutputField = new JTextField(50);

        //
        // Set help tooltips
        //
        inputOutputField.setToolTipText("Enter input or receive output here");

        add(inputOutputMenu);
        add(inputOutputField);
    }

}
