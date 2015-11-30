package userinterface;

import java.awt.CardLayout;

import javax.swing.JPanel;

import gamecontrol.GameManager;
import gameentity.LevelScreen;

public class ScreenManager
{
	private CardLayout layout;
	private LoginScreen loginScreen;
	private CreateAccountScreen createAccountScreen;
	private MainMenuScreen mainMenuScreen;
	private LevelScreen levelScreen;
	
	public ScreenManager( GameFrame frame)
	{
		layout = new CardLayout();
		frame.setLayout( layout);
		
		GameManager gameManager = new GameManager();
		loginScreen = new LoginScreen( this, gameManager);
		createAccountScreen = new CreateAccountScreen( this, gameManager);
		mainMenuScreen = new MainMenuScreen( this, gameManager);
		levelScreen = new LevelScreen();
		
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.add( loginScreen, "login");
		contentPane.add( createAccountScreen, "create account");
		contentPane.add( mainMenuScreen, "main menu");
		contentPane.add( levelScreen, "level");
	}
	
	public LevelScreen getLevelScreen()
	{
		return levelScreen;
	}
	
	public void openScreen( String screen)
	{
		if (screen.equals( "login"))
			layout.show( loginScreen.getParent(), "login");
		else if (screen.equals( "create account"))
			layout.show( createAccountScreen.getParent(), "create account");
		else if (screen.equals( "main menu"))
			layout.show( mainMenuScreen.getParent(), "main menu");
		else if (screen.equals( "level"))
			layout.show( levelScreen.getParent(), "level");
	}
}
