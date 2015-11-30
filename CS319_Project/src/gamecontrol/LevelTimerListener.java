package gamecontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameentity.Ball;
import gameentity.BallSequence;
import gameentity.BallShooter;
import gameentity.Hole;
import gameentity.LevelScreen;

public class LevelTimerListener implements ActionListener
{
	private LevelManager levelManager;
	private boolean rewinds;
	
	public LevelTimerListener( LevelManager levelManager)
	{
		this.levelManager = levelManager;
	}
	
	public void setRewinds( boolean rewinds)
	{
		this.rewinds = rewinds;
	}
	
	@Override
	public void actionPerformed( ActionEvent arg0)
	{
		LevelScreen screen = levelManager.getLevelScreen();
		final BallSequence sequence = screen.getSequence();
		BallShooter shooter = screen.getShooter();
		final Hole hole = screen.getHole();
		
		if (!shooter.hasBall())
		{
			shooter.prepareBall();
			shooter.switchHasBall();
		}
		
		if (shooter.isShooting())
		{
			shooter.shoot( shooter.getShootPoint());
			
			Ball current = shooter.getCurrent();
			int index = sequence.contains( current.getPoint());
			if (index != -1)
			{
				levelManager.updateSequence( current, sequence, index);
				shooter.switchShooting();
				shooter.switchHasBall();
				shooter.prepareBall();
			}
			else if (current.getPoint().getX() >= screen.getWidth() ||
					 current.getPoint().getY() >= screen.getHeight() ||
					 current.getPoint().getX() <= 0 ||
					 current.getPoint().getY() <= 0)
			{
				shooter.switchShooting();
				shooter.switchHasBall();
				shooter.prepareBall();
			}
		}
		
		if (hole.containsBall( sequence))
			levelManager.finishLevel( 0); // lost
		if (sequence.getSize() == 0)
			levelManager.finishLevel( 1); // won
		screen.repaint();
	}
	
	public class SequenceTimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			LevelScreen screen = levelManager.getLevelScreen();
			BallSequence sequence = screen.getSequence();
			sequence.slide();
		}	
	}	
}
