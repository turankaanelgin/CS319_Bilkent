package gameentity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Coin extends ScreenElement
{
	private int value;
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue()
	{
		int random1 = (int) (Math.random() * 4);
		
		if (random1 == 0)
		{
			int random2 = (int) (Math.random() * 50) + 51;
			value = random2;
		}
		else
		{
			int random2 = (int) (Math.random() * 50) + 1;
			value = random2;
		}
	}
	
	public boolean contains( Point p)
	{
		return (p.x >= point.x && p.x <= point.x + 5
				&& p.y >= point.y && p.y <= point.y + 5);	
	}
	
	@Override
	public void draw( Graphics g)
	{
		g.setColor( Color.YELLOW);
		g.fillOval( point.x, point.y, 5, 5);
	}
}
