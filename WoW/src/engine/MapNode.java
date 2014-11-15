//This is probably going to end up being mified quite a bit as i mature the engine,
//but I'll try and keep things working the same as far as you are concerned so any backcode wont get bropken
package engine;

import java.awt.Image;
import java.awt.image.BufferedImage;


public class MapNode
{
	//automatically a passable tile unless otherwise told, aka no collision
private boolean passable=true;
public int x, y;
private int g, h ,f;
public Image tile;
private Useable item;
private boolean hasItem=false;
public MapNode(Image tile, int tx, int ty)
{
    this.tile=tile;
    g = h= f= -1;
    x=tx;
    y=ty;
}
public boolean setUseable(Useable titem)
{
	//true if the useable is added, false if not
	if (item==null)
	{
    item=titem;
    hasItem=true;
    //makes sure you cant walk on top of a person, becuase that is just silly
    if (item instanceof Person) passable=false;
    System.out.print("added "+hasItem);
    return true;
	}
	return false;
}
public Useable peekItem()
{ //gets the tiem without removieng it
    return item;
}
public Useable getItem()
{ //gets the item and removes it
    Useable titem=item;
    item=null;
    hasItem=false;
    return titem;
}
public boolean hasItem()
{
	return hasItem;
}
public void setTile(Image ttile)
{
    tile=ttile;
}
public void setPassable(boolean b)
{
	passable=b;
}
public boolean isPassable()
{
	return passable;
}
//dont worry about these, never use them
public void setG(int tg)
{
    g=tg+1;
    setF();
}
public int getG()
{
    return g;
}
public int getF()
{
    return f;
}
public static int heuristic(MapNode begin, MapNode end)
{
    return (int)(Math.sqrt(Math.pow(begin.y, 2)+Math.pow(end.y, 2))+Math.sqrt(Math.pow(begin.x, 2)+Math.pow(end.x, 2)));
}
public void setH(MapNode end)
{
    h=heuristic(this, end);
    setF();
}
private void setF()
{
    f=h+g;
}
}
