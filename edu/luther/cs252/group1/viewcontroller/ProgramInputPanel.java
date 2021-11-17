package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;
import java.awt.*;

public class ProgramInputPanel extends JPanel implements BasicObserver {

	private VirtualMachine252 vm252;
	private JTextField inputOutputField;

	//
	// Constructor
	//

	public ProgramInputPanel(VirtualMachine252 vm252) {

		this.vm252 = vm252;

	}

	@Override
	public void update() {
		// TODO: Integrate with model
	}
}
