package engine;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.Serializable;

import client.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.SaveGameFile;

public class Window extends JFrame{
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice defaultScreen = env.getDefaultScreenDevice();
	DisplayMode defaultDisplay = defaultScreen.getDisplayMode();
	final int SCREEN_WIDTH = defaultDisplay.getWidth();
	final int SCREEN_HEIGHT = defaultDisplay.getHeight();
	Thread animationThread;
	public int framerate = 10;
	JavaHasNoStructs struct;
	Panel curPanel;
	DatFerret ferret;
	Walmart walmart;
	ServerComms comms;
	public SaveGameFile saveFile;
	SettingsWindow settings;
	public Window(ServerComms comm) throws Exception
	{
		super("World of Walmart");
		comms=comm;
		saveFile = new SaveGameFile("test.sav");
		struct = new JavaHasNoStructs(new Inventory(4,4), this);
		walmart = new Walmart(struct);
		ferret = new DatFerret(struct);
		curPanel = new TitleScreen(struct);
//		saveFile= new SaveGameFile(comms.getUser()+".sav");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(new Menu(this).makeMenu());
		this.setContentPane(curPanel);
		this.addKeyListener(curPanel.board);
//		this.addMouseListener(walmart.mouse);
		this.setVisible(true);
		pack();
		animationThread = new Thread()
		{
			public void run()
			{
				while(true)
				{
					curPanel.update();
					repaint();
					try
					{
						sleep(500/framerate);
					}
					catch(InterruptedException e)
					{
						System.out.print("what");
					}
				}
			}
		};
		animationThread.setPriority(Thread.MAX_PRIORITY);
	}
	public Window(SaveGameFile save, ServerComms comm) throws Exception
	{
		//this shit
		super("World of Walmart");
		comms=comm;
		struct = new JavaHasNoStructs(new Inventory(4,4), this);
		walmart = new Walmart(struct);
		ferret = new DatFerret(struct);
		curPanel = new TitleScreen(struct);
		saveFile= save;
		Saver saver = (Saver)saveFile.getSave();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(new Menu(this).makeMenu());
		this.setContentPane(curPanel);
//		this.addKeyListener(walmart.board);
//		this.addMouseListener(walmart.mouse);
		this.setVisible(true);
		pack();
		animationThread = new Thread()
		{
			public void run()
			{
				while(true)
				{
					curPanel.update();
					repaint();
					try
					{
						sleep(1000/framerate);
					}
					catch(InterruptedException e)
					{
						System.out.print("what");
					}
				}
			}
		};
	}
	public void fullscreen()
	{
		//sets things to fullscreen. be warned, the map drawing get EXTREMEMLY slow when you have a huge monitor
		//this is all really optimized for 800x600
		this.dispose();
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(new Menu(this).makeMenu());
		this.setContentPane(walmart);
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		walmart.resize(SCREEN_HEIGHT, SCREEN_WIDTH);
	}
	public void wondow(int height, int width)
	{
		this.dispose();
		this.setUndecorated(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(new Menu(this).makeMenu());
		this.setContentPane(walmart);
		this.setVisible(true);
		this.setSize(width, height);
		walmart.resize(height, width);
	}
	public Walmart getWalmart()
	{
		return walmart;
	}
	public void start()
	{
	       animationThread.start();
	}
	@SuppressWarnings("static-access")
	public void pause()
	{
		animationThread.yield();
	}
	public Dimension getPreferredSize()
	{
	//	return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
		return new Dimension(800, 600);
	}
	public void toFerret()
	{
		this.removeKeyListener(curPanel.board);
		this.removeMouseListener(curPanel.mouse);
		this.setContentPane(ferret);
	    curPanel= ferret;
		this.addKeyListener(curPanel.board);
		this.addMouseListener(curPanel.mouse);
		pack();
	} 
	public void toWalmart()
	{
		this.removeKeyListener(curPanel.board);
		this.removeMouseListener(curPanel.mouse);
		this.setContentPane(walmart);
	    curPanel= walmart;
		this.addKeyListener(curPanel.board);
		this.addMouseListener(curPanel.mouse);
		pack();
	}
	public void setPanel(Panel fight)
	{
		this.removeKeyListener(curPanel.board);
		this.removeMouseListener(curPanel.mouse);
	    curPanel = fight;
	    this.setContentPane(curPanel);
	    this.addKeyListener(curPanel.board);
	    this.addMouseListener(curPanel.mouse);
	    pack();
	}
	public void save()
	{
		Saver save = new Saver();
		if (curPanel==walmart)
		{
		save.tcurPanel = 1;
		saveFile.save(save);
		}

	}
	public JavaHasNoStructs getStruct()
	{
		return struct;
	}
	public void settings()
	{
		settings = new SettingsWindow(this, comms);
//		this.add(settings);
		settings.setVisible(true);
//		curPanel.add(settings);
		this.transferFocus();
	}
}
