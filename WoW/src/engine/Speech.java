package engine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Speech {
	String[] talk;
	int cur=0;
	Font font;
	public Speech(String[] stuff)
	{
		//you can set the font to whatever, Helvetica is just ironic
		font = new Font("Andale Mono", 1, 32);
		talk=stuff;
	}
	public boolean next()
	{//advances the speech to the next statement
		cur++;
		if (cur==talk.length)
			return false;
		return true;
	}
	public void paint(Graphics g, int width, int hieght)
	{
		g.setColor(Color.GRAY);
		g.fillRect(0, (int) (hieght*.66), width, hieght/3);
		g.setColor(Color.BLACK);
		int current=0, LPL = width / 18;
		g.setFont(font);
		while (talk[cur].substring(current).length()>LPL)
		{
			//crazy ass math to make it so taaht it text wraps regardless of screen size, awwww yeeeeeah
			g.drawString(talk[cur].substring(current, current+LPL), 10, (int) (hieght*.66+32)+current/LPL*32);
			current+=width/18;
		}
		g.drawString(talk[cur].substring(current), 10, (int) (hieght*.66+32+current/LPL*32));
	}
	public String[] getTalk()
	{
		return talk;
	}
}
