//Every item needs a more specific class to be like "This Item is this thing"
package engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.image.ImageObserver;
//
//

public class Food extends Item {

	Player wurp;
	public Food(Image sprite, int id, int x, int y, Player player) {
		super(sprite, id, true);
		wurp=player;
		super.setx(x);
		super.sety(y);
	}

	@Override
	//if they eat it then they get slapped for being dumb
	//this is ferret food, have you no standards?
	//you are shopping at walmart...
	public void use() {
		wurp.hurt(4);
		return;
	}


	@Override
	public void paint(Graphics g, ImageObserver o)
	{
		g.drawImage(super.getImage(), super.getx()*32+16, super.gety()*32+16, o);
	}



}
