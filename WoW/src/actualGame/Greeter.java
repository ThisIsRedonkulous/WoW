package actualGame;

import java.awt.Image;

import UsefulThings.ImageGetter;
import engine.*;

public class Greeter extends Person
{
	JavaHasNoStructs yup;
	int x;
	int y;
	public Greeter(Image[] tup, Image[] tdown, Image[] tleft, Image[] tright,
			int xcord, int ycord, int dir, Map maper, JavaHasNoStructs structs) {
		super(tup, tdown, tleft, tright, xcord, ycord, dir, maper);
		yup = structs;
		x = xcord;
		y = ycord;
	}
	public Greeter(Image[][] sprites, int tx, int ty, int tdir, Map map, JavaHasNoStructs struct) 
	{
		super(sprites, tx, ty, tdir, map);
		yup = struct;
		x = tx;
		y = ty;
	}
	public Greeter (String name, int xcord, int ycord, Map maper, JavaHasNoStructs struct)
	{
		super(name,xcord,ycord,maper);
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
		yup = struct;
		//speech = new Speech(text);
		x=xcord;
		map=maper;
		y=ycord;
		setDirection(2);
		//numFrames=frames[0].length;
	}
	public void use()
	{
		String[] fightOptions =  {"Say Hi", "Kill them all", "Tell them to take a nap", "Give them sugary candy"};
		GreeterFight fight = new GreeterFight(ImageGetter.getImage("Images/walmart-greeter.jpg"), fightOptions, yup, x, y);
		yup.window.setPanel(fight);
	}

}
