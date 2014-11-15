package client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import server.User;
import engine.Saver;

public class Test  {
        
	public static void main(String[] args)
	{
	    ServerComms clientt = new ServerComms(2578, null);
	    User bob = new User("bob", null, false );
	    Saver boby = new Saver();
	    if(clientt.isConnected())
	    {
	        clientt.authenticate("bob", "bob".toCharArray());
	        System.out.println(clientt.save(bob));
	        System.out.println((User)clientt.load());
	    }
	    else
	        System.out.println("fail");
	    clientt.close();
	}
	
	

}
