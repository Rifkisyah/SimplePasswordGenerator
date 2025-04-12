package com.passwordgenerator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.passwordgenerator.logic.GeneratePassword;

public class PasswordGeneratorAppGui extends JFrame  implements ActionListener{
	private JPanel mainPanel = new JPanel();
	private GridBagConstraints gridConstraint = new GridBagConstraints();
	
	private JLabel label = new JLabel();
	private JTextField outputPassword = new JTextField();
	private JTextField inputSizePassword = new JTextField();
	private JButton buttonGenerateRandomPassword = new JButton();
	private JButton buttonClearOutput = new JButton();
	private JButton buttonCopyToClipboard = new JButton();
	
	private GeneratePassword newPassword = new GeneratePassword();
	private Clipboard copyToClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	private ImageIcon icon;
	private int mouseX, mouseY;
 

	public PasswordGeneratorAppGui () {
		GithubTheme.styleDarkButton(buttonGenerateRandomPassword);
		GithubTheme.styleDarkButton(buttonClearOutput);
		GithubTheme.styleDarkButton(buttonCopyToClipboard);
		GithubTheme.styleDarkLabel(label);
		GithubTheme.styleDarkTextField(inputSizePassword);
		GithubTheme.styleDarkTextField(outputPassword);
		GithubTheme.styleDarkPanel(mainPanel);
		GithubTheme.styleDarkFrame(this);

		this.setUndecorated(true);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Random Password Generator");
		this.setSize(350, 200);
		
		this.setCustomTitlebar();
		this.setMainPanel();
		this.add(mainPanel);
		
		this.setVisible(true);
	}

	private void setCustomTitlebar() {
		this.setUndecorated(true); // penting
		this.setLayout(new BorderLayout());

		// Buat title bar
		JPanel titleBar = new JPanel(new BorderLayout());
		titleBar.setBackground(new Color(36, 41, 46));
		titleBar.setPreferredSize(new Dimension(650, 30));

		JLabel titleLabel = new JLabel("  Random Password Generator");
		titleLabel.setForeground(Color.WHITE);

		JButton closeButton = new JButton("✕");
		closeButton.setFocusPainted(false);
		closeButton.setBorder(null);
		closeButton.setBackground(new Color(36, 41, 46));
		closeButton.setForeground(Color.WHITE);
		closeButton.setPreferredSize(new Dimension(40, 30));
		closeButton.addActionListener(e -> System.exit(0));

		titleBar.add(titleLabel, BorderLayout.WEST);
		titleBar.add(closeButton, BorderLayout.EAST);

		// Enable drag
		titleBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		titleBar.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				PasswordGeneratorAppGui.this.setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
			}
		});

		// Tambahkan ke frame
		this.add(titleBar, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER); // <- panel utama kamu

	}
	
	private void setMainPanel () {
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setPreferredSize(new Dimension(650, 250));

		gridConstraint.insets = new Insets(7, 5, 5, 5);
		gridConstraint.fill = GridBagConstraints.HORIZONTAL;
		
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
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		
		// set attribute for component
		inputSizePassword.setText("");
		inputSizePassword.setHorizontalAlignment(SwingConstants.CENTER);
		inputSizePassword.setPreferredSize(new Dimension(125, 25));
		inputSizePassword.setFocusable(true);

		//listener for input text
		inputSizePassword.getDocument().addDocumentListener(new DocumentListener() {
			private void checkValue() {
				String text = inputSizePassword.getText();
				int sizeInput = 0;
				
				if (text.length() > 10) {
					outputPassword.setText("Size Password is too long...");
					buttonGenerateRandomPassword.setEnabled(false);
					buttonClearOutput.setEnabled(false);
					buttonCopyToClipboard.setEnabled(false);
				}

				if (!text.isEmpty()) {
					sizeInput = Integer.parseInt(text);
				} else {
					// error
				}

				// check if size is less than 5
				if(sizeInput < 5 && text.length() == 1) {
					outputPassword.setText("Size Password is too short...");
					buttonGenerateRandomPassword.setEnabled(false);
					buttonClearOutput.setEnabled(false);
					buttonCopyToClipboard.setEnabled(false);
				} else {
					outputPassword.setText("");
					buttonGenerateRandomPassword.setEnabled(!text.isEmpty());
					buttonClearOutput.setEnabled(!text.isEmpty());
					buttonCopyToClipboard.setEnabled(!text.isEmpty());
				}
			}

			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				checkValue();
			}
			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				checkValue();
			}
			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				checkValue();
			}
		});

		// set input only number
		inputSizePassword.addKeyListener(new KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				char key = e.getKeyChar();

				if(!(Character.isDigit(key)) && (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_DELETE)) {
					e.consume();
				}
				if(inputSizePassword.getText().equals("")) {
					inputSizePassword.repaint();
					inputSizePassword.setText("");
					inputSizePassword.setEditable(true);
					inputSizePassword.setEnabled(true);
					inputSizePassword.requestFocusInWindow();

				}
			}
		});
	}
	
	private void setOutputPassword() {
		// set coordinate at panel
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 4;
		gridConstraint.fill = GridBagConstraints.HORIZONTAL;
				
		// set attribute for component
		outputPassword.setText("");
		outputPassword.setHorizontalAlignment(SwingConstants.LEFT);
		outputPassword.setPreferredSize(new Dimension(250, 25));
		outputPassword.setFocusable(false);
		outputPassword.setEditable(false);
	}
	
	private void setButtonGenerateRandomPassword () {
		// set coordinate at panel
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 2;
		gridConstraint.gridwidth = 1;
				
		// set attribute for component
		buttonGenerateRandomPassword.setText("Generate");
		buttonGenerateRandomPassword.setHorizontalAlignment(SwingConstants.CENTER);
		
		// add listener to button
		buttonGenerateRandomPassword.setEnabled(false);
		buttonGenerateRandomPassword.addActionListener(this);
	}
	
	private void setButtonClearOutput() {
		// set coordinate at panel
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 2;
		gridConstraint.gridwidth = 2;
				
		// set attribute for component
		buttonClearOutput.setText("Clear");
		buttonClearOutput.setHorizontalAlignment(SwingConstants.CENTER);
		
		// add listener to button
		buttonClearOutput.setEnabled(false);
		buttonClearOutput.addActionListener(this);
	}
	
	private void setButtonCopyToClipboard() {
		// set coordinate at panel
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 3;
		gridConstraint.gridwidth = 4;
				
		// set attribute for component
		buttonCopyToClipboard.setText("Copy");
		buttonCopyToClipboard.setHorizontalAlignment(SwingConstants.CENTER);
		
		// add listener to button
		buttonCopyToClipboard.setEnabled(false);
		buttonCopyToClipboard.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		String text = source.getText();

		int sizeInput = Integer.parseInt(inputSizePassword.getText());
		
		if(text == "Generate") {
			try {
				newPassword.setPasswordSize( sizeInput - 1 );
				newPassword.runningApp();
				outputPassword.setText( newPassword.getFinalPassword() );
				inputSizePassword.setEditable(false);
				
			} catch (IllegalArgumentException a) {
				System.out.println("error :  size under 5 char!");
				outputPassword.setText("Size Password is too short...");
			}

		} else if (text == "Clear") {
			inputSizePassword.setEditable(true);
			newPassword.setPasswordSize(0);
			inputSizePassword.setText("");
			outputPassword.setText("");
			
		} else if (text == "Copy") {
			StringSelection strSet = new StringSelection( outputPassword.getText() );
			copyToClipboard.setContents(strSet, strSet);
		}
	}
}
	