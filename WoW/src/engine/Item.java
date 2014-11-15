package engine;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;


public abstract class Item implements Useable{
	private Image sprite;
	int x, y;
	//id that is unique tot he item type, aka for each item you make a new class for, give it a different id
	private int id;
	private boolean dropable;
	public Item(Image sprite, int id, boolean dropable){
		this.sprite=sprite;
		this.dropable = dropable;
		this.id=id;
	}
	public int getId()
	{
		return id;
	}
	public Image getImage()
	{
		return sprite;
	}
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
	public void setx(int x) {
		// TODO Auto-generated method stub
		this.x=x;
	}

	public void sety(int y) {
		// TODO Auto-generated method stub
		this.y=y;
	}
	public void setDropable(boolean b)
	{
		dropable=b;
	}
	//only reason this is abstract is so each item can have its own implementation of use()
	public abstract void use();
	public boolean canDrop()
	{
		return dropable;
	}
	public int hashCode()
	{
		return sprite.hashCode()*id;
	}
	public boolean equals(Object obj)
	{
		if (((Item) obj).getId() == this.id) return true;
		else return false;
	}
	public Image getSprite()
	{
		return sprite;
	}
}
