package gameentity;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class BallSequence extends ScreenElement
{
	private ArrayList<Ball> balls;
	
	public BallSequence()
	{
		balls = new ArrayList<Ball>();
	}
	
	public ArrayList<Ball> getBalls()
	{
		return balls;
	}
	
	public void addToBalls( Ball b)
	{
		balls.add( b);
	}
	
	public void slide()
	{
		Ball first = balls.get( 0);
		
		if (first.point == null)
		{
			first.point = new Point( Maze.X, Maze.Y);
			return;
		}
		
		for (int i = balls.size() - 1; i > 0; i--)
		{
			if (balls.get( i - 1).point != null)
			{
				Point nextPoint = balls.get( i - 1).point;
				balls.get( i).point = new Point( nextPoint.x, nextPoint.y);
			}
		}
		first.moveForward();
	}
	
	public void rewind()
	{
		// to be done
	}
	
	public boolean contains( Point p)
	{
		for (int i = 0; i < balls.size(); i++)
			if (balls.get( i).contains( p))
				return true;
		return false;
	}
	
	public int getSize()
	{
		return balls.size();
	}
	
	@Override
	public void draw( Graphics g)
	{
		for (int i = 0; i < balls.size(); i++)
			if (balls.get( i).point != null)
				balls.get( i).draw( g);
	}
}
