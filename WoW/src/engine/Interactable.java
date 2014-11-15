//I am pretty sure I don't use this anywhere but ill just leave it here is case maybe i do
package engine;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;


public class Interactable implements Moveable {
	private int x, y;
	private Image sprite;
	private Item item;
	public Interactable(int tx, int ty, Image tsprite, Item titem)
	{
		x=tx;
		y=ty;
		sprite = tsprite;
		item=titem;
	}
	@Override
	public void move(int dx, int dy) {
		x+=dx;
		y+=dy;
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
	public Item getItem()
	{
		return item;
	}
	public void paint(Graphics g, ImageObserver o)
	{
		g.drawImage(sprite, x, y, o);
	}
}
