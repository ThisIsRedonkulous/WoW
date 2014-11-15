/*Name:	
 *Date:
 *Period:
 *Teacher:
 *Description:
 */
package actualGame;
import java.awt.Image;

import engine.*;

public class EpicHero extends Player
{
    String name = "";
    int killcount;
    public EpicHero(Image[][] tFrames, int tx, int ty, int direction, int health, Map maper, String Nname) {
            super(tFrames,tx,ty,direction,health,maper);
            name = Nname;
            killcount = 0;
        }
    public void setKillCount(){
        killcount++;
    }
       
}
