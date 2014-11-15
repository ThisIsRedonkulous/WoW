//This is no longer used, but it s a quadtree. Bask in the glory
package engine;

public class Stuff {
	private StuffNode root;
	public Stuff(int xlength, int ylength)
	{
		root = new StuffNode(0, 0, xlength, ylength);
	}
	public void addUseable(int x, int y, Useable item)
	{
		root.add(x, y, item);
	}
	public Useable getThing(int x, int y)
	{
		return root.getThing(x, y);
	}
}
