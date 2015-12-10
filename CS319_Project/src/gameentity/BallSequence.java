package gameentity;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import gameentity.Ball.Direction;

public class BallSequence extends ScreenElement
{
	private ArrayList<Ball> balls;
	
	public BallSequence()
	{
		balls = new ArrayList<Ball>();
	}
	
	/**@post result = self.balls
	 * */
	public ArrayList<Ball> getBalls()
	{
		return balls;
	}
	
	/**@pre self.balls.contains(b) = False
	 * @post self.balls.contains(b) = True
	 * */
	public void addToBalls( Ball b)
	{
		balls.add( b);
	}
	
	/**@pre self.balls.contains(b) = False
	 * @post self.balls.contains(b) = True 
	 * */
	public void addToBalls( Ball b, int index)
	{
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
			if (nextPoint != null)
				balls.get( i).setPoint( new Point( nextPoint.x, nextPoint.y));
			balls.get( i).setDirection( balls.get( i + 1).getDirection());
		}
		balls.remove( index);
	}
	
	
	 /**@post result = self.balls->select(self.index = index)
	 * */
	public Ball get( int index)
	{
		return balls.get( index);
	}
	
	/**@pre self.balls->size > 0
	 * */
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
	
	/**@pre self.balls->size > 0
	 * */
	public void rewind()
	{
		int lastIndex = balls.size() - 1;
		
		for (int i = balls.size() - 1; i >= 0; i--)
		{
			if (balls.get( i).point != null)
			{
				lastIndex = i;
				break;
			}
		}
		
		for (int i = 0; i < lastIndex; i++)
		{
			if (balls.get( i + 1).point != null)
			{
				Point prevPoint = balls.get( i + 1).point;
				balls.get( i).setPoint( new Point( prevPoint.x, prevPoint.y));
				balls.get( i).setDirection( balls.get( i + 1).getDirection());
			}
		}
		
		Ball last = balls.get( lastIndex);
		reverseDirection( last);
		
		if (lastIndex == balls.size() - 1)
			last.moveBackward();
		else
			last.setPoint( null);
		
		reverseDirection( last);
		
	}
	
	private void reverseDirection( Ball last)
	{
		if (last.getDirection() == Direction.DOWN)
			last.setDirection( Direction.UP);
		else if (last.getDirection() == Direction.UP)
			last.setDirection( Direction.DOWN);
		else if (last.getDirection() == Direction.LEFT)
			last.setDirection( Direction.RIGHT);
		else if (last.getDirection() == Direction.RIGHT)
			last.setDirection( Direction.LEFT);
	}
	
	/**@pre self.balls->size > 0
	 * @post result = self.balls->select(contains(p)).getIndex()
	 * */
	public int contains( Point p)
	{
		for (int i = 0; i < balls.size(); i++)
			if (balls.get( i).getPoint() != null)
				if (balls.get( i).contains( p))
					return i;
		return -1;
	}
	
	/**@post result = self.balls->size
	 * */
	public int getSize()
	{
		return balls.size();
	}
	
	/**@pre !(self.balls.point = null)
	 * */
	@Override
	public void draw( Graphics g)
	{
		for (int i = 0; i < balls.size(); i++)
			if (balls.get( i).point != null)
				balls.get( i).draw( g);
	}
}
