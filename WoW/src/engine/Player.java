package engine;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import UsefulThings.ImageGetter;


public class Player extends HealthPerson {
	public int x, y;
	public Player(Image[][] tFrames, int tx, int ty, int direction, int health, Map maper) {
		super(tFrames, tx, ty, direction, health, maper);
		x=tx;
		y=ty;
		// TODO Auto-generated constructor stub
	}
	public Player(Image[] tup, Image[] tdown, Image[] tleft, Image[] tright, int xcord, int ycord, int dir, Map maper) {
		super(tup, tdown, tleft, tright, xcord, ycord, dir, maper);
	}
	   public Player(String name, int xcord, int ycord, Map maper, Speech spock)
	    {
	       super(name,xcord,ycord,maper,spock);
	        x = xcord;
	        y = ycord;
	    }
	public boolean walk(int direction, int distance)
	{
//		okay so it goes down like this. You tell the player to walk
//		the next frame will be the seond one in the array of images
//		then the next frame the player will return to the first image in the array
		if (this.getDirection()!=direction)
		{
		this.setDirection(direction);
		return false;
		}
		else if (isMoving() || !canWalk(direction, distance)) return false;
		else
		{
		setMoving(true);
		setCurrentFrame(1); 
		switch(direction)
		{
		case 0: y-=1;
		break;
		case 1: x+=1;
		break;
		case 2: y+=1;
		break;
		case 3: x-=1;
		break;
		default: System.out.print("What are you doing");
		}
		return true;
		}
	}
	protected boolean canWalk(int direction2, int distance2) {
		int tx = x, ty=y;
		switch(direction2)
		{
		case 0: ty-=1;
		break;
		case 1: tx+=1;
		break;
		case 2: ty+=1;
		break;
		case 3: tx-=1;
		}
		return map.passable(tx,ty);
	}
	public void paint(Graphics g, ImageObserver o)
	{
		if (isMoving() && getCurrentFrame()!=frames[getDirection()].length)
		{
			switch(direction)
			{
			case 0: y-=distance;
			break;
			case 1: x+=distance;
			break;
			case 2: y+=distance;
			break;
			case 3: x-=distance;
			}
			g.drawImage(frames[getDirection()][getCurrentFrame()], 352, 288, o);
			setCurrentFrame(getCurrentFrame() + 1);
			return;
		}
		else if (isMoving()) setMoving(false);
			g.drawImage(frames[getDirection()][0], 352, 288, o);
	}

}
