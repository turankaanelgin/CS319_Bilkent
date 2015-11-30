package gamecontrol;
import gameentity.Ball;
import gameentity.BallSequence;
import gameentity.BallShooter;
import gameentity.Hole;
import gameentity.LevelScreen;
import gameentity.Maze;

public class LevelScreenManager
{
	private LevelScreen levelScreen;
	
	public LevelScreenManager( LevelManager levelManager, LevelScreen levelScreen)
	{
		this.levelScreen = levelScreen;
		levelScreen.setMaze( new Maze());
		levelScreen.setHole( new Hole());
		levelScreen.setShooter( new BallShooter());
		
		BallSequence sequence = new BallSequence();
		for (int i = 0; i < 20 * levelManager.getLevelNo(); i++)
			sequence.addToBalls( new Ball());
		
		levelScreen.setSequence( sequence);
		
		LevelInputManager levelInputManager = levelManager.getLevelInputManager();
		levelScreen.addMouseListener( levelInputManager);
		levelScreen.addMouseMotionListener( levelInputManager);
		levelScreen.addKeyListener( levelInputManager);
	}
	
	public LevelScreen getScreen()
	{
		return levelScreen;
	}
}
