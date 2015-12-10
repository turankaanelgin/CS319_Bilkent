package userinterface;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;

import javax.swing.JPanel;

import gamecontrol.GameManager;
import gameentity.LevelScreen;
import userinterface.gamescreen.CreateAccountScreen;
import userinterface.gamescreen.DeleteAccountScreen;
import userinterface.gamescreen.GameScreen;
import userinterface.gamescreen.HelpScreen;
import userinterface.gamescreen.HighScoreScreen;
import userinterface.gamescreen.LevelMenuScreen;
import userinterface.gamescreen.LoginScreen;
import userinterface.gamescreen.MainMenuScreen;
import userinterface.gamescreen.PauseMenuScreen;
import userinterface.gamescreen.StoreMenuScreen;

public class ScreenManager
{
	private CardLayout layout;
	private GameScreen loginScreen;
	private GameScreen createAccountScreen;
	private GameScreen deleteAccountScreen;
	private GameScreen mainMenuScreen;
	private GameScreen pauseMenuScreen;
	private GameScreen helpScreen;
	private GameScreen levelMenuScreen;
	private GameScreen highScoreScreen;
	private GameScreen storeMenuScreen;
	private LevelScreen levelScreen;
	
	public ScreenManager( GameFrame frame)
	{
		layout = new CardLayout();
		frame.setLayout( layout);
		
		final GameManager gameManager = new GameManager();
		loginScreen = new LoginScreen( this, gameManager);
		createAccountScreen = new CreateAccountScreen( this, gameManager);
		deleteAccountScreen = new DeleteAccountScreen( this, gameManager);
		mainMenuScreen = new MainMenuScreen( this, gameManager);
		levelScreen = new LevelScreen();
		pauseMenuScreen = new PauseMenuScreen( this, gameManager);
		helpScreen = new HelpScreen( this, gameManager);
		levelMenuScreen = new LevelMenuScreen( this, gameManager);
		highScoreScreen = new HighScoreScreen(this, gameManager);
		storeMenuScreen = new StoreMenuScreen(this, gameManager);
		
		gameManager.setObservers( (Observer) levelMenuScreen, (Observer) highScoreScreen,
								  (Observer) storeMenuScreen, (Observer) levelScreen); 
		gameManager.setLevelScreens( pauseMenuScreen, helpScreen);
		gameManager.setMainMenuScreen( mainMenuScreen);

		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.add( loginScreen, "login");
		contentPane.add( createAccountScreen, "create account");
		contentPane.add( deleteAccountScreen, "delete account");
		contentPane.add( mainMenuScreen, "main menu");
		contentPane.add( levelScreen, "level");
		contentPane.add( pauseMenuScreen, "pause menu");
		contentPane.add( helpScreen, "help");
		contentPane.add( levelMenuScreen, "level menu");
		contentPane.add(highScoreScreen, "high score");
		contentPane.add(storeMenuScreen, "store menu");

		
		frame.addWindowListener( new WindowAdapter() {
			
			@Override
			public void windowClosing( WindowEvent e)
			{
				gameManager.saveGame();
			}

			@Override
			public void windowOpened( WindowEvent e)
			{
				gameManager.loadGame();
			}
		});
	}
	
	public LevelScreen getLevelScreen()
	{
		return levelScreen;
	}
	
	public void openScreen( String screen)
	{
		if (screen.equals( "login"))
			layout.show( loginScreen.getParent(), screen);
		else if (screen.equals( "create account"))
			layout.show( createAccountScreen.getParent(), screen);
		else if (screen.equals( "delete account"))
			layout.show( deleteAccountScreen.getParent(), screen);
		else if (screen.equals( "main menu"))
			layout.show( mainMenuScreen.getParent(), screen);
		else if (screen.equals( "level"))
			layout.show( levelScreen.getParent(), screen);
		else if (screen.equals( "pause menu"))
			layout.show( pauseMenuScreen.getParent(), screen);
		else if (screen.equals( "help"))
			layout.show( helpScreen.getParent(), screen);
		else if (screen.equals( "level menu"))
			layout.show( levelMenuScreen.getParent(), screen);
		else if (screen.equals( "high score"))
			layout.show( highScoreScreen.getParent(), "high score");
		else if (screen.equals( "store menu"))
			layout.show( storeMenuScreen.getParent(), "store menu");
	}
}
