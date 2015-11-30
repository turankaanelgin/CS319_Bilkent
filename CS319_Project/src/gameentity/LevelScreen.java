package gameentity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class LevelScreen extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Maze maze;
	private Hole hole;
	private BallSequence sequence;
	private BallShooter shooter;
	private AffineTransform at;
	
	public LevelScreen()
	{
		setBackground( Color.LIGHT_GRAY);
		setPreferredSize( new Dimension( 1000, 1000));
	}
	
	@Override
	public void paintComponent( Graphics g)
	{
		super.paintComponent( g);
		
		if (maze != null && hole != null && 
				sequence != null && shooter != null)
		{
			maze.draw( g);
			hole.draw( g);
			sequence.draw( g);
			shooter.draw( g, at);
		}
	}
	
	public void setAffineTransform( AffineTransform at)
	{
		this.at = at;
	}
	
	public Maze getMaze()
	{
		return maze;
	}
	
	public void setMaze( Maze maze)
	{
		this.maze = maze;
	}
	
	public Hole getHole()
	{
		return hole;
	}
	
	public void setHole( Hole hole)
	{
		this.hole = hole;
	}
	
	public BallSequence getSequence()
	{
		return sequence;
	}
	
	public void setSequence( BallSequence sequence)
	{
		this.sequence = sequence;
	}
	
	public BallShooter getShooter()
	{
		return shooter;
	}
	
	public void setShooter( BallShooter shooter)
	{
		this.shooter = shooter;
	}
}
