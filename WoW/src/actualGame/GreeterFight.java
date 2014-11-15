package actualGame;

import java.awt.Image;

import UsefulThings.ImageGetter;
import engine.*;

public class GreeterFight extends Fight {

	JavaHasNoStructs structs;
	int xcord;
	int ycord;
	public GreeterFight(Image op, String[] options, JavaHasNoStructs struct, int x, int y) {
		super(op, options);
		structs = struct;
		xcord = x;
		ycord = y;
	}
	@Override
	public void option1() 
	{
		String bitch = "Lol you wish";
		setSpeech(bitch);
	}

	@Override
	public void option2() 
	{
		String bitch = "Lol you wish";
		setSpeech(bitch);
	}

	@Override
	public void option3()
	{
		String bitch = "Lol you wish";
		setSpeech(bitch);
	}

	@Override
	public void option4() 
	{
		String[] bitch = {"Well that worked.", "He seems to have dropped his wallet somewhere in the store."};
		structs.window.getWalmart().speech(bitch);
		System.out.print(xcord);
		System.out.print(ycord);
		Image sp = ImageGetter.getImage("Images/wallet.png");
		Wallet wallet = new Wallet(sp, 0, true);
		String[] lols = {"Watch it pal we protect our own here."};
		Image oldGuy = ImageGetter.getImage("Images/OldGuy/facingForward.png");
		structs.window.getWalmart().remove(xcord+1, ycord);
		Speech his = new Speech(lols);
		talker him = new talker(oldGuy, xcord, ycord, structs.window.getWalmart().getMap(),his, structs.window);
		structs.window.getWalmart().addUsable(10, 70, wallet);
		structs.window.getWalmart().getMap().addUseable(xcord-1, ycord, him);
		structs.window.toWalmart();
	}

}
