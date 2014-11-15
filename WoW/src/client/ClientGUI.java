package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import engine.Window;

public class ClientGUI extends JFrame {
	JPanel panelLogin, panelPass, panelUser, panelRegister, panel;
	JButton loginButton, registerButton, forgotPassButton;
	JTextField usernameField;
	JPasswordField passwordField;
	JLabel userLabel, passLabel;
	
	ClickHandler clicks;
	RegistrationWindow reg;
	AdministrationWindow admin;
	
	Window game;
	ServerComms client = new ServerComms(2578, game);
	
	public ClientGUI()
	{
		clicks = new ClickHandler();
		if(client.isConnected())
			guiInit();
		else
		{
			JOptionPane.showMessageDialog(this, "The Server is down or your internet conection is fucked.", "Connection error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public final void guiInit()
	{
		//creates panels for each section(row)
		panelUser = new JPanel();
		panelPass = new JPanel();
		panelLogin = new JPanel();
		panelRegister = new JPanel();
		panel = new JPanel();
		
		//adds panels to window
		this.setLayout(new FlowLayout());
		this.getContentPane().add(panelUser);
		this.getContentPane().add(panelPass);
		this.getContentPane().add(panelRegister);
		this.getContentPane().add(panelLogin);
		this.getContentPane().add(panel);
		
		//creates username label
		userLabel = new JLabel("Username:");
		//field for entering username
		usernameField = new JTextField(10);
		panelUser.add(userLabel);
		panelUser.add(usernameField);
		
		//password label
		passLabel = new JLabel("Password:");
		//password field
		passwordField = new JPasswordField(10);
		panelPass.add(passLabel);
		panelPass.add(passwordField);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(clicks);
		loginButton.setDefaultCapable(true);//makes this button auto higlighted so when enter is pressed this is sleected
		getRootPane().setDefaultButton(loginButton);
		panelLogin.add(loginButton);
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(clicks);
		panelRegister.add(registerButton);
		
		forgotPassButton = new JButton("Forgot Password");
		forgotPassButton.addActionListener(clicks);
		panel.add(forgotPassButton);
		
		this.setTitle("World of Walmart");
		setSize(250, 200);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowWatcher());
	}
	
	public class ClickHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==loginButton)
			{
				String username = usernameField.getText().trim();
				if(username!="" && (passwordField.getPassword()[0]!=0))
				{
					System.out.println(username+" is trying to log in");
					char[] clearPassword = passwordField.getPassword();
					System.out.println("login");
					if(client.authenticate(username, clearPassword))
					{
						System.out.println("login sucsessful");
						JOptionPane.showMessageDialog(ClientGUI.this, "You logged in!!", "w00t!", JOptionPane.PLAIN_MESSAGE);
						if(client.isAdmin())
						{
							ClientGUI.this.setVisible(false);
							System.out.println("GUI getiing user list");
							admin = new AdministrationWindow(client.getUserList(), new WindowWatcher(), clicks);
							System.out.println("has user list");
							admin.setVisible(true);
							System.out.println("administor");
						}
						else
						{
							ClientGUI.this.setVisible(false);
							try {
								game = new Window(client);
								game.start();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else
					{
						System.out.println("login fail.");
						JOptionPane.showMessageDialog(ClientGUI.this, "FAIL!", "You Suck at this.", JOptionPane.PLAIN_MESSAGE);
					}
					clearPassword = null;
					passwordField.setText(null);
				}
				else
					JOptionPane.showMessageDialog(ClientGUI.this, "Please enter your username and password", "FAIL!", JOptionPane.PLAIN_MESSAGE);
			}
			else if(e.getSource()==registerButton)
			{
				reg = new RegistrationWindow(new WindowWatcher(), clicks, ClientGUI.this.usernameField.getText());
				ClientGUI.this.setVisible(false);
				reg.setVisible(true);
				System.out.println("register");
			}
			else if(e.getSource()==forgotPassButton)
			{
				JOptionPane.showMessageDialog(ClientGUI.this, "Looks like you might have a problem.\n Good luck remebering ur password!", "SUX!", JOptionPane.PLAIN_MESSAGE);
			}
			else if(reg!=null)
			{
				if(e.getSource()==reg.submitRegButton)
				{
					String username = reg.newUsernameField.getText().trim();
					if(username.length()>2 && username.length()<14)
					{
						char[] clearPassword = reg.newPasswordField.getPassword();
						if(clearPassword.length>2)//switch to six when done testing
						{
							if(client.register(username, clearPassword))
							{
								JOptionPane.showMessageDialog(ClientGUI.this, username+" has been registered!!", "w00t!", JOptionPane.PLAIN_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(ClientGUI.this, "Registration failed.\n"+username+" already exists.", "Error", JOptionPane.ERROR_MESSAGE);
							}
							clearPassword=null;
							reg.newPasswordField.setText(null);
							reg.setVisible(false);
							usernameField.requestFocusInWindow();
							ClientGUI.this.setVisible(true);
							reg = null;
						}
						else
							JOptionPane.showMessageDialog(ClientGUI.this, "Password must be atleast six characters long.", "Password too short", JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(ClientGUI.this, "Usernames must be between two and 14 characters long.\nPasswords must be atleast six characters long.", "Username too short", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(admin!=null)
			{
				Object[] selectedUsers;
				if(e.getSource()==admin.removeUser)
				{
					selectedUsers = admin.userList.getSelectedValues();
					for(Object removeUserd:selectedUsers)
					{
						System.out.println(removeUserd+" was removed: "+client.adminTask("remove", (String) removeUserd));
					}
					admin.updateList(client.getUserList());
					admin.userList.requestFocusInWindow();
				}
				else if(e.getSource()==admin.makeAdmin)
				{
					selectedUsers = admin.userList.getSelectedValues();
					for(Object userd:selectedUsers)
					{
						System.out.println(userd+" is now an admin: "+client.adminTask("admin", (String)userd));
					}
					admin.updateList(client.getUserList());
					admin.userList.requestFocusInWindow();
				}
				else if(e.getSource()==admin.makeNorm)
				{
					selectedUsers = admin.userList.getSelectedValues();
					for(Object userd:selectedUsers)
					{
						System.out.println(userd+" is no longer an admin: "+client.adminTask("normal", (String)userd));
					}
					admin.updateList(client.getUserList());
					admin.userList.requestFocusInWindow();
				}
			}
		}
	}
	
	
	public  class WindowWatcher implements WindowListener
	{

		public void windowClosing(WindowEvent e) {
			if(e.getSource() instanceof RegistrationWindow)
			{
				reg.setVisible(false);
				usernameField.requestFocusInWindow();
				ClientGUI.this.setVisible(true);
				reg = null;
			}
			else
			{
				client.close();
				System.exit(0);
			}
		}

		public void windowActivated(WindowEvent e) {}
		public void windowClosed(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowOpened(WindowEvent e) {}
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				ClientGUI cgui = new ClientGUI();
				cgui.setVisible(true);
			}
		});
	}
}

