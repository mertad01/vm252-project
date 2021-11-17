package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;
import java.io.PrintStream;
import javax.swing.*;

public class ProgramInputPanel extends JPanel implements BasicObserver {

    private VirtualMachine252 vm252;

    public ProgramInputPanel(VirtualMachine252 vm252) {

        this.vm252 = vm252;

     JTextArea inputOutputField=
                    new JTextArea(10, 25);
	//
        // Create scrollable text area for program input/output
        //


        //
        //
        // Create label for text field
        //

            JLabel inputOutputAreaLabel = new JLabel("Input/Output:");

        


            inputOutputField.setEditable(false);

            JScrollPane inputOutputScroller = new JScrollPane(inputOutputField);
        //
        // Set help tooltips
        //
        inputOutputField.setToolTipText("Enter input or receive output here");
        add(inputOutputAreaLabel);
        add(inputOutputScroller);
    }
    @Override
    public void update() {
        // TODO: Integrate with model
    }
}

