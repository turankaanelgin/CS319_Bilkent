package gamecontrol;

import java.util.ArrayList;

import gameentity.Ball;
import gameentity.LevelScreen;
import userinterface.CreateAccountScreen;
import userinterface.DeleteAccountScreen;
import userinterface.HelpScreen;
import userinterface.HighScoreScreen;
import userinterface.LevelMenuScreen;
import userinterface.LoginScreen;
import userinterface.MainMenuScreen;
import userinterface.PauseMenuScreen;
import userinterface.StoreMenuScreen;

public class GameManager
{
	private AccountManager accountManager;
	private LevelManager levelManager;
	private ArrayList<PlayerProxy> players;
	private MainMenuScreen mainMenuScreen;
	private StoreMenuScreen storeMenuScreen;
	private PauseMenuScreen pauseMenuScreen;
	private LevelMenuScreen levelMenuScreen;
	private LoginScreen loginScreen;
	private CreateAccountScreen createAccountScreen;
	private DeleteAccountScreen deleteAccountScreen;
	private HelpScreen helpScreen;
	private HighScoreScreen highScoreScreen;
	
	public GameManager()
	{
		accountManager = new AccountManager( this);
		players = new ArrayList<PlayerProxy>();
	}
	
	public void setLoginScreen( LoginScreen loginScreen)
	{
		this.loginScreen = loginScreen;
	}
	
	public void setCreateAccountScreen( CreateAccountScreen createAccountScreen)
	{
		this.createAccountScreen = createAccountScreen;
	}
	
	public void startLevel( int levelNo, LevelScreen levelScreen)
	{
		levelManager = new LevelManager( this, levelNo, levelScreen);
	}
	
	public void addPlayer( String name, String password)
	{
		accountManager.addPlayer( name, password);
	}
	
	public void removePlayer( int id, String password)
	{
		PlayerProxy proxy = players.get( id);
		if (proxy.getPassword().equals( password))
		{
			accountManager.removePlayer( id);
			players.remove( proxy);
		}
	}
	
	public void addPlayerItem( StoreItem item)
	{
		accountManager.addPlayerItem( item);
	}
	
	public void addPlayerScore( int levelNo, int score)
	{
		accountManager.addPlayerScore( levelNo, score);
	}
	
	public void setCurrentPlayer( int id, String password)
	{
		
	}
	
	public boolean hasItem( Ball.BallType type)
	{
		return accountManager.hasItem( type);
	}
	
	public void pause()
	{
		pauseMenuScreen.display();
	}
	
	public void help()
	{
		helpScreen.display();
	}
	
	public void addPlayerProxy( int id, String password)
	{
		players.add( id, new PlayerProxy( id, password));
	}
}
