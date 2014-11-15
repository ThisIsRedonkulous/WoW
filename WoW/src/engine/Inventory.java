package engine;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageObserver;
import java.awt.image.Kernel;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import UsefulThings.ImageGetter;


public class Inventory {
BufferedImage background, filter;
Item[][] shit;
public Inventory (int rows, int col)
{
	shit = new Item[col][rows];
	filter = ImageGetter.getImage("images/badfliter.png");
}
public void setImage(BufferedImage temp)
{
	background=temp;
}
public void paint(Graphics g, ImageObserver o)
{
	g.drawImage(background, 0, 0, o);
	g.drawImage(filter, 0, 0, o);
	for (int j=0; j<shit.length; j++)
	{
	for (int x = 0; x<shit[0].length; x++)
	{
		if (shit[j][x] != null)
			g.drawImage(shit[j][x].getImage(), x*128+64, j*128+64, o);
	}
	}
}
public boolean add(Item titem)
{//returns true if the item was added tot he invantory
	for (int j=0; j<shit.length; j++)
	{
	for (int x =0; x<shit[0].length; x++)
	{
		if (shit[j][x]==null)
		{
			shit[j][x]=titem;
			return true;
		}
	}
	}
	return false;
}
public void use(int x, int y)
{
	if (shit[x][y]!=null && shit[x][y].canDrop())
	{
	shit[x][y].use();
	shit[x][y]=null;
	}
	else if(shit[x][y]!=null)
		shit[x][y].use();
}
public List<Item> getContents()
{//returns a list of all the stuff in the inventory
	List<Item> stuff = new LinkedList<Item>();
	for (int x = 0; x<shit.length; x++)
	{
		for (int j = 0; j<shit[0].length; j++)
			if (shit[x][j]!=null) stuff.add(shit[x][j]);
	}
	return stuff;
}
public void remove(Item titem) {
	Item temp;
	for (int x=0; x<shit.length; x++)
	{
		for (int j = 0; j<shit[0].length; j++)
		{
			if (shit[x][j]==null) continue;
			temp = shit[x][j];
			if (temp.equals(titem)) shit[x][j]=null;
			
		}
	}
	
}
}
