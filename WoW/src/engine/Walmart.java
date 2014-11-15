package engine;
/* The drawing panel for Walmart
 * 
 */



import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.HashSet;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import UsefulThings.ImageGetter;


public class Walmart extends Panel{
	//all the crap that needs rendering besides the player.
	//Litterally, put all of it here or things will start getting really wack
Player player;
Map map;
int dy=0, dx=0;
boolean killOnNext=true, showInventory=false, showSpeech=false, moveable=true;
Speech speech;
JavaHasNoStructs struct;
	public Walmart(JavaHasNoStructs s) throws Exception
	{
	    refreshListeners();
		map = new Map(19, 65, 76, 100);
		map.loadMap(new File("data/defaultMap.dat"), null);
		/*Image[][] sprites= new Image[4][3];
		sprites[0][0]= ImageGetter.getImage("Images/facingBack.png");
		sprites[0][1]= ImageGetter.getImage("Images/steppingBackRight.png");
		sprites[0][2]= ImageGetter.getImage("Images/facingRight.png");
		sprites[1][0]= ImageGetter.getImage("Images/facingRight.png");
		sprites[1][1]= ImageGetter.getImage("Images/facingRight.png");
		sprites[1][2]= ImageGetter.getImage("Images/steppingRight.png");
		sprites[2][0]= ImageGetter.getImage("Images/facingForward.png");
		sprites[2][1]= ImageGetter.getImage("Images/steppingForwardLeft.png");
		sprites[2][2]= ImageGetter.getImage("Images/facingRight.png");
		sprites[3][0]= ImageGetter.getImage("Images/facingLeft.png");
		sprites[3][1]= ImageGetter.getImage("Images/steppingLeft.png");
		sprites[3][2]= ImageGetter.getImage("Images/facingRight.png");
		player = new Player(sprites, 16, 9, 1, 10, map);*/
		String st = "SecondChick";
		struct=s;
		String[] stl = {"lol"};
		Speech stall = new Speech(stl);
		player = new Player(st, 30, 74, map, stall);
		addUsable(1, 1, new Food(ImageGetter.getImage("images/shoot lol.png"), 1, 1, 1, player));
	}
	public void save(Saver save)
	{
		map.save(save);
		save.playerx=player.getx();
		save.playery=player.gety();
	}
	public boolean addUsable(int x, int y, Useable item)
	{

		//objects.add(item);
		return map.addUseable(x, y, item);
	}
	public void randomlyAddUsable(Item titem) {
		map.randomlyAddUseable(titem);
		//objects.add(titem);
	}
	public Useable remove(int x, int y)
	{
		return map.getItem(x, y);
	}
	public void refreshListeners()
	{
	       setKeyListener(new KeyBoard());
	        setMouseListener(new Mouse());
	}
	public void addToInv(Item titem)
	{
		struct.inventory.add(titem);
	}
//	public BufferedImage getImage(String name)
//	{
//		try
//		{
//			BufferedImage image;
//			URL url = null;
//				url = this.getClass().getResource(name);
//				image = ImageIO.read(url);
//				return image;
//		}
//			catch (IOException ex)
//			{
//				ex.printStackTrace();
//				return null;
//			}
//	}
	public void update()
	{
		if (dy != 0 || dx != 0)
		{			
			if(killOnNext) 
				{
				map.move(dx, dy);
				dy = dx =0;
				moveable=true;
				}
			killOnNext=true;
//			offsetx-=dx;
//			offsety-=dy;
		}
	}
	public void speech(String[] text)
	{
		speech= new Speech(text);
		showSpeech=true;
		moveable=false;
	}
	public void speech (Speech speech2)
	{
		speech = speech2;
		showSpeech=true;
		moveable=false;
	}
    public void resize(int newHieght, int newWidth)
    {
    	map.resize(newHieght, newWidth);
    }
private class KeyBoard extends KeyAdapter implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			//all these VK_whateevers are just if the player presses this, what do
			// the 32 for walk does literally nothing. It is just there because it is needed to override the walk method in person
			if (e.getKeyCode()==e.VK_W)
			{
				if (moveable && killOnNext)
				{
					if (player.walk(0, 32))
					{
				dy=1; 
				killOnNext=false;
				moveable=false;
				}
				}
			}
			else if (e.getKeyCode()==e.VK_A)
			{
				if (moveable && killOnNext)
				{
					if (player.walk(3, 32))
					{
				dx=1;
				killOnNext=false;
				moveable=false;
				}
				}
			}
			else if (e.getKeyCode()==e.VK_D)
			{

				if(moveable && killOnNext)
				{
					if (player.walk(1, 32))
					{
				dx=-1;
				killOnNext=false;
				moveable=false;
				}
				}
			}
			else if (e.getKeyCode()==e.VK_S)
			{
				if(moveable && killOnNext)
				{
					if (player.walk(2, 32))
					{
				dy=-1;
				killOnNext=false;
				moveable=false;
				}
				}
			}
			else if (e.getKeyCode()==e.VK_E)
			{
				if(moveable)
				{
					//interact with something in front of you;
					double dir = player.getDirection()*Math.PI/2;
//				System.out.println(player.x+Math.sin(dir)+" "+(player.y-Math.cos(dir)));
				//essentially this is just being like if it is a thing, put it in the inventory
				//if it is someone, talk to them
				Useable titem = map.peekItem((int) (player.x+Math.sin(dir)), (int) (player.y-Math.cos(dir)));
				if (titem instanceof Item)
				{
				map.getItem((int) (player.x+Math.sin(dir)), (int) (player.y-Math.cos(dir)));
				struct.inventory.add((Item) titem);
				}
				else if (titem instanceof Person)
				{
					titem.use();
				}
				}
				else if (showSpeech)
				{
					showSpeech=speech.next();
					if (!showSpeech) moveable = true;
				}
			}
			else if (e.getKeyCode() == e.VK_I)
			{
				if (!showSpeech)
				{
				showInventory=!showInventory;
				moveable=!moveable;
				}
			} 
			else if (e.getKeyCode() == e.VK_Z)
			{
				struct.window.wondow(600, 800);
//				String[] taco = new String[2];
//				taco[0]="lololol";
//				taco[1] = "herp";
//				Speech herp = new Speech(taco);
//				showSpeech=true;
//				speech=herp;
//				moveable=false;
			}
			else if (e.getKeyCode() == e.VK_F)
			{
				//just testing switching panels
				struct.window.fullscreen();
			}
			else if (e.getKeyCode()==e.VK_H)
			{
				DatFerret f = new DatFerret(struct);
				struct.window.setPanel(f);
			}
			else if (e.getKeyCode()==e.VK_J)
			{
				struct.window.setPanel(new ChapterIntro(struct, "Chapter 1", 100));
			}
		}
	}
	private class Mouse implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			if (showInventory)
			{
//			    System.out.println(arg0.getX()/200 + (arg0.getY()/200));
			struct.inventory.use(arg0.getX()/200, arg0.getY()/200);
			}
		
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if (showInventory)
			{
				struct.inventory.paint(g, this);
				return;
			}
			Graphics2D g2d = (Graphics2D) g;
			map.paint(g2d, this);
			player.paint(g2d, this);
			if (showSpeech) speech.paint(g2d, this.getWidth(), this.getHeight());
			}
		public Dimension getPreferredSize()
		{
			return struct.window.getPreferredSize();

		}
		public Map getMap()
		{
		    return map;
		}
		public JavaHasNoStructs getStruct()
		{
			return struct;
		}
}
