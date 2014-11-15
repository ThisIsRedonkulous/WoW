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


public class Background implements Moveable {
	Image[][] grid;
	BufferedImage reference;
	int x, y, hieght, width;
	public Background(int tx, int ty, int height, int width)
	{
		grid = new Image[height][width];
		this.hieght = height;
		this.width = width;
		x=tx;
		y=ty;
		reference = getImage("images/reference.png");
	}
	public BufferedImage getImage(String name)
	{
		try
		{
			return ImageIO.read(this.getClass().getResource(name));
		}
			catch (IOException ex)
			{
				ex.printStackTrace();
				return null;
			}
	}
	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub
		x+=dx/32;
		y+=dy/32;
	}
	public void loadMap(File page)
	{
		try {
			Scanner scan = new Scanner(page);
			int x = 0;
			int j;
			Image image;
			while (scan.hasNext())
			{
				j=scan.nextInt();
				image = reference.getSubimage(j%10*32, j/10*32, 32, 32);
				grid[x%hieght][x/width]=image;
				x++;
			}
		} catch (FileNotFoundException e) {
			System.out.print("ERROR 404 - FILE NOT FOUND");
		}
	}
	public int hashCode()
	{
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
	public void paint(Graphics g, ImageObserver o)
	{
		for (int x=this.x; x<grid.length && x<this.x+10; x++)
		{
			if (x>0)
			for (int j=this.y; j<grid[0].length && j<this.y+10; j++)
			{
				if (j>0)
				g.drawImage(grid[x][j], x*32, j*32, o);
			}
		}
		//g.drawImage(background, x, y, o);
	}
}
