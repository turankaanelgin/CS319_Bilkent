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
	
	// Returns the ball at the specified position
        public Ball getBall(int index)
        {
          return  balls.get(index);
        }
	
	public void addToBalls( Ball b)
	{
		balls.add( b);
	}
	
	// Add the specified ball to specified index in balls.
        public void addToBalls(int index, Ball b)
	{
		balls.add(index, b);
	}
        
        // Removes the ball at the specified position
        public void removeBall(int index)
        {
            balls.remove(index);
        }
        
        // fromIndex and toIndex are inclusive
        public void removeBalls(int fromIndex, int toIndex)
        {
            for(int i = fromIndex; i <= toIndex; i++)
                balls.remove(i);
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
	
	 // In balls, finds the index of a ball which is hit by the thrown ball(current)
        public int getIndexOfTargetBall(Point p)
        {
            int index = -1;
            
            for (int i = 0; i < balls.size(); i++)
            {
                if ((balls.get(i)).contains(p))
                    index = i;
            }
            return index;
			
        }
        
        // In balls, find the ball which is hit by the thrown ball(current)
        public Ball getTargetBall(Point p)
        {
            Ball  target = new Ball();
            
            for (int i = 0; i < balls.size(); i++)
            {
                if ((balls.get(i)).contains(p))
                    target = balls.get(i);
            }
            return target;
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
