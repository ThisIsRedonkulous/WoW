package actualGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import UsefulThings.ImageGetter;

import engine.Item;
import engine.Window;

public class List extends Item {
	Window wondow;
public List(Window window) {
		super(ImageGetter.getImage("images/list.png"), 2, false);
		wondow=window;
	}
	@Override
	public void use() {
		wondow.setPanel(new ListPanel(wondow));

	}
	@Override
	public void paint(Graphics g, ImageObserver o) {
		// TODO Auto-generated method stub
		
	}

}
