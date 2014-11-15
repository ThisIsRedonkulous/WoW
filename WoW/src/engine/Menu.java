package engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.*;

public class Menu implements ItemListener, ActionListener{
//	JTextArea output;
//	JScrollPane pane;
    private Window wondow;
    private JMenuItem newGame, save, settings, quit, help;
    public static JCheckBoxMenuItem fullscreeB;
    public Menu(Window w)
    {
    	wondow=w;
    }
    public JMenuBar makeMenu()
    {
    	//lol
    	//yeah I think I'll have to explain this in person. I'll try with camments, but lol
    	//got us a bar
    	JMenuBar bar = new JMenuBar();
    
    	//got us a menu for this bar
    	JMenu menu = new JMenu("File");
    	//sets a as the buttont hat automatically open the menu (this doesnt work if you are in game dont worry about it)
    
    	menu.setMnemonic(KeyEvent.VK_A);
    	//describes the menu's stuff
    	menu.getAccessibleContext().setAccessibleDescription("Menu things");
    	//puts the menu into the bar
    	bar.add(menu);
    	
    	//makes new save file and lets user choose between boy or girl with dialog making sure user wants to do this
    	newGame = new JMenuItem("Start new Game");
    	newGame.addActionListener(this);
    	menu.add(newGame);
    	
    	//saves current state of game with dialog asking if they wants to save
    	save = new JMenuItem("Save");
    	save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
    	save.addActionListener(this);
    	menu.add(save);
    	
    	//opens settings window
    	settings = new JMenuItem("Settings");
    	settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
    	settings.addActionListener(this);
    	menu.add(settings);
    	
    	help = new JMenuItem("Help");
    	help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
    	help.addActionListener(this);
    	menu.add(help);
    	
    	//fucking quits the game!
    	quit = new JMenuItem("Quit");
    	quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
    	quit.addActionListener(this);
    	menu.add(quit);
    	
    	
    //	//oh shizzle, an item for our menu that will open with the press of T
    //
    //	JMenuItem item = new JMenuItem("text", KeyEvent.VK_T);
    //	//sets the quick keystroke for it to be alt+1, that is what that altmask is
    //	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    //	//sets its description
    //	item.getAccessibleContext().setAccessibleDescription("no use");
    //	//sets its action listener, like the KeyListeners and MouseListeners, to be this class right here, chances are you will change this
    //	item.addActionListener(this);
    //	menu.add(item);
    //	//makes an icon for the menu item below
    //	ImageIcon icon = createImageIcon("images/allblack22.jpg");
    //	//new item here
    //	item = new JMenuItem("text+image", icon);
    //	//sets its corresponding key to be b (you can do it like the other tiem, but this is just another way
    //	item.setMnemonic(KeyEvent.VK_B);
    //	item.addActionListener(this);
    //	menu.add(item);
    //	//makes a separating bar thing
    //	menu.addSeparator();
    //
    //	// a button group, like a group, of buttons, wooooooah
    //
    //	ButtonGroup group = new ButtonGroup();
    //	//these are those little circle button things
    //	JRadioButtonMenuItem rbItem= new JRadioButtonMenuItem("radio menu");
    //	//defaults to this one being selected
    //	rbItem.setSelected(true);
    //	rbItem.setMnemonic(KeyEvent.VK_R);
    //	rbItem.addActionListener(this);
    //	//you must add to the group and the menu
    //	group.add(rbItem);
    //	menu.add(rbItem);
    //	rbItem = new JRadioButtonMenuItem("antoher");
    //	rbItem.setMnemonic(KeyEvent.VK_O);
    //	rbItem.addActionListener(this);
    //	group.add(rbItem);
    //	menu.add(rbItem);
//    	menu.addSeparator();
    //	//these are checkboxes
    //	JCheckBoxMenuItem cbItem = new JCheckBoxMenuItem("check box");
    //	cbItem.setMnemonic(KeyEvent.VK_C);
    //	//these take item listeners, not action listeners
    //	cbItem.addItemListener(this);
    //	menu.add(cbItem);
    //	//yo dawg, we heard you like menus, so we put a menu in your menu so you can men while you u while you men while you u
    //	JMenu subMenu = new JMenu("sub-menu");
    //	subMenu.setMnemonic(KeyEvent.VK_S);
    //	item = new JMenuItem("Shit is cash in this submenu");
    //	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
    //	item.addActionListener(this);
    //	subMenu.add(item);
    //	menu.add(subMenu);
    	
    	menu = new JMenu("View");
    	//menu.setMnemonic(KeyEvent.VK_N);
    	menu.getAccessibleContext().setAccessibleDescription("Nope");
    	bar.add(menu);
    	bar.setPreferredSize(new Dimension(200, 20));
    	fullscreeB= new JCheckBoxMenuItem("Fullscreen");
    	//defaults to this one being selected
    	fullscreeB.setSelected(wondow.isUndecorated());
    	fullscreeB.setMnemonic(KeyEvent.VK_F);
    	fullscreeB.addActionListener(this);
    	//you must add to the group and the menu
//        	group.add(rbItem);
    	menu.add(fullscreeB);
    	return bar;
    }
    //thses are those right click menus that you see. We dont have a use so far as I can tell but ill leave the code here
    //public void createPopup()
    //{
    //	JPopupMenu popup = new JPopupMenu();
    //	JMenuItem item = new JMenuItem("yup");
    //	item.addActionListener(this);
    //	popup.add(item);
    //	MouseListener popupListen = new PopupListener(popup);
    //	output.addMouseListener(popupListen);
    //}
    
