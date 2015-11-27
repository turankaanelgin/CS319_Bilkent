package gameentity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ball extends ScreenElement
{
	public enum BallType
	{
		PLAIN, BOMB, FREEZE, BACK;
	}
	
	public enum Direction
	{
		LEFT, RIGHT, UP, DOWN;
	}
	
	public static int SIZE = 30;
	private Color color;
	private BallType type;
	private Direction dir;
	
	public Ball()
	{
		type = BallType.PLAIN;
		dir = Direction.DOWN;
		setColor();
	}
	
	public Ball( Point point)
	{
		type = BallType.PLAIN;
		dir = Direction.DOWN;
		setColor();
		this.point = point;
	}
	
	public Ball( Color color, BallType type, Direction dir)
	{
		this.color = color;
		this.type = type;
		this.dir = dir;
	}
	
	public void setPoint( Point point)
	{
		this.point = point;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	private void setColor()
	{
		int random = (int) (Math.random() * 5);
		
		switch (random)
		{
			case 0:
				color = Color.RED;
				break;
			case 1:
				color = Color.ORANGE;
				break;
			case 2:
				color = Color.YELLOW;
				break;
			case 3:
				color = Color.GREEN;
				break;
			case 4:
				color = Color.BLUE;
				break;
			case 5:
				color = Color.MAGENTA;
				break;
		}
	}
	
	public BallType getType()
	{
		return type;
	}
	
	public void setType( BallType type)
	{
		this.type = type;
	}
	
	public void moveForward()
	{
		if (dir == Direction.LEFT)
		{
			point.setLocation( point.x - SIZE, point.y);
			if (point.x == Maze.X + Maze.BORDERS[3] && point.y == Maze.Y + Maze.BORDERS[2])
				dir = Direction.DOWN;
			else if (point.x == Maze.X + Maze.BORDERS[7] && point.y == Maze.Y + Maze.BORDERS[6])
				dir = Direction.DOWN;
		}
		else if (dir == Direction.RIGHT)
		{
			point.setLocation( point.x + SIZE, point.y);
			if (point.x == Maze.X + Maze.BORDERS[1] - SIZE && point.y == Maze.Y + Maze.BORDERS[0] - SIZE)
				dir = Direction.UP;
			else if (point.x == Maze.X + Maze.BORDERS[5] - SIZE && point.y == Maze.Y + Maze.BORDERS[4] - SIZE)
				dir = Direction.UP;
		}
		else if (dir == Direction.UP)
		{
			point.setLocation( point.x, point.y - SIZE);
			if (point.x == Maze.X + Maze.BORDERS[1] - SIZE && point.y == Maze.Y + Maze.BORDERS[2])
				dir = Direction.LEFT;
			else if (point.x == Maze.X + Maze.BORDERS[5] - SIZE && point.y == Maze.Y + Maze.BORDERS[6])
				dir = Direction.LEFT;
		}
		else if (dir == Direction.DOWN)
		{
			point.setLocation( point.x, point.y + SIZE);
			if (point.x == Maze.X && point.y == Maze.Y + Maze.BORDERS[0] - SIZE)
				dir = Direction.RIGHT;	
			else if (point.x == Maze.X + Maze.BORDERS[3] && point.y == Maze.Y + Maze.BORDERS[4] - SIZE)
				dir = Direction.RIGHT;
			else if (point.x == Maze.X + Maze.BORDERS[7] && point.y == Maze.Y + Maze.BORDERS[8] - SIZE)
				dir = Direction.RIGHT;
		}
	}
	
	public void moveBackward()
	{
		// to be done
	}
	
	public boolean contains( Point p)
	{
		if (p.x > point.x && p.x < point.x + SIZE)
			if (p.y > point.y && p.y < point.y + SIZE)
				return true;
		return false;
	}
	
	// Check the collision from left part of the ball
        public boolean containsLeftPart(Point p)
        {
                if (p.x > point.x && p.x < point.x + (SIZE / 2))
			if (p.y > point.y && p.y < point.y + SIZE)
				return true;
		return false;
        }
        
        // Check the collision from right part of the ball
        public boolean containsRightPart(Point p)
        {
            	if (p.x >= point.x + (SIZE / 2) && p.x < point.x + SIZE)
			if (p.y > point.y && p.y < point.y + SIZE)
				return true;
		return false;
        }
	
	@Override
	public void draw( Graphics g) 
	{
		g.setColor( color);
		g.fillOval( point.x, point.y, SIZE, SIZE);
		
		if (type == BallType.BOMB)
			g.drawString( "!", point.x + 5, point.y + 5);
		else if (type == BallType.BACK)
			g.drawString( "<", point.x + 5, point.y + 5);
		else if (type == BallType.FREEZE)
			g.drawString( "F", point.x + 5, point.y + 5);
	}
}
