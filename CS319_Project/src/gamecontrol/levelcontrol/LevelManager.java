package gamecontrol.levelcontrol;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

import gamecontrol.GameManager;
import gamecontrol.playercontrol.StoreItem;
import gameentity.Ball;
import gameentity.BallSequence;
import gameentity.BallShooter;
import gameentity.Coin;
import gameentity.LevelScreen;

public class LevelManager 
{
	private GameManager gameManager;
	private LevelInputManager levelInputManager;
	private LevelScreenManager levelScreenManager;
	private CollisionManager collisionManager;
	private SoundManager soundManager;
	private TimerManager timerManager;
	private LevelTimerListener levelTimerListener;
	private int levelNo;
	private boolean undo;
	private Ball.BallType undoBallType;
	private boolean guest;
	
	public LevelManager( GameManager gameManager, int levelNo, 
						 LevelScreen levelScreen)
	{
		this.gameManager = gameManager;
		this.setLevelNo( levelNo);
		levelInputManager = new LevelInputManager( this);
		levelScreenManager = new LevelScreenManager( this, levelScreen);
		collisionManager = new CollisionManager( this);
		soundManager = new SoundManager();
		levelTimerListener = new LevelTimerListener( this);
		undo = false;
		guest = false;
		levelInputManager.addLevelShortcuts();
	}
	
	public void setAsGuest()
	{
		guest = true;
	}
	
	/**@post result = self.levelNo*/
	public int getLevelNo()
	{
		return levelNo;
	}
	
	/** @post result = self.levelNo*/
	private void setLevelNo( int levelNo)
	{
		this.levelNo = levelNo;
	}
	
	public LevelInputManager getLevelInputManager()
	{
		return levelInputManager;
	}
	
	/**@pre !(self.levelTimerListener = null)*/
	public void startLevel( int levelNo)
	{
		LevelTimerListener.SequenceTimerListener sequenceTimerListener = 
						  levelTimerListener.new SequenceTimerListener();
		if (guest)
			levelTimerListener.setAsGuest();
		levelTimerListener.initialize();
		timerManager = new TimerManager( this, levelTimerListener, sequenceTimerListener,
										 (int) (600 - Math.pow( levelNo, 3)));
		timerManager.startTimer();
		levelScreenManager.initializeScreen( this);
		levelScreenManager.setLevelNo( levelNo);
		if (guest)
			levelScreenManager.setAsGuest();
		else
			levelScreenManager.setAsUser();
		setLevelNo( levelNo);
	}
	
	/**@pre !(self.levelScreenManager.getLevelScreen().getShooter() = null)
	 * */
	public void rotateShooter( double angle)
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		AffineTransform at = shooter.rotate( angle);
		screen.setAffineTransform( at);
		screen.repaint();
	}
	
	/**@pre !(self.levelScreenManager.getLevelScreen().getShooter() = null)
	 * */
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
	
	public void drawCoin()
	{
		levelScreenManager.drawCoin();
		timerManager.startCoinTimer();
	}
	
	public void removeCoin()
	{
		levelScreenManager.removeCoin();
		levelTimerListener.removeCoin();
		timerManager.stopCoinTimer();
	}
	
	/**@pre !(self.levelScreenManager.getLevelScreen().getCoin() = null)
	 * */
	public void checkCoin( Point p)
	{
		LevelScreen levelScreen = levelScreenManager.getScreen();
		Coin coin = levelScreen.getCoin();
		
		if (coin != null)
		{
			if (coin.contains( p))
			{
				soundManager.playSound( null);
				int value = coin.getValue();
				gameManager.addPlayerCoin( value);
				levelScreenManager.removeCoin();
			}
		}
	}
	
	public void toggleSound()
	{
		soundManager.toggleSound();
	}
	
	public boolean isShooting()
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		return shooter.isShooting();
	}
	
	/**@pre !(self.levelScreenManager.getLevelScreen().getShooter().getCurrent() = null)*/
	public void changeBallType( Ball.BallType type)
	{
		LevelScreen screen = levelScreenManager.getScreen();
		BallShooter shooter = screen.getShooter();
		
		if (shooter.getCurrent().getType() != type)
		{
			StoreItem item = gameManager.hasItem( type);
			
			if (item != null)
			{
				gameManager.useItem( item);
				undoBallType = shooter.getCurrent().getType();
				shooter.changeBallType( type);
				screen.repaint();
				undo = true;
			}
		}
	}
	
	public void undoChangeBallType()
	{
		if (undoBallType != null && undo)
		{
			LevelScreen screen = levelScreenManager.getScreen();
			BallShooter shooter = screen.getShooter();
			gameManager.undoPlayerItem( shooter.getCurrent().getType());
			shooter.changeBallType( undoBallType);
			screen.repaint();
			undo = false;
			undoBallType = null;
		}
	}
	
	public void pause()
	{
		timerManager.stopSequence();
		gameManager.pause();
	}
	
	public void help()
	{
		timerManager.stopSequence();
		gameManager.help();
	}
	
	public void resumeLevel()
	{
		timerManager.startSequence();
	}
	
	public void playSound( Ball.BallType type)
	{
		soundManager.playSound( type);
	}
	
	public void finishSound()
	{
		soundManager.finishSound();
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
	
	public void freezeTimer()
	{
		timerManager.startFreezeTimer();
		levelTimerListener.setFreeze( true);
	}
	
	public void stopFreezeTimer()
	{
		timerManager.stopFreezeTimer();
		levelTimerListener.setFreeze( false);
		soundManager.finishSound();
	}
	
	public void rewindSequence()
	{
		levelTimerListener.setRewindTime();
	}
	
	public void finishLevel( int status)
	{
		if (status != 0 && status != 1)
			return;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setSize( 450, 500);
		
		if (status == 0)
		{
			if (!guest)
			{
				gameManager.useItem( StoreItem.LIVE);
				int checkpoint = gameManager.getPlayerCheckpoint();
				int live = gameManager.getPlayerLive();
				if (live == 0)
				{
					checkpoint -= checkpoint % 3;
					gameManager.addPlayerCheckpoint( checkpoint);
				}
				frame.setTitle( "You lose");
				frame.getContentPane().add( new LevelLosePanel( this, live, checkpoint, levelNo, frame));
			}
			else
				frame.getContentPane().add( new LevelLosePanel( this, 0, 0, levelNo, frame));
		}
		else if (status == 1)
		{
			frame.setTitle( "You win");
			if (!guest)
			{
				int time = levelTimerListener.getTime();
				int score = 10000 / time;
				gameManager.addPlayerScore( levelNo, score);
				if (gameManager.getPlayerCheckpoint() < levelNo + 1)
					gameManager.addPlayerCheckpoint( levelNo + 1); 
				frame.getContentPane().add( new LevelWinPanel( this, score, levelNo, frame));
			}
			else 
				frame.getContentPane().add( new LevelWinPanel( this, 0, levelNo, frame));
		}
		
		frame.setResizable( false);
		frame.setUndecorated( true);
		frame.setLocationRelativeTo( null);
		frame.pack();
		frame.setVisible( true);
		timerManager.stopTimer();
	}
	
	public void goToMainMenu()
	{
		gameManager.goToMainMenu();
	}
	
	public void goToLevelMenu()
	{
		gameManager.goToLevelMenu();
	}
	
	public void quitGame()
	{
		gameManager.saveGame();
		System.exit( 0);
	}
}
