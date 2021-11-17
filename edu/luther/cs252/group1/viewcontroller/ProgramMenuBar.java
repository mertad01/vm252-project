package edu.luther.cs252.group1.viewcontroller;

import edu.luther.cs252.group1.model.VirtualMachine252;
import edu.luther.cs252.group1.observation.BasicObserver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.*;
import java.util.concurrent.atomic.AtomicBoolean;
public class ProgramMenuBar extends JMenuBar implements BasicObserver {
	private AtomicBoolean paused;
	private VirtualMachine252 vm252;
	private JTextField fileNameField;
	private JTextField runDelayField;
	private Thread threadObject;
	//
	// Constructor
	//
	public ProgramMenuBar(VirtualMachine252 vm252) {

		this.vm252 = vm252;

		//
		// Create menu items
		//
		paused = new AtomicBoolean(false);
		JMenu loadMenu = new JMenu("File");
		JMenu runMenu = new JMenu("Run");
		// JMenu pauseMenu = new JMenu("Pause");
		JMenu stopMenu = new JMenu("Stop");
		JMenu fileNameLabel = new JMenu("Enter File Name:");

		//
		// TextField Takes up to 30 characters for a given file name input.
		//
		fileNameField = new JTextField(100);
		runDelayField = new JTextField("Delay", 10);

		//
		// Set help tooltips
		//
		loadMenu.setToolTipText("Load a vm252 object file");
		runMenu.setToolTipText("Run program until breakpoint reached");
		// pauseMenu.setToolTipText("Pause the execution of the program");
		stopMenu.setToolTipText("Stop the execution of the program");
		fileNameLabel.setToolTipText("Enter a name to load a vm252 object file");
		runDelayField.setToolTipText("Delay executing each instruction of the program");


		//
		// Add components to the menu bar
		//
		add(loadMenu);
		add(fileNameLabel);
		add(fileNameField);
		add(runMenu);
		add(runDelayField);
		// add(pauseMenu);
		add(stopMenu);

		JMenuItem runItem = new JMenuItem("Run");
		runItem.addActionListener(
				actionEvent -> {
	Runnable runnable = new Runnable()
        {
            @Override
            public void run() 
            {
	while(!vm252.isLastInstructionCausedHalt()){

                        if(paused.get())
                        {
                            synchronized(threadObject)
                            {
                                // Pause
                                try 
                                {
                                    threadObject.wait();
                                } 
                                catch (InterruptedException e) 
                                {
                                }
                            }
                        }
			try {
        long delayValue = (Long.parseLong(runDelayField.getText()))*1000;
	try        
{
	System.out.println("sleeping");
    Thread.sleep(delayValue);
	System.out.println("slept for " + delayValue);
} 
catch(InterruptedException ex) 
{
	System.out.println("no delay inserted");
}
    } catch (NumberFormatException e) {
    }

                        // Write to text area
			vm252.runNextInstruction();}


                    }
                };
        threadObject = new Thread(runnable);
        threadObject.start();
					// while(!vm252.isLastInstructionCausedHalt()){
					// 	vm252.runNextInstruction();}
				}
				);
		runMenu.add(runItem);
		JMenuItem pauseItem = new JMenuItem("Pause");
		pauseItem.addActionListener(
				actionEvent -> {
            if(!paused.get())
            {
                pauseItem.setText("Start");
                paused.set(true);
            }
            else
            {
                pauseItem.setText("Pause");
                paused.set(false);

                // Resume
                synchronized(threadObject)
                {
                    threadObject.notify();
                }
            }
				}
				);
		runMenu.add(pauseItem);
		JFileChooser vm252FileChooser = new JFileChooser();
		FileNameExtensionFilter vm252ExtensionFilter = new FileNameExtensionFilter(
				"VM252 Object File", "vm252obj"
				);
		vm252FileChooser.setFileFilter(vm252ExtensionFilter);

		JMenuItem fileMenuItem = new JMenuItem("Open");
		fileMenuItem.addActionListener(
				actionEvent -> {
					int returnVal = vm252FileChooser.showOpenDialog(fileMenuItem);
					System.out.println(returnVal);
					if (returnVal == JFileChooser.APPROVE_OPTION)
						vm252.loadFile(vm252FileChooser.getSelectedFile().toString());
				}
				);
		loadMenu.add(fileMenuItem);
	}

	@Override
	public void update() {
		// TODO: implement when model is integrated
	}




}
