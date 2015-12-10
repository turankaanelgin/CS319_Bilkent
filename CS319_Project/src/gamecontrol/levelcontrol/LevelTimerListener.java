package gamecontrol.levelcontrol;

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
	private boolean coinExists;
	private boolean freeze;
	private int rewindTime;
	private int time;
	private boolean guest;
	
	public LevelTimerListener( final LevelManager levelManager)
	{
		this.levelManager = levelManager;
		coinExists = false;
		freeze = false;
		rewindTime = 0;
		guest = false;
	}
	
	public void setAsGuest()
	{
		guest = true;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public void initialize()
	{
		freeze = false;
		rewindTime = 0;
	}
	
	public void setRewindTime()
	{
		rewindTime = 1;
	}
	
	public void setFreeze( boolean freeze)
	{
		this.freeze = freeze;
	}
	
	public void removeCoin()
	{
		coinExists = false;
	}
	
	
	public void loop()
	{
		LevelScreen screen = levelManager.getLevelScreen();
		final BallSequence sequence = screen.getSequence();
		final BallShooter shooter = screen.getShooter();
		final Hole hole = screen.getHole();
		
		if (!guest)
		{
			int coin = (int) (Math.random() * 100);
			
			if (coin == 5 && !coinExists)
			{
				levelManager.drawCoin();
				coinExists = true;
			}
		}
		
		if (!shooter.hasBall())
		{
			shooter.prepareBall( sequence);
			shooter.switchHasBall();
		}
		
		for (int i = 0; i < 5; i++)
		{
			if (shooter.isShooting())
			{
				shooter.shoot( shooter.getShootPoint());
				final Ball current = shooter.getCurrent();
				final int index = sequence.contains( current.getPoint());
				if (index != -1)
				{
					levelManager.updateSequence( current, sequence, index);
					shooter.switchShooting();
					shooter.switchHasBall();
					shooter.prepareBall( sequence);	
					break;
				}
				else if (current.getPoint().getX() >= screen.getWidth() ||
						 current.getPoint().getY() >= screen.getHeight() ||
						 current.getPoint().getX() <= 0 ||
						 current.getPoint().getY() <= 0)
				{
					shooter.switchShooting();
					shooter.switchHasBall();
					shooter.prepareBall( sequence);
				}
			}
		}
		
		if (hole.containsBall( sequence))
			levelManager.finishLevel( 0); // lost
		else if (sequence.getSize() == 0)
			levelManager.finishLevel( 1); // won
		screen.repaint();
	}
	
	@Override
	public void actionPerformed( ActionEvent arg0)
	{
		loop();
	}
	
	public class SequenceTimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (!freeze)
			{
				time++;
				LevelScreen screen = levelManager.getLevelScreen();
				BallSequence sequence = screen.getSequence();
				
				if (rewindTime == 0)
					sequence.slide();
				else
				{	
					sequence.rewind();
					rewindTime++;
					rewindTime %= 5;
				}
			}
		}	
	}	
}
