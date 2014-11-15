package actualGame;

import java.awt.Image;

import engine.*;

public class talker extends Person {

	protected Map map;
	protected Image[][] frames;
	private int x, y;
	private int currentFrame=0;
	private boolean isMoving=false;
	//0=up, 1=right, 2=down, 3=left This makes no sense so if things are dicking up, this is why, that is how it rolls. dwi
	protected int direction;
	private int numFrames;
	protected double distance=0;
	Speech speech;
	Window window;
	public talker(Image g, int xcord, int ycord, Map maper, Speech spock, Window w) {
		super(g, xcord, ycord, maper, spock);
		window = w;
		speech = spock;
		x=xcord;
		map=maper;
		y=ycord;
		setDirection(0);
		//numFrames=frames[0].length;
		// TODO Auto-generated constructor stub
	}
	public void use() {
		// TODO Auto-generated method stub
		window.getWalmart().speech(speech.getTalk());
	}

}
