package client;

import java.awt.FlowLayout;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.ClientGUI.ClickHandler;
import client.ClientGUI.WindowWatcher;

public class RegistrationWindow extends JFrame
{
	JPanel panelUserReg, panelPassReg, panel, panelSubmitReg;
	JButton submitRegButton;
	JLabel userRegLabel, passRegLabel, emailLabel, adressLabel, cityLabel, stateLabel, zipLabel;
	JTextField newUsernameField, emailField, addressOneField, addressTwoField, cityField, stateField, zipField, mobileNumField, homeNumField;
	JPasswordField newPasswordField;
	ClientGUI main;
	public RegistrationWindow(WindowListener winWatch, ClickHandler clicks, String username)
	{
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		this.getContentPane().add(panel);
//		
		userRegLabel = new JLabel("Username:");
		newUsernameField = new JTextField(10);
		newUsernameField.setText(username);
		panel.add(userRegLabel);
		panel.add(newUsernameField);
		
		passRegLabel = new JLabel("Password:");
		newPasswordField = new JPasswordField(12);
		panel.add(passRegLabel);
		panel.add(newPasswordField);
		
		emailLabel = new JLabel("Email:");
		emailField = new JTextField(13);
		panel.add(emailLabel);
		panel.add(emailField);
		
		submitRegButton = new JButton("Submit");
		submitRegButton.addActionListener(clicks);
		panel.add(submitRegButton);
		getRootPane().setDefaultButton(submitRegButton);
		
		this.setTitle("Registration");
		setSize(250, 450);
		this.setLocationRelativeTo(null);
		this.addWindowListener(winWatch);
	}
}
