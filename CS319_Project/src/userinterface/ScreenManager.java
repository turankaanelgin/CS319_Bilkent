package userinterface;

import java.awt.CardLayout;

import javax.swing.JPanel;

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
		loginScreen = new LoginScreen( this);
		createAccountScreen = new CreateAccountScreen( this);
		levelScreen = new LevelScreen();
		
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.add( loginScreen, "login");
		contentPane.add( createAccountScreen, "create account");
	}
	
	public void openScreen( String screen)
	{
		if (screen.equals( "login"))
			layout.show( loginScreen.getParent(), "login");
		else if (screen.equals( "create account"))
			layout.show( createAccountScreen.getParent(), "create account");
	}
}
