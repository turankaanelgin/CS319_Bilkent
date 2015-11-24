package gamecontrol;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gameentity.Ball;
import gameentity.BallSequence;
import gameentity.BallShooter;
import gameentity.Hole;
import gameentity.LevelScreen;

public class LevelManager implements ActionListener
{
	private GameManager gameManager;
	private LevelInputManager levelInputManager;
	private LevelScreenManager levelScreenManager;
	private CollisionManager collisionManager;
	private SoundManager soundManager;
	private TimerManager timerManager;
	private int levelNo;
	private boolean undo;
	private Ball.BallType undoBallType;
	
	public LevelManager( GameManager gameManager, int levelNo, 
						 LevelScreen levelScreen)
	{
		this.gameManager = gameManager;
		this.setLevelNo( levelNo);
		levelInputManager = new LevelInputManager( this);
		levelScreenManager = new LevelScreenManager( this, levelScreen);
		collisionManager = new CollisionManager( this);
		soundManager = new SoundManager( this);
		undo = false;
	}

	public int getLevelNo()
	{
		return levelNo;
	}

	public void setLevelNo( int levelNo)
	{
		this.levelNo = levelNo;
	}
	
	public LevelInputManager getLevelInputManager()
	{
		return levelInputManager;
	}
	
	public void startLevel( int levelNo)
	{
		timerManager = new TimerManager( this, (int) (1.0F / levelNo * 1000));
	}
	
	public void rotateShooter( int angle)
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		shooter.rotate( angle);
	}
	
	public void throwBall()
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		shooter.shoot();
	}
	
	public void changeBallType( Ball.BallType type)
	{
		if (gameManager.hasItem( type))
		{
			LevelScreen screen = levelScreenManager.getScreen();
			BallShooter shooter = screen.getShooter();
			shooter.changeBallType( type);
			undo = true;
		}
	}
	
	public void undoChangeBallType()
	{
		if (undoBallType != null && undo)
		{
			LevelScreen screen = levelScreenManager.getScreen();
			BallShooter shooter = screen.getShooter();
			shooter.changeBallType( undoBallType);
			undo = false;
		}
	}
	
	public void pause()
	{
		gameManager.pause();
	}
	
	public void help()
	{
		gameManager.help();
	}
	
	private void finishLevel( int status)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		
		if (status == 0)
		{
			
		}
		else if (status == 1)
		{
			
		}
		
		frame.setVisible( true);
		timerManager.stopTimer();
	}

	@Override
	public void actionPerformed( ActionEvent arg0)
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		BallSequence sequence = screen.getSequence();
		Hole hole = screen.getHole();
		
		sequence.slide();
		
		if (shooter.getShooting())
		{
			shooter.shoot();
			
			Ball current = shooter.getCurrent();
			if (sequence.contains( current.getPoint()))
			{
				collisionManager.updateSequence( current, sequence);
				shooter.switchShooting();
			}
		}
		
		if (hole.containsBall( sequence))
			finishLevel( 0); // lost
		if (sequence.getSize() == 0)
			finishLevel( 1); // won
	}
}
