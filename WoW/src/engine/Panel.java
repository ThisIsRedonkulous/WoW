//this i just here for the purpose of having a pointer to the current panel in the Window class
package engine;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public abstract class Panel extends JPanel{
    public KeyListener board;
    public MouseListener mouse;
    public Panel()
    {
    }
    protected void setKeyListener(KeyListener key)
    {
        board=key;
    }
    protected void setMouseListener(MouseListener m)
    {
        mouse=m;
    }
    public abstract void refreshListeners();
	public abstract void update();
}
