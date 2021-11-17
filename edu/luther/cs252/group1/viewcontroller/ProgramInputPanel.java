package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class ProgramInputPanel extends JPanel implements BasicObserver{

    private VirtualMachine252 vm252;

    //
    // Constructor
    //
    public ProgramInputPanel(VirtualMachine252 vm252) {

        this.vm252 = vm252;

     //JTextArea inputOutputField=
     //               new JTextArea(10, 25);
     //InputOutput(inputOutputField);
    //}
    //public void InputOutput(JTextArea passedTextArea){

	////
     //   // Create scrollable text area for program input/output
     //   //


     //   //
     //   //
     //   // Create label for text field
     //   //
	//passedTextArea.setText(passedTextArea.getText());
     //       JLabel inputOutputAreaLabel = new JLabel("Input/Output:");
        //
        // Create Input/Output Menu and Text Field
        //
        
        //JMenu inputOutputMenu = new JMenu("Input/Output: ");
        //inputOutputField = new JTextField(50);
        
        //
        // Set the height and width of the Input/Output textbox for better visibility
        //

        //inputOutputField.setPreferredSize(new Dimension(450, 90));

        


     //       passedTextArea.setEditable(false);

     //       JScrollPane inputOutputScroller = new JScrollPane(passedTextArea);
        //
        // Set help tooltips
        //
        // passedTextArea.setToolTipText("Enter input or receive output here");
        // add(inputOutputAreaLabel);
        // add(inputOutputScroller);
        
        //inputOutputField.setToolTipText("Enter input or receive output here");

        //add(inputOutputMenu);
        //add(inputOutputField);
    }
    
    @Override
    public void update() {
        // TODO: Integrate with model
    }
}

