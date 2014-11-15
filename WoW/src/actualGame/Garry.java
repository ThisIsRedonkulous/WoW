package actualGame;

import java.awt.Image;

import engine.Map;
import engine.Person;
import engine.Speech;
import engine.Window;

public class Garry extends talker {

	public Garry(Image g, int xcord, int ycord, Map maper, Speech spock,
			Window w) {
		super(g, xcord, ycord, maper, spock, w);
		// TODO Auto-generated constructor stub
	}
	public void use() {
		// TODO Auto-generated method stub
		window.getWalmart().speech(speech.getTalk());
		window.getWalmart().addToInv(new List(window));
	}


}
