package client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import client.ClientGUI.ClickHandler;
import engine.Window;

public class SettingsWindow extends JFrame implements WindowListener, ActionListener{
	JButton changePassB, frameRateB;
	JTextField frameRateT;
	JPasswordField oldPassPF, newPassPF, newPassConfPF;
	JLabel oldPassL, newPassL, newPassConfL, frameRateL; 
	JPanel oldPassP, newPassP, newPassConfP, changePassP, frameRateP;
	JOptionPane optPane;
	ServerComms comms;
	Window wondow;
	public SettingsWindow(Window win, ServerComms comm)
	{
		wondow = win;
		optPane = new JOptionPane();
		comms = comm;
	    this.setLayout(new FlowLayout());

	    oldPassP = new JPanel();
	    oldPassL = new JLabel("Old Password: ");
	    oldPassPF = new JPasswordField(12);
	    oldPassP.add(oldPassL);
	    oldPassP.add(oldPassPF);
	    this.getContentPane().add(oldPassP);
	    
	    newPassP = new JPanel();
	    newPassL = new JLabel("New Password: ");
	    newPassPF = new JPasswordField(12);
	    newPassP.add(newPassL);
	    newPassP.add(newPassPF);
	    this.getContentPane().add(newPassP);
	    
	    newPassConfP = new JPanel();
	    newPassConfL = new JLabel("Confirm Password: ");
	    newPassConfPF = new JPasswordField(12);
	    newPassConfP.add(newPassConfL);
	    newPassConfP.add(newPassConfPF);
	    this.getContentPane().add(newPassConfP);
	    
	    changePassP = new JPanel();
	    changePassB = new JButton();
	    changePassB.setText("Change Password");
	    changePassB.addActionListener(this);
	    changePassP.add(changePassB);
	    this.getContentPane().add(changePassP);
	    
	    frameRateP = new JPanel();
	    frameRateL = new JLabel("Frame Rate: ");
	    frameRateT = new JTextField(3);
	    frameRateT.setText(((Integer)wondow.framerate).toString());
	    frameRateB = new JButton();
	    frameRateB.setText("Set");
	    frameRateB.addActionListener(this);
	    frameRateP.add(frameRateL);
	    frameRateP.add(frameRateT);
	    frameRateP.add(frameRateB);
	    this.getContentPane().add(frameRateP);
	    
	    
	    this.setTitle("Settings");
	    this.setSize(290, 215);
	    this.setLocation(wondow.getWidth()/2, wondow.getHeight()/2);
	    this.addWindowListener(this);
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(changePassB))
		{
			System.out.println("tesefetaf");
			System.out.println(newPassPF.getPassword());
			System.out.println(newPassConfPF.getPassword());
			if(oldPassPF.getPassword().length>0 && comms.passwd(comms.getUser(), oldPassPF.getPassword(), newPassPF.getPassword()))
			{
				if(newPassPF.getPassword().length>0 &&newPassPF.getPassword().equals(newPassConfPF.getPassword()))
					optPane.showMessageDialog(this, "You successfully changed your password.", "Congradulations", optPane.PLAIN_MESSAGE);
				else
					optPane.showMessageDialog(this, "New passwords do not match.", "You fucked up.", optPane.ERROR_MESSAGE);
			}
			else
			{
				optPane.showMessageDialog(this, "Old password does not match.", "You fucked up.", optPane.ERROR_MESSAGE);
			}
		}
		else if(arg0.getSource().equals(frameRateB))
		{
			wondow.framerate = (Integer.parseInt(frameRateT.getText()));
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