    //public Container createPanel()
    //{
    //	JPanel content = new JPanel(new BorderLayout());
    //	content.setOpaque(true);
    //	output = new JTextArea(5, 30);
    //	output.setEditable(false);
    //	pane = new JScrollPane(output);
    //	content.add(pane, BorderLayout.CENTER);
    //	return content;
    //}
    
    protected static ImageIcon createImageIcon(String path)
    {
    	java.net.URL imgURL = Menu.class.getResource(path);
    	if (imgURL != null) return new ImageIcon(imgURL);
    	else System.err.println("nope");
    	return null;
    }
    //public void rundis()
    //{
    //	JFrame frame = new JFrame("HelloWorld");
    //
    //	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //	Menu demo = new Menu();
    //	frame.setJMenuBar(demo.makeMenu());
    ////	frame.setContentPane(demo.createPanel());
    ////	demo.createPopup();
    //	frame.setSize(450, 260);
    //	frame.setVisible(true);
    //}
    @Override
    //OH LOOK THERE ARE THE ACTION AND ITEM LISTENERS
    //They work the same as the KeyBoard and Mouse listeners for the most part.
    public void itemStateChanged(ItemEvent arg0) {
//    	if(arg0.getSource().equals(fullscreeB))
//    	{
//    		wondow.fullscreen();
//    	}
    //	JMenuItem source = (JMenuItem)(arg0.getSource());
    //	String s = "Item event from " + source.getText() +  ". It is now " + ((arg0.getStateChange() == ItemEvent.SELECTED) ? "selected":"unselected");
    //	output.append(s+"\n");
    //	output.setCaretPosition(output.getDocument().getLength());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	JMenuItem source = (JMenuItem)(e.getSource());
    	String s = "Action went down. It came from " + source.getText();
        System.out.println(s);
    	if(e.getSource()==save)
    	{
    		if(JOptionPane.showConfirmDialog(settings, "Are you shure you want to save?", "", JOptionPane.YES_NO_OPTION)==0)
    		{	
    			System.out.println("testnvuoe4");
    			wondow.save();
    		}
    	}
    	else if(e.getSource().equals(quit))
    	{
    		if(JOptionPane.showConfirmDialog(settings, "Are you shure you want to quit? \n All unsaved data will be lost.", "", JOptionPane.YES_NO_OPTION)==0)
    		{
    			wondow.comms.save((Serializable) wondow.saveFile.getSave());
    			System.exit(0);
    		}
    	}
    	else if(e.getSource().equals(settings))
    	{
    		wondow.settings();
    		System.out.println("fiopehfi9vbj ");
    	}
    	else if(e.getSource().equals(newGame))
    	{
    		if(JOptionPane.showConfirmDialog(settings, "Are you shure you want to start a new game?\nYour previous save file will be overwritten.", "", JOptionPane.YES_NO_OPTION)==0)
    		{
    			
    		}
    	}
    	else if(e.getSource().equals(fullscreeB))
    	{
    		if(wondow.isUndecorated())
    		{
	    		wondow.wondow(600, 800);
	    		System.out.println("Fullscreen was done.");
	    		fullscreeB.setState(false);
    		}
    		else
    		{
    			wondow.fullscreen();
    			fullscreeB.setState(true);
    		}
    	}
    	else if(e.getSource().equals(help))
    	{
    		JOptionPane.showMessageDialog(help, "Movement\n\tUse W+A+S+D to walk around and to select options in a fight\nInteraction:\n\tUse \"E\" to interact with whatever is in front of your charactor\n\tWhile in fights and in the inventroy,\n\tyou may select options and drop items with \n\tPress \"i\" to open up your inventory and close it again", "", JOptionPane.YES_NO_OPTION);
    	}
    //	output.append(s+"\n");
    //	output.setCaretPosition(output.getDocument().getLength());
    }
}
