//No, not a data structure, well it kind of is, but it really isn't
package engine;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

import UsefulThings.ImageGetter;


public class Map implements Moveable {
	//Stores a buttload of MapNodes in a grid. How big is this grid? HOWEVER BIG YOU WANT IT BRO
	MapNode[][] grid;
	int x, y, height, width, xdistance, ydistance;
	public String url;
	public Map(int tx, int ty, int height, int width)
	{
		grid = new MapNode[width][height];
		this.height = height;
		this.width = width;
		x=tx;
		y=ty;
		ImageGetter.getImage("images/sprite_sheet.png");
		xdistance=800/32;
		ydistance=600/32;
	}
	public boolean passable(int x, int y)
	{
		return grid[x][y].isPassable();
	}
	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub
		x-=dx;
		y-=dy;
	}
	public void loadMap(File page, BufferedImage reference) throws Exception
	{ //makes the map out of the file you pass in. You can pass in your own reference image,
		//or just pass in null and use the default one
		//make sure you grid is the same size as the file you pass in. you will get out of bounds'd if you dont
	    url = page.getAbsolutePath();
		if (reference==null)
			reference=ImageGetter.getImage("images/sprite_sheet.png");
		//WARNING: MAKE THE IMAGE SOME MULTIPLE OF 32 BY SOME MULTIPLE OF 32. I WILL THROW ERRORS AND FEEL NO REMORSE
		if (reference.getHeight()%32!=0 || reference.getWidth()%32!=0)
			throw new Exception("I warned you bro, I warned you about making reference.png not a grid of 32 by 32 tiles. \nRemorse==0: true");
		try {
			Scanner scan = new Scanner(page);
			int lx = 0;
			int j;
			int col = reference.getWidth()/32;
			Image image;
			while (scan.hasNext())
			{
				j=scan.nextInt();
				image = reference.getSubimage((j%col)*32, j/col*32, 32, 32);
				grid[lx%width][lx/width]= new MapNode(image, lx%width, lx/width);
				if (j>9)
				{
					grid[lx%width][lx/width].setPassable(false);
				}
				lx++;
			}
		} catch (FileNotFoundException e) {
			System.out.print("ERROR 404 - FILE NOT FOUND");
		}
	}
	public int hashCode()
	{
		//ensures that this will be the first thing in the hashSet. Who said you cant organize hashes?
		return 0;
	}
	@Override
	public int getx() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int gety() {
		// TODO Auto-generated method stub
		return y;
	}
	public boolean addUseable(int x, int y, Useable item)
	{
		return grid[x+1][y+1].setUseable(item);
	}
	public boolean removeUseable(int x, int y, Useable item)
	{
		return grid[x+1][y+1].setUseable(item);
	}
	public Useable getItem(int x, int y)
	{
		//this is the tile, or grid, corfdinate, not the pixels
		grid[x][y].setPassable(true);
		return grid[x][y].getItem();
	}
	public Useable peekItem(int x, int y)
	{
		return grid[x][y].peekItem();
	}
	public void paint(Graphics g, ImageObserver o)
	{
		//pretty self evident, just renders the map.
		int xdis = x+xdistance;
		int ydis= y+ydistance;
		for (int b=x; b<grid.length && b<xdis; b++)
		{
			if (b>0)
			for (int j=y; j<grid[0].length && j<ydis; j++)
			{
				if (j>0)
				{
				g.drawImage(grid[b][j].tile, (b-x)*32, (j-y)*32, o);
				if (grid[b][j].hasItem())
				{
					g.drawImage((grid[b][j].peekItem()).getSprite(), (b-x)*32, (j-y)*32, o);
				}
				}
			}
		}
		//g.drawImage(background, x, y, o);
	}
	public void randomlyAddUseable(Item titem) {
		int bx = (int)(Math.random()*grid.length);
		int by = (int)(Math.random()*grid[0].length);
		while(!grid[bx][by].setUseable(titem));
		titem.setx(bx);
		titem.sety(by);
		System.out.println(bx+" "+by);
	}
    public void save(Saver save)
    {
        save.mapx=x;
        save.mapy=y;
        save.mapReference=url;
        save.maph=height;
        save.mapw=width;
    }
    public void resize(int newHieght, int newWidth)
    {
    	xdistance=newWidth/32;
    	ydistance=newHieght/32;
    }
}
