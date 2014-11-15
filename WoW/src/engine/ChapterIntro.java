package engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ChapterIntro extends Panel {
	int limiter=0, limit;
	JavaHasNoStructs struct;
	String string;
	Font font;
	public ChapterIntro(JavaHasNoStructs st, String s, int limit)
	{
		struct=st;
		string=s;
//		this.limit=limit;
		refreshListeners();
		font = new Font("Sans Serif", 1, 48);
	}
	@Override
	public void refreshListeners() {
		setKeyListener(new KeyBoard());
	}

	@Override
	public void update() {
//		if (limiter==limit)
//		{
//			struct.window.toWalmart();
//		}
//		limiter++;
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(string, getWidth()/2-32, getHeight()/2-16);
	}
	private class KeyBoard extends KeyAdapter implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			//System.out.print("Ben you're a sexy stud");
			struct.window.toWalmart();
		}
	}
}
