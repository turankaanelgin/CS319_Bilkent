package gameentity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Hole extends ScreenElement
{
	public Hole()
	{
		point = new Point( Maze.X + Maze.BORDERS[9] - 10, Maze.Y + Maze.BORDERS[9] - 10 - Ball.SIZE);
	}
	
	public boolean containsBall( BallSequence sequence)
	{
		ArrayList<Ball> balls = sequence.getBalls();
		for (int i = 0; i < balls.size(); i++)
		{
			Ball b = balls.get( i);
			if (b.point.x >= point.x && b.point.x <= point.x + 30
				&& b.point.y >= point.y && b.point.y <= point.y + 30)
				return true;
		}
		return false;
	}
	
	@Override
	public void draw( Graphics g)
	{
		g.setColor( Color.BLACK);
		g.fillOval( point.x, point.y, 50, 50);
	}
}
