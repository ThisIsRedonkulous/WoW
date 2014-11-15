//yeah this is a pretty yitty class. It is supposed to be for the ferret, and it is, but I didn't make anything look nice.
//Not my job
package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JPanel;

import UsefulThings.ImageGetter;

//import engine.Walmart.KeyBoard;
//import engine.Walmart.Mouse;

public class DatFerret extends Fight{
Inventory inventory;
private static BufferedImage pic = ImageGetter.getImage("images/ferret.jpg");
private static String[] taco = {"fight", "items", "rocks", "run"};
JavaHasNoStructs struct;
Speech speech;
public DatFerret (JavaHasNoStructs s)
{
	super(pic, taco);
	struct = s;
	setKeyListener(new KeyBoard());
}
//public void paintComponent(Graphics g)
//{
//	super.paintComponent(g);
//	g.setColor(Color.GRAY);
//	g.fillRect(0, 0, 400, 400);
//	if(end)
//	{
//		speech.paint(g, 400, 400);
//	}
//	else
//	{
//	g.setColor(Color.BLACK);
//	g.drawString("NO DADDY NO", 40, 300);
//	}
//}
public Dimension getPreferredSize()
{
	//return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
	return struct.window.getPreferredSize();
}
public void option1()
{
	//searches for some food to give the ferret, if it finds none then one of the players items is returned.
	ListIterator<Item> i = struct.inventory.getContents().listIterator();
	Item titem = null;
	while (i.hasNext())
	{
		titem = i.next();
		if (titem instanceof Food) break;
	}
	struct.inventory.remove(titem);
	if (titem!=null)
		struct.window.walmart.randomlyAddUsable(titem);
	String[] yay = new String[1];
	if (titem instanceof Food) 
	{
		yay[0] = "Ferret is a happy lad";
	}
else {
		yay[0] = "Ferret is unhappy, so he stole your item. You can find it elsewhere in the store";
}
	struct.window.getWalmart().speech(yay);
	struct.window.toWalmart();
}
@Override
public void refreshListeners()
{
    setKeyListener(new KeyBoard());
}
@Override
public void option2() {
	// TODO Auto-generated method stub
	
}
@Override
public void option3() {
	// TODO Auto-generated method stub
	
}
@Override
public void option4() {
	// TODO Auto-generated method stub
	
}

}
