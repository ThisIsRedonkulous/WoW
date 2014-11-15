package engine;
import java.awt.Image;

import UsefulThings.ImageGetter;


public class HealthPerson extends Person implements Moveable {
	private int health;
	public HealthPerson(Image[][] tFrames, int tx, int ty, int direction, int health, Map maper) {
		super(tFrames, tx, ty, direction, maper);
		this.health = health;
		// TODO Auto-generated constructor stub
	}
	public HealthPerson(Image[] tup, Image[] tdown, Image[] tleft,
			Image[] tright, int xcord, int ycord, int dir, Map maper) {
		super(tup, tdown, tleft, tright, xcord, ycord, dir, maper);
		// TODO Auto-generated constructor stub
	}
	   public HealthPerson (String name, int xcord, int ycord, Map maper, Speech spock)
	    {
	       super(name,xcord,ycord,maper,spock);
	    }
	public void hurt(int pain)
	{
		health-=pain;
		System.out.print(health);
	}


}
