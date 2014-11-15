package engine;

public class StuffNode {
	private Useable obj;
	private StuffNode nw, sw, se, ne;
	private int x, y, xlength, ylength;
	public StuffNode(int x, int y, int xlength, int ylength)
	{
		this.x = x;
		this.y = y;
		this.xlength = xlength;
		this.ylength = ylength;
		if (xlength>63 && ylength>63)
		{
			nw = new StuffNode(x, y, xlength/2, ylength/2);
			sw = new StuffNode(x, y+ylength/2, xlength/2, ylength/2);
			se = new StuffNode(x+xlength/2, y+ylength/2, xlength/2, ylength/2);
			ne = new StuffNode(x+xlength/2, y, xlength/2, ylength/2);
		}
		else if (xlength>63 && ylength < 64)
		{
			nw = new StuffNode(x, y, xlength/2, ylength);
			ne = new StuffNode(x+xlength/2, y, xlength/2, ylength);
		}
		else if (xlength<64 && ylength>63)
		{
			nw = new StuffNode(x, y, xlength, ylength/2);
			sw = new StuffNode(x, y + ylength/2, xlength, ylength);
		}
	}
	public void setObj(Useable turboDerp)
	{
		obj=turboDerp;
	}
	public boolean contains(int x, int y)
	{
		if((x-this.x>0 && x-this.x<xlength) && (y-this.y>0 && y-this.y<ylength)) return true;
		return false;
	}
	public Useable getThing(int x, int y)
	{
		if (nw==null) 
			{
			if (obj!=null)
			System.out.print("got");
			Useable derp;
			derp = obj;
			obj=null;
			return derp;
			}
		else
		{
			if (nw.contains(x, y)) return nw.getThing(x, y);
			else if (ne!=null && ne.contains(x, y)) return ne.getThing(x, y);
			else if (sw!=null && sw.contains(x, y)) return sw.getThing(x, y);
			else if (se!=null && se.contains(x, y)) return se.getThing(x, y);
		}
		return null;
	}
	public void add(int x, int y, Useable use)
	{
		if (nw==null)
			{
			obj=use;
			System.out.print("added"+ this.x+ " " + this.y);
			}
		else
		{
			if (nw.contains(x, y)) nw.add(x, y, use);
			else if (sw.contains(x, y)) sw.add(x, y, use);
			else if (se.contains(x, y)) se.add(x, y, use);
			else if (ne.contains(x, y)) ne.add(x, y, use);
		}
	}
}
