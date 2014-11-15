package actualGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.JavaHasNoStructs;
import engine.Panel;
import engine.Window;

public class ListPanel extends Panel {
	Font font;
	Window window;
	String string = "1. Red Rider BB Gun", string2="2. Fishing Rod\n", string3 = "3. Half Life 2: Episode 3";
	public ListPanel(Window st)
	{
		refreshListeners();
		window=st;
		font = new Font("Sans Serif", 1, 48);
	}
	@Override
	public void refreshListeners() {
		setKeyListener(new KeyBoard());

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(string, 40, 40);
		g.drawString(string2, 40,80);
		g.drawString(string3, 40, 120);
	}
	private class KeyBoard extends KeyAdapter implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			System.out.print("Ben you're a sexy stud");
			window.toWalmart();
		}
	}
}
