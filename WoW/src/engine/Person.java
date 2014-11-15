package engine;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
/*A few things to consider:
 * If there are x frames for a person walking one step, it will take x frame updates for him to go that distance
 * everything has the same framerate so if someone needs to move faster, the images should reflect that person hauling ass
 */

import UsefulThings.ImageGetter;

public class Person implements Moveable, Useable{
	//as of right now these should only have two images, a standing around one, and a walking one, but I'll probably
	//implement support for moving with as many frames as people want in the future
	protected Map map;
	protected Image[][] frames;
	private int x, y;
	private int currentFrame=0;
	private boolean isMoving=false;
	//0=up, 1=right, 2=down, 3=left This makes no sense so if things are dicking up, this is why, that is how it rolls. dwi
	protected int direction;
	private int numFrames;
	protected double distance=0;
	private Speech speech;
	public Person (String name, int xcord, int ycord, Map maper, Speech spock)
	{
		frames = new Image[4][3];
		frames[0][0]= ImageGetter.getImage("Images/"+name+"/facingBack.png");
		frames[0][1]= ImageGetter.getImage("Images/"+name+"/steppingBackRight.png");
		frames[0][2]= ImageGetter.getImage("Images/"+name+"/steppingBackLeft.png");
		frames[1][0]= ImageGetter.getImage("Images/"+name+"/facingRight.png");
		frames[1][1]= ImageGetter.getImage("Images/"+name+"/steppingRightRight.png");
		frames[1][2]= ImageGetter.getImage("Images/"+name+"/steppingRightLeft.png");
		frames[2][0]= ImageGetter.getImage("Images/"+name+"/facingForward.png");
		frames[2][1]= ImageGetter.getImage("Images/"+name+"/steppingForwardLeft.png");
		frames[2][2]= ImageGetter.getImage("Images/"+name+"/steppingForwardRight.png");
		frames[3][0]= ImageGetter.getImage("Images/"+name+"/facingLeft.png");
		frames[3][1]= ImageGetter.getImage("Images/"+name+"/steppingLeftLeft.png");
		frames[3][2]= ImageGetter.getImage("Images/"+name+"/steppingLeftRight.png");
		speech = spock;
		x=xcord;
		map=maper;
		y=ycord;
		setDirection(0);
		numFrames=frames[0].length;
	}
	public Person (String name, int xcord, int ycord, Map maper)
	{
		frames = new Image[4][3];
		frames[0][0]= ImageGetter.getImage("Images/"+name+"/facingBack.png");
		frames[0][1]= ImageGetter.getImage("Images/"+name+"/steppingBackRight.png");
		frames[0][2]= ImageGetter.getImage("Images/"+name+"/steppingBackLeft.png");
		frames[1][0]= ImageGetter.getImage("Images/"+name+"/facingRight.png");
		frames[1][1]= ImageGetter.getImage("Images/"+name+"/steppingRightRight.png");
		frames[1][2]= ImageGetter.getImage("Images/"+name+"/steppingRightLeft.png");
		frames[2][0]= ImageGetter.getImage("Images/"+name+"/facingForward.png");
		frames[2][1]= ImageGetter.getImage("Images/"+name+"/steppingForwardLeft.png");
		frames[2][2]= ImageGetter.getImage("Images/"+name+"/steppingForwardRight.png");
		frames[3][0]= ImageGetter.getImage("Images/"+name+"/facingLeft.png");
		frames[3][1]= ImageGetter.getImage("Images/"+name+"/steppingLeftLeft.png");
		frames[3][2]= ImageGetter.getImage("Images/"+name+"/steppingLeftRight.png");
		String[] text = {" "};
		speech = new Speech(text);
		x=xcord;
		map=maper;
		y=ycord;
		setDirection(0);
		numFrames=frames[0].length;
	}
	public Person (Image g, int xcord, int ycord, Map maper, Speech spock)
	{
		frames = new Image[4][3];
		frames[0][0]= g;
		frames[0][1]= g;
		frames[0][2]= g;
		frames[1][0]= g;
		frames[1][1]= g;
		frames[1][2]= g;
		frames[2][0]= g;
		frames[2][1]= g;
		frames[2][2]= g;
		frames[3][0]= g;
		frames[3][1]= g;
		frames[3][2]= g;
		speech = spock;
		x=xcord;
		map=maper;
		y=ycord;
		setDirection(0);
		numFrames=frames[0].length;
	}
	//if this Person doesn't move then you can just have one image in each array
	//or one image in the one applicable array, and pass in null for the rest
	public Person (Image[] tup, Image[] tdown, Image[] tleft, Image[] tright, int xcord, int ycord, int dir, Map maper)
	// TOO MANY PARAMETERS AAAAGGGGGHHHH
	{
		map=maper;
		frames = new Image[4][tup.length];
		frames[0]=tup;
		frames[2]=tdown;
		frames[3]=tleft;
		frames[1]=tright;
		//for now speech is just a black whitepace, but you can change the construtors to accept a speech, or use setSpeech
		String[] talk = {" "};
		speech = new Speech(talk);
		x=xcord;
		y=ycord;
		setDirection(dir);
		numFrames=frames[0].length;
	}
	public Person (Image[][] tFrames, int tx, int ty, int direction, Map maper)
	{
		map=maper;
		frames=tFrames;
		x=tx;
		y=ty;
		this.setDirection(direction);
		numFrames=frames[0].length;
	}
	public void move(int dx, int dy)
	{
		x+=dx;
		y+=dy;
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	public boolean walk(int direction, int distance)
	{
//		okay so it goes down like this. You tell the player to walk
//		the next frame will be the second one in the array of images
//		the next frame the player will move one half the distance specified
//		then the next frame the player will return to the first image in the array
//		then move the last half
		if (isMoving() || !canWalk(direction, distance)) return false;
		else
		{
		setMoving(true);
		if(currentFrame<frames[0].length)
		currentFrame++;
		else currentFrame=0;
		this.direction = direction;
		this.distance=distance/numFrames;
		return true;
		}
	}
	protected boolean canWalk(int direction2, int distance2) {
		int tx = x, ty=y;
		switch(direction)
		{
		case 0: ty-=distance;
		break;
		case 1: tx+=distance;
		break;
		case 2: ty+=distance;
		break;
		case 3: tx-=distance;
		}
		return map.passable(x,y);
	}
	public void turn(int newdir)
	{
		setDirection(newdir);
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
			g.drawImage(frames[getDirection()][getCurrentFrame()], x*32, y*32, o);
			setCurrentFrame(getCurrentFrame() + 1);
			return;
		}
		else if (isMoving()) setMoving(false);
			g.drawImage(frames[getDirection()][0], x*32, y*32, o);
	}
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	public boolean isMoving() {
		return isMoving;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getDirection() {
		return direction;
	}
	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	public int getCurrentFrame() {
		return currentFrame;
	}
	public void setSpeech(Speech sp)
	{
		speech=sp;
	}
	public Speech talk()
	{
		return speech;
	}
	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Image getSprite() {
		// TODO Auto-generated method stub
		return frames[direction][currentFrame];
	}
}
