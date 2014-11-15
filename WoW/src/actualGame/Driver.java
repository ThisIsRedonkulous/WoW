/*Name:	
 *Date:
 *Period:
 *Teacher:
 *Description:
 */
package actualGame;
import java.awt.Image;

import UsefulThings.ImageGetter;
import client.ServerComms;
import engine.*;

public class Driver
{
    public static void main(String[] args) throws Exception {
        Window game = new Window(null);

        String[] mat1 = {"It is raining outside so you should wait \n until the rain stops to leave Wal-mart."};
        Speech mat2 = new Speech(mat1);
        String[] gary1 = {"Whatever Gramps I'll just get some loser \n to do all my shopping for me" ,"and I'll stay here with my Corvette.", "Hey punk, go do my shopping", "You have recieved a Shopping List!"};
        Speech gary2 = new Speech(gary1);
        Image a = ImageGetter.getImage("Images/mat.png");
        Image gary = ImageGetter.getImage("Images/gary-motherfucking-oak.png");
        Image transparent = ImageGetter.getImage("Images/Transparent.png");
        String[] corvette1 = {"Oh my so many dead cheerleaders. \n And why was a ten year old driving."};
        Speech corvette2 = new Speech(corvette1);
        talker corvette = new talker(transparent, 72,73,game.getWalmart().getMap(),corvette2,game);
        talker corvettea = new talker(transparent, 73,73,game.getWalmart().getMap(),corvette2,game);
        talker corvetteb = new talker(transparent, 74,73,game.getWalmart().getMap(),corvette2,game);
        talker mat = new talker(a,29,75,game.getWalmart().getMap(),mat2,game);
        talker mata = new talker(a,32,75,game.getWalmart().getMap(),mat2,game);
        talker matb = new talker(a,31,75,game.getWalmart().getMap(),mat2,game);
        talker matc = new talker(a,30,75,game.getWalmart().getMap(),mat2,game);
        talker matd = new talker(a,28,75,game.getWalmart().getMap(),mat2,game);
        Garry Gary = new Garry(gary, 70,75, game.getWalmart().getMap(),gary2,game);
        Image oak = ImageGetter.getImage("Images/prof oak/facingLeft.png");
        String[] oak1 = {"Gary I can't believe you crashed \n your corvette and killed all the" ," cheerleaders. How will you possibly \n shop for your presents now."};
        Speech oak2 = new Speech(oak1);
        talker Oak = new talker(oak, 71,75, game.getWalmart().getMap(),oak2,game);
        game.getWalmart().addUsable(70, 74, Gary);
        game.getWalmart().addUsable(71, 74, Oak);
        game.getWalmart().addUsable(29,74, mat);
        game.getWalmart().addUsable(28,74, matd);
        game.getWalmart().addUsable(32,74, mata);
        game.getWalmart().addUsable(31,74, matb);
        game.getWalmart().addUsable(30,74, matc);
        game.getWalmart().addUsable(72,73, corvette);
        game.getWalmart().addUsable(73,73, corvettea);
        game.getWalmart().addUsable(74,73, corvetteb);
        Person service1 = new Person("prof oak", 4, 72, game.getWalmart().getMap());
        Person service2 = new Person("SecondChick", 3, 72, game.getWalmart().getMap());
        String [] talk = new String [4];
        talk[0] = "World of Walmart is a tile-based";
        talk[1] =  "game designed and produced by";
        talk[2] = "Ben Miller, Charles Teese, and Ryan Shea.";
        talk[3] = "This is just the beta version.";
        Speech sprechen = new Speech(talk);
        talker pers = new talker(transparent, 4, 71, game.getWalmart().getMap(), sprechen, game);
        game.getWalmart().addUsable(4, 72, service1);
        game.getWalmart().addUsable(3, 72, service2);
        String[] talk1 = {"If you seriously \n need help in the beta," , "then you should probably \n stop playing now."};
        Speech sprechen1 = new Speech(talk1);
        talker pers1 = new talker(transparent, 3, 71, game.getWalmart().getMap(), sprechen1, game);
        game.getWalmart().addUsable(3, 71, pers1);
        game.getWalmart().addUsable(4, 71,pers);
        	String[] talk6 = new String[1];
        	talk6[0] = "Wow this line isn't moving \n at all today.";
        	Speech sprechen6 = new Speech(talk6);

        		Image oldguy = ImageGetter.getImage("Images/OldGuy/facingRight.png");
        		Image chick = ImageGetter.getImage("Images/chick/facingRight.png");
        		Image Fatguy = ImageGetter.getImage("Images/FatGuy/facingRight.png");
        		Image crazyDude = ImageGetter.getImage("Images/crazyDude/facingRight.png");
        		Image secondChick = ImageGetter.getImage("Images/SecondChick/facingRight.png");
        		talker lineMan1 = new talker(oldguy, 2, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan2 = new talker(chick, 3, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan3 = new talker(Fatguy, 4, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan4 = new talker(crazyDude, 5, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan5 = new talker(oldguy, 6, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan6 = new talker(chick, 7, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan7 = new talker(Fatguy, 8, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan8 = new talker(crazyDude, 9, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan9 = new talker(oldguy, 10, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan10 = new talker(chick, 11, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan11 = new talker(Fatguy, 12, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan12 = new talker(crazyDude, 13, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan13 = new talker(oldguy, 14, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan14 = new talker(chick, 15, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan15 = new talker(Fatguy, 16, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan16 = new talker(crazyDude, 17, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan17 = new talker(oldguy, 18, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan18 = new talker(chick, 19, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan19 = new talker(Fatguy, 20, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan20 = new talker(crazyDude, 21, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan21 = new talker(oldguy, 22, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan22 = new talker(chick, 23, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan23 = new talker(Fatguy, 24, 66, game.getWalmart().getMap(), sprechen6, game);
        		talker lineMan24 = new talker(crazyDude, 1, 66, game.getWalmart().getMap(), sprechen6, game);
        		game.getWalmart().getMap().addUseable(2, 66, lineMan1);
        		game.getWalmart().getMap().addUseable(3, 66, lineMan2);
        		game.getWalmart().getMap().addUseable(4, 66, lineMan3);
        		game.getWalmart().getMap().addUseable(5, 66, lineMan4);
        		game.getWalmart().getMap().addUseable(6, 66, lineMan5);
        		game.getWalmart().getMap().addUseable(7, 66, lineMan6);
        		game.getWalmart().getMap().addUseable(8, 66, lineMan7);
        		game.getWalmart().getMap().addUseable(9, 66, lineMan8);
        		game.getWalmart().getMap().addUseable(10, 66, lineMan9);
        		game.getWalmart().getMap().addUseable(11, 66, lineMan10);
        		game.getWalmart().getMap().addUseable(12, 66, lineMan11);
        		game.getWalmart().getMap().addUseable(13, 66, lineMan12);
        		game.getWalmart().getMap().addUseable(14, 66, lineMan13);
        		game.getWalmart().getMap().addUseable(15, 66, lineMan14);
        		game.getWalmart().getMap().addUseable(16, 66, lineMan15);
        		game.getWalmart().getMap().addUseable(17, 66, lineMan16);
        		game.getWalmart().getMap().addUseable(18, 66, lineMan17);
        		game.getWalmart().getMap().addUseable(19, 66, lineMan18);
        		game.getWalmart().getMap().addUseable(20, 66, lineMan19);
        		game.getWalmart().getMap().addUseable(21, 66, lineMan20);
        		game.getWalmart().getMap().addUseable(22, 66, lineMan21);
        		game.getWalmart().getMap().addUseable(23, 66, lineMan22);
        		game.getWalmart().getMap().addUseable(24, 66, lineMan23);
        		game.getWalmart().getMap().addUseable(1, 66, lineMan24);

        String[] server1 = {"How may I help you today? \n Oh okay walk away jerk."};
        Speech server2 = new Speech(server1);
        talker serverA = new talker(secondChick, 23, 70, game.getWalmart().getMap(), server2, game);
        talker serverB = new talker(secondChick, 19, 70, game.getWalmart().getMap(), server2, game);
        talker serverC = new talker(secondChick, 15, 70, game.getWalmart().getMap(), server2, game);
        talker serverD = new talker(secondChick, 11, 70, game.getWalmart().getMap(), server2, game);
        talker serverE = new talker(secondChick, 7, 70, game.getWalmart().getMap(), server2, game);
        game.getWalmart().getMap().addUseable(22, 69, serverA);
        game.getWalmart().getMap().addUseable(18, 69, serverB);
        game.getWalmart().getMap().addUseable(14, 69, serverC);
        game.getWalmart().getMap().addUseable(10, 69, serverD);
        game.getWalmart().getMap().addUseable(6, 69, serverE);
        //Image oldGuy2 = ImageGetter.getImage("Images/OldGuy/facingForward.png");
        Greeter greet = new Greeter("OldGuy", 40 ,70, game.getWalmart().getMap(), game.getWalmart().getStruct());
        game.getWalmart().getMap().addUseable(40,69,greet);
        game.start();

    }
}
