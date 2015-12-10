package gamecontrol;

import java.util.HashMap;
import java.util.Observer;

import gamecontrol.levelcontrol.LevelManager;
import gamecontrol.playercontrol.AccountManager;
import gamecontrol.playercontrol.AccountManagerAdapter;
import gamecontrol.playercontrol.Player;
import gamecontrol.playercontrol.StoreItem;
import gameentity.Ball;
import gameentity.LevelScreen;
import gameentity.Ball.BallType;
import userinterface.gamescreen.GameScreen;
import userinterface.gamescreen.HelpScreen;
import userinterface.gamescreen.HighScoreScreen;
import userinterface.gamescreen.LevelMenuScreen;
import userinterface.gamescreen.MainMenuScreen;
import userinterface.gamescreen.PauseMenuScreen;

public class GameManager
{
	private AccountManager accountManager;
	private LevelManager levelManager;
	private HashMap<Integer, String> players;
	private GameScreen pauseMenuScreen;
	private GameScreen helpScreen;
	private GameScreen mainMenuScreen;
	private Observer storeMenuScreen;
	private Observer levelMenuScreen;
	private Observer highScoreScreen;
	private Observer levelScreen;
	
	public GameManager()
	{
		accountManager = new AccountManagerAdapter( this);
		players = new HashMap<Integer, String>();
	}
	
	public void startLevel( int levelNo, LevelScreen levelScreen, boolean guest)
	{
		levelManager = new LevelManager( this, levelNo, levelScreen);
		if (guest)
		{
			levelManager.setAsGuest();
			((PauseMenuScreen) pauseMenuScreen).setAsGuest();
		}
		else 
			((PauseMenuScreen) pauseMenuScreen).setAsUser();
		levelManager.startLevel( levelNo);
	}
	
	public void setMainMenuScreen( GameScreen mainMenuScreen)
	{
		this.mainMenuScreen = mainMenuScreen;
	}
	
	public void setLevelScreens( GameScreen pauseMenuScreen, GameScreen helpScreen)
	{
		this.pauseMenuScreen = pauseMenuScreen;
		this.helpScreen = helpScreen;
	}
	
	public void setObservers( Observer levelMenuScreen, Observer highScoreScreen, 
							  Observer storeMenuScreen, Observer levelScreen)
	{
		this.levelMenuScreen = levelMenuScreen;
		this.highScoreScreen = highScoreScreen;
		this.storeMenuScreen = storeMenuScreen;
		this.levelScreen = levelScreen;
	}
	
	public void setTopTen( Player[] topTen)
	{
		((HighScoreScreen) highScoreScreen).setScoreTable( topTen);
	}
	
	public void setScoreNo()
	{
		((HighScoreScreen) highScoreScreen).setTotalNo( players.size());
	}
	
	/**@pre !(self.accountManager = null)
	 * */
	public int addPlayer( String name, String password)
	{
		int id = ((AccountManagerAdapter) accountManager).addPlayer( name, password);
		addPlayerProxy( id, password);
		setScoreNo();
		setTopTen( accountManager.getTopTen());
		accountManager.setObservers( id);
		return id;
	}
	
	/**@pre !(self.accountManager = null)
	 * @pre self.players->includes(self.key = id & self.value = password) = True
	 * @post self.players->includes(self.key = id & self.value = password) = False
	 * @post self.players->size = self@pre.players->size - 1
	 * */
	public boolean removePlayer( int id, String password)
	{
		if (!players.containsKey( id))
			return false;
		
		if (players.get( id).equals( password))
		{
			players.remove( id);
			accountManager.removePlayer( id);
			return true;
		}
		return false;
	}
	
	/**@pre self.accountManager.getPlayers().getObservers()->asSet()->includes (o) = False
	 * @post self.accountManager.getPlayers().getObservers()->asSet()->includes (o) = True
	 * */
	public void addPlayerObservers()
	{
		accountManager.addPlayerObserver( levelMenuScreen);
		accountManager.addPlayerObserver( highScoreScreen);
		accountManager.addPlayerObserver( storeMenuScreen);
		accountManager.addPlayerObserver( levelScreen);
	}
	
	public void undoPlayerItem( Ball.BallType type)
	{
		if (type == BallType.BOMB)
			accountManager.undoPlayerItem( StoreItem.BOMB_BALL);
		else if (type == BallType.BACK)
			accountManager.undoPlayerItem( StoreItem.BACK_BALL);
		else if (type == BallType.FREEZE)
			accountManager.undoPlayerItem( StoreItem.FREEZE_BALL);
	}
	
	public boolean addPlayerItem( StoreItem item)
	{
		return accountManager.addPlayerItem( item);
	}
	
	public void addPlayerScore( int levelNo, int score)
	{
		accountManager.addPlayerScore( levelNo, score);
	}
	
	public void addPlayerCoin( int value)
	{
		accountManager.addPlayerCoin( value);
	}
	
	public void addPlayerCheckpoint( int checkpoint)
	{
		accountManager.addPlayerCheckpoint( checkpoint);
	}
	
	/**@pre self.players.getKeys()->asSet->includes(id) = False
	 * @post self.players->includes(self.key = id & self.value = password) = True
	 * */
	public void addPlayerProxy( int id, String password)
	{
		if (!players.containsKey( id))
			players.put( id, password);
	}
	
	public StoreItem hasItem( Ball.BallType type)
	{
		return accountManager.hasItem( type);
	}
	
	public void useItem( StoreItem item)
	{
		accountManager.usePlayerItem( item);
	}
	
	public void pause()
	{
		((PauseMenuScreen) pauseMenuScreen).display();
	}
	
	public void help()
	{
		((HelpScreen) helpScreen).display();
	}
	
	public void resume()
	{
		levelManager.startSequence();
	}
	
	public void toggleSound()
	{
		levelManager.toggleSound();
	}
	
	public int getPlayerCheckpoint()
	{
		return accountManager.getPlayerCheckpoint();
	}
	
	public int getPlayerLive()
	{
		return accountManager.getPlayerLive();
	}
	
	/**@pre self.players.getKeys()->includes(id)= True
	 * @pre !(accountManager = null)
	 * */
	public boolean loadPlayer( int id, String password)
	{
		if (!players.containsKey( id))
			return false;
		
		if (players.get( id).equals( password))
		{
			accountManager.loadCurrentPlayer( id);
			return true;
		}
		return false;
	}
	
	public void goToMainMenu()
	{
		((MainMenuScreen) mainMenuScreen).display();
	}
	
	public void goToLevelMenu()
	{
		((LevelMenuScreen) levelMenuScreen).display();
	}
	
	public void saveGame()
	{
		accountManager.saveGame();
	}
	
	public void loadGame()
	{
		accountManager.loadPlayers();
	}
}
