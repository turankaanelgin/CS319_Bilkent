package gameentity;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import gameentity.Ball.BallType;

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
	
	public void addToBalls( Ball b, int index)
	{
		b.setType( BallType.PLAIN);
		balls.add( index, b);
		Point nextPoint = balls.get( index - 1).point;
		b.setPoint( new Point( nextPoint.x, nextPoint.y));
		b.setDirection( balls.get( index - 1).getDirection());
		
		for (int i = index - 1; i > 0 ; i--)
		{
			nextPoint = balls.get( i - 1).point;
			balls.get( i).setPoint( new Point( nextPoint.x, nextPoint.y));
			balls.get( i).setDirection( balls.get( i - 1).getDirection());
		}
		balls.get( 0).moveForward();
	}
	
	public void removeBall( int index)
	{	
		for (int i = 0; i < index; i++)
		{
			Point nextPoint = balls.get( i + 1).point;
			balls.get( i).setPoint( new Point( nextPoint.x, nextPoint.y));
			balls.get( i).setDirection( balls.get( i + 1).getDirection());
		}
		balls.remove( index);
	}
	
	public Ball get( int index)
	{
		return balls.get( index);
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
				balls.get( i).setPoint( new Point( nextPoint.x, nextPoint.y));
				balls.get( i).setDirection( balls.get( i - 1).getDirection());
			}
		}
		first.moveForward();
	}
	
	public void rewind()
	{
		Ball last = balls.get(balls.size() - 1);
		
		if(last.point == null)
		{
			last.point = new Point(Maze.X, Maze.Y);
			return;
		}
		
		
		for(int i = 0; i < balls.size() - 1; i++)
		{
			if(balls.get(i + 1).point != null)
			{
				Point nextPoint = balls.get(i + 1).point;
				balls.get(i).setPoint( new Point(nextPoint.x, nextPoint.y));
				balls.get(i).setDirection( balls.get(i + 1).getDirection());
			}
		}
		last.moveBackward();
	}
	
	public void explode(int index)
	{
			removeBall(index);
	
	public int contains( Point p)
	{
		for (int i = 0; i < balls.size(); i++)
			if (balls.get( i).getPoint() != null)
				if (balls.get( i).contains( p))
					return i;
		return -1;
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
