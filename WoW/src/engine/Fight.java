/*Name:	
 *Date:
 *Period:
 *Teacher:
 *Description:
 */
package engine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import UsefulThings.ImageGetter;


public abstract class Fight extends Panel
{
    private static final Image player= ImageGetter.getImage(("images/pacman.png"));
    private Image opponent;
    private String[] option;
    private boolean showMenu=false;
    private JavaHasNoStructs struct;
    private int oppX, pX, curOption, limiter=40;
    Font font, bigfont;
    String speech;
    public void update()
    {
        if(showMenu)
        {
        }
        else
        {
        oppX+=10;
        pX-=10;
        if (oppX==300) showMenu=true;
        }
    }
    public void setSpeech(String bitch)
    {
    	limiter = 0;
    	speech=bitch;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g.drawImage(opponent, oppX, 10, this);
        if(showMenu)
        {
        g.setColor(Color.GRAY);
    	g.fillRect(0, 400, 800, 200);
    	g.setColor(Color.BLACK);

    	if(limiter<40)
    	{
        	g.setFont(bigfont);
    		g.drawString(speech, 25, 470);
    		limiter++;
    	}
    	else{
    	g.setFont(font);
    	g.drawString(option[0], 20, 430);
    	g.drawString(option[1], 410, 430);
    	g.drawString(option[2], 20, 490);
    	g.drawString(option[3], 410, 490);
    	g.setColor(Color.YELLOW);
    	switch(curOption)
    	{
    	case 1: g.drawRect(5, 405, 395, 50);
    	break;
    	case 2: g.drawRect(405, 405, 395, 40);
    	break;
    	case 3: g.drawRect(5, 455, 395, 40);
    	break;
    	case 4: g.drawRect(405, 455, 395, 40);
    	break;
    	}
    	}
        }
    	g.drawImage(player, pX, 300, this);
    }
    public Fight(Image op, String[] options)
    {
    	option=options;
        opponent = op;
        oppX = 0;
        pX = 400;
        curOption=0;
        font = new Font("Sans Serif", 1, 32);
        bigfont = new Font("Sans Serif", 1, 64);
    	setKeyListener(new KeyBoard());
    	setMouseListener(new Mouse());
    }
    @Override
    public void refreshListeners()
    {
        // TODO Auto-generated method stub
        
    }
    public abstract void option1();
    public abstract void option2();
    public abstract void option3();
    public abstract void option4();
    public class KeyBoard implements KeyListener
    {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==e.VK_W)
			{
				if (curOption-2>-1) curOption-=2;
			}
			else if (e.getKeyCode()==e.VK_A)
			{
				if (curOption-1>-1) curOption-=1;
			}
			else if (e.getKeyCode()==e.VK_D)
			{
				if (curOption+1<5) curOption++;
			}
			else if (e.getKeyCode()==e.VK_S)
			{
				if (curOption+2<5) curOption+=2;
			}
			else if (e.getKeyCode()==e.VK_E)
			{
				switch(curOption)
				{
				case 1: option1();
				break;
				case 2: option2();
				break;
				case 3: option3();
				break;
				case 4: option4();
				break;
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
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
			System.out.println(arg0.getX()+ " " + arg0.getY());
			if(arg0.getX()>205)
			{
				if (arg0.getY()>298) option4();
				else option2();
			}
			else
			{
				if(arg0.getY()<298) option1();
				else option3();
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
