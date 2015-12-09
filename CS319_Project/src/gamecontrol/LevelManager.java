package gamecontrol;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

import gameentity.Ball;
import gameentity.BallSequence;
import gameentity.BallShooter;
import gameentity.LevelScreen;

public class LevelManager 
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
	
	public void rewind()
	{
		levelTimerListener.setRewinds();
	}
	
	public void explode()
	{
		levelTimerListener.setExplode();
	}
	
	public void playSound(Ball.BallType type)
	{
		soundManager.playSound(type);
	}

	public int getLevelNo()
	{
		return levelNo;
	}

	private void setLevelNo( int levelNo)
	{
		this.levelNo = levelNo;
	}
	
	public LevelInputManager getLevelInputManager()
	{
		return levelInputManager;
	}
	
	public void startLevel( int levelNo)
	{
		LevelTimerListener levelTimerListener = new LevelTimerListener( this);
		LevelTimerListener.SequenceTimerListener sequenceTimerListener = 
						  levelTimerListener.new SequenceTimerListener();
		
		timerManager = new TimerManager( levelTimerListener, sequenceTimerListener,
										 (int) (1.0F / levelNo * 1000));
		timerManager.startTimer();
		setLevelNo( levelNo);
	}
	
	public void rotateShooter( double angle)
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		AffineTransform at = shooter.rotate( angle);
		screen.setAffineTransform( at);
		screen.repaint();
	}
	
	public void throwBall( Point shoot)
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		shooter.shoot( shoot);
	}
	
	public void updateSequence( Ball current, BallSequence sequence, int index)
	{
		collisionManager.updateSequence( current, sequence, index);
	}
	
	public boolean isShooting()
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		return shooter.isShooting();
	}
	
	public void changeBallType( Ball.BallType type)
	{
		if (gameManager.hasItem( type))
		{
			LevelScreen screen = levelScreenManager.getScreen();
			BallShooter shooter = screen.getShooter();
			shooter.changeBallType( type);
			screen.repaint();
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
			screen.repaint();
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
	
	public LevelScreen getLevelScreen()
	{
		return levelScreenManager.getScreen();
	}
	
	public void startSequence()
	{
		timerManager.startSequence();
	}
	
	public void stopSequence()
	{
		timerManager.stopSequence();
	}
	
	public void finishLevel( int status)
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
}
