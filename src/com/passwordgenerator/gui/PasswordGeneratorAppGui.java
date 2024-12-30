package com.passwordgenerator.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.passwordgenerator.logic.GeneratePassword;

public class PasswordGeneratorAppGui extends JFrame  implements ActionListener{
	JPanel mainPanel = new JPanel();
	GridBagConstraints gridConstraint = new GridBagConstraints();
	
	JLabel label = new JLabel();
	JTextField outputPassword = new JTextField();
	JTextField inputSizePassword = new JTextField();
	JButton buttonGenerateRandomPassword = new JButton();
	JButton buttonClearOutput = new JButton();
	JButton buttonCopyToClipboard = new JButton();
	
	GeneratePassword newPassword = new GeneratePassword();
	Clipboard copyToClipvoard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	public PasswordGeneratorAppGui () {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Random Generator Password");
		this.setSize(450, 150);
		
		this.setMainPanel();
		this.add(mainPanel);
		
		this.setVisible(true);
	}
	
	private void setMainPanel () {
		mainPanel.setLayout(new GridBagLayout());
//		mainPanel.setBounds(10, 20, 300, 300);
		gridConstraint.insets = new Insets(4, 4, 4, 4);
		
		setLabel();
		mainPanel.add(label, gridConstraint);
		
		setInputSizePassword();
		mainPanel.add(inputSizePassword, gridConstraint);
		
		setOutputPassword();
		mainPanel.add(outputPassword, gridConstraint);
		
		setButtonGenerateRandomPassword();
		mainPanel.add(buttonGenerateRandomPassword, gridConstraint);
		
		setButtonClearOutput();
		mainPanel.add(buttonClearOutput, gridConstraint);
		
		setButtonCopyToClipboard();
		mainPanel.add(buttonCopyToClipboard, gridConstraint);
	}
	
	private void setLabel() {
		// set coordinate at panel
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 1;
		gridConstraint.fill = GridBagConstraints.NONE;
				
		// set attribute for component
		label.setText("Input Size Password : ");
	}
	
	private void setInputSizePassword() {
		// set coordinate at panel
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		
		// set attribute for component
		inputSizePassword.setText("");
		inputSizePassword.setHorizontalAlignment(SwingConstants.CENTER);
		inputSizePassword.setPreferredSize(new Dimension(125, 25));
	}
	
	private void setOutputPassword() {
		// set coordinate at panel
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 2;
		gridConstraint.gridwidth = 4;
		gridConstraint.fill = GridBagConstraints.HORIZONTAL;
				
		// set attribute for component
		outputPassword.setText("");
		outputPassword.setHorizontalAlignment(SwingConstants.LEFT);
		outputPassword.setPreferredSize(new Dimension(250, 25));
		outputPassword.setFocusable(false);
	}
	
	private void setButtonGenerateRandomPassword () {
		// set coordinate at panel
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 1;
		gridConstraint.fill = GridBagConstraints.NONE;
				
		// set attribute for component
		buttonGenerateRandomPassword.setText("Generate");
		buttonGenerateRandomPassword.setHorizontalAlignment(SwingConstants.CENTER);
		
		// add listener to button
		buttonGenerateRandomPassword.addActionListener(this);
	}
	
	private void setButtonClearOutput() {
		// set coordinate at panel
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 1;
		gridConstraint.fill = GridBagConstraints.NONE;
				
		// set attribute for component
		buttonClearOutput.setText("Clear");
		buttonClearOutput.setHorizontalAlignment(SwingConstants.CENTER);
		
		// add listener to button
		buttonClearOutput.addActionListener(this);
	}
	
	private void setButtonCopyToClipboard() {
		// set coordinate at panel
		gridConstraint.gridx = 3;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 1;
		gridConstraint.fill = GridBagConstraints.NONE;
				
		// set attribute for component
		buttonCopyToClipboard.setText("copy");
		buttonCopyToClipboard.setHorizontalAlignment(SwingConstants.CENTER);
		
		// add listener to button
		buttonCopyToClipboard.addActionListener(this);
	}
	
	private void checkMinusSize(int size) throws IllegalArgumentException {
		if(size < 5) {
			throw new IllegalArgumentException("size password cannot under 5 character!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		String text = source.getText();
		int size = Integer.parseInt(inputSizePassword.getText());
		
		if(text == "Generate") {
			try {
				checkMinusSize(size);
				newPassword.setPasswordSize( size - 1 );
				newPassword.runningApp();
				outputPassword.setText( newPassword.getFinalPassword() );
				inputSizePassword.setFocusable(false);
				
			} catch (IllegalArgumentException a) {
				System.out.println("error :  size under 5 char!");
				outputPassword.setText("Size Password is too short...");
			}

		} else if (text == "Clear") {
			inputSizePassword.setFocusable(true);
			newPassword.setPasswordSize(0);
			inputSizePassword.setText("");
			outputPassword.setText("");
			
		} else if (text == "copy") {
			StringSelection strSet = new StringSelection( outputPassword.getText() );
			copyToClipvoard.setContents(strSet, strSet);
		}
		
	}
}
	