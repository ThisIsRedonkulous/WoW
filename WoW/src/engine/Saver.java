package engine;

import java.io.Serializable;

public class Saver implements Serializable {
	static final long serialVersionUID = 1L;
	public int tcurPanel;
	public int[] items;
	public int mapx, mapy , maph, mapw;
	public String mapReference;
	public int playerx, playery;
}
