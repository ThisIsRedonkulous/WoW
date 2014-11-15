//crap you can move
//aka if it is going to be rendered you should probably be implementing this somewhere
package engine;
import java.awt.Graphics;
import java.awt.image.ImageObserver;


public interface Moveable {
	void move(int dx, int dy);
	void paint(Graphics g, ImageObserver o);
	//these are for checking if the object is out of the screen, so there is no point rendering it, among other uses
	int getx();
	int gety();
}
