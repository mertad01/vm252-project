package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class ProgramInputPanel extends JPanel implements BasicObserver{

    private VirtualMachine252 vm252;

    public ProgramInputPanel(VirtualMachine252 vm252) {

        this.vm252 = vm252;

     JTextArea inputOutputField=
                    new JTextArea(10, 25);
     InputOutput(inputOutputField);
    }
    public void InputOutput(JTextArea passedTextArea){

	//
        // Create scrollable text area for program input/output
        //


        //
        //
        // Create label for text field
        //
            JLabel inputOutputAreaLabel = new JLabel("Input/Output:");

        


            passedTextArea.setEditable(false);

            JScrollPane inputOutputScroller = new JScrollPane(passedTextArea);
        //
        // Set help tooltips
        //
        passedTextArea.setToolTipText("Enter input or receive output here");
        add(inputOutputAreaLabel);
        add(inputOutputScroller);
	System.out.println(passedTextArea.getText()+"123");
	passedTextArea.setText(passedTextArea.getText());
	passedTextArea.revalidate();
	passedTextArea.repaint();
    }
    
    @Override
    public void update() {
        // TODO: Integrate with model
    }
}

