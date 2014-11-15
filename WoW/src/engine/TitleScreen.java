package engine;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import UsefulThings.ImageGetter;

public class TitleScreen extends Panel {
	int limiter=0, limit=1;
	JavaHasNoStructs struct;
	static BufferedImage screen = ImageGetter.getImage("images/title.png");
	public TitleScreen(JavaHasNoStructs struct)
	{
		this.struct=struct;
		refreshListeners();
	}
	@Override
	public void refreshListeners() {
		setKeyListener(new KeyBoard());
	}

	@Override
	public void update() {
	if (limiter==limit)
	{
		struct.window.setPanel(new ChapterIntro(struct, "Chapter 1", 60));
	}
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(screen, 0, 0, this);
	}
	private class KeyBoard extends KeyAdapter implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
//			System.out.print("Ben you're a sexy stud");
			limiter=limit;
		}
	}
}
