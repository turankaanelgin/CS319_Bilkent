package gameentity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Coin extends ScreenElement
{
	private final int SIZE = 15;
	private int value;
	
	public Coin()
	{
		point = new Point( 930, 900);
		setValue();
	}
	
	public int getValue()
	{
		return value;
	}
	
	private void setValue()
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
		return (p.x >= point.getX() && p.x <= point.getX() + SIZE
				&& p.y >= point.getY() && p.y <= point.getY() + SIZE);	
	}
	
	@Override
	public void draw( Graphics g)
	{
		g.setColor( Color.YELLOW);
		g.fillOval( (int) point.getX(), (int) point.getY(), SIZE, SIZE);
	}
}
