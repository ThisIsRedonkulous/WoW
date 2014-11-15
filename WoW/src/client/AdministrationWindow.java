package client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.ClientGUI.WindowWatcher;
import client.ClientGUI.ClickHandler;

public class AdministrationWindow extends JFrame
{
	JPanel panelUserChoose, panelOptions;
	JScrollPane userListPane;
	JTextField user;
	JLabel userLab;
	JList userList;
	JButton removeUser, makeAdmin, makeNorm, userListButton;
	public AdministrationWindow(String[] userArray, WindowListener winWatch, ClickHandler clicks)
	{
		userListPane = new JScrollPane();
		userListPane.setSize(440, 150);
		panelOptions = new JPanel();
		
		this.setLayout(new FlowLayout());
		this.getContentPane().add(userListPane);
		this.getContentPane().add(panelOptions);
		
		userList = new JList(userArray);
		userList.setSize(440, 150);
		userList.setVisibleRowCount(4);
		userListPane.getViewport().setView(userList);
		userListPane.setPreferredSize(new Dimension(250, 80));
		
		removeUser = new JButton("Remove");
		removeUser.addActionListener(clicks);
		makeAdmin = new JButton("Make Admin");
		makeAdmin.addActionListener(clicks);
		makeNorm = new JButton("Make nomrmal user");
		makeNorm.addActionListener(clicks);
		panelOptions.add(removeUser);
		panelOptions.add(makeAdmin);
		panelOptions.add(makeNorm);
		
		this.setTitle("Administration");
		setSize(440,170);
		this.setLocationRelativeTo(null);
		this.addWindowListener(winWatch);
	}
	public void updateList(String[] newUserArray)
	{
		userList.setListData(newUserArray);
	}
}