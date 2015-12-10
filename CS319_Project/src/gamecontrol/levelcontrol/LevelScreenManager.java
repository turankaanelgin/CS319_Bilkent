package gamecontrol.levelcontrol;
import gameentity.Ball;
import gameentity.BallSequence;
import gameentity.BallShooter;
import gameentity.Coin;
import gameentity.Hole;
import gameentity.LevelScreen;
import gameentity.Maze;

public class LevelScreenManager
{
	private LevelScreen levelScreen;
	
	public LevelScreenManager( LevelManager levelManager, LevelScreen levelScreen)
	{
		this.levelScreen = levelScreen;
		initializeScreen( levelManager);
	}
	
	public void initializeScreen( LevelManager levelManager)
	{
		levelScreen.setMaze( new Maze());
		levelScreen.setHole( new Hole());
		levelScreen.setShooter( new BallShooter());
		levelScreen.setLevelNo( levelManager.getLevelNo());
		
		BallSequence sequence = new BallSequence();
		for (int i = 0; i < 20 * levelManager.getLevelNo(); i++)
			sequence.addToBalls( new Ball());
		
		levelScreen.setSequence( sequence);
		
		LevelInputManager levelInputManager = levelManager.getLevelInputManager();
		levelScreen.addMouseListener( levelInputManager);
		levelScreen.addMouseMotionListener( levelInputManager);
	}
	
	public void setAsGuest()
	{
		levelScreen.setAsGuest();
	}
	
	public void setAsUser()
	{
		levelScreen.setAsUser();
	}
	
	public void setLevelNo( int levelNo)
	{
		levelScreen.setLevelNo( levelNo);
	}
	
	public LevelScreen getScreen()
	{
		return levelScreen;
	}
	
	public void drawCoin()
	{
		levelScreen.setCoin( new Coin());
	}
	
	public void removeCoin()
	{
		levelScreen.setCoin( null);
	}
}
