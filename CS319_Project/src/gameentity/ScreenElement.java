package gameentity;
import java.awt.Graphics;
import java.awt.Point;

public abstract class ScreenElement 
{
	protected Point point;
	
	public Point getPoint()
	{
		return point;
	}
	
	public abstract void draw( Graphics g);
}
