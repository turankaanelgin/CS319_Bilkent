package userinterface;

import gamecontrol.GameManager;

public class HelpScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;

	public HelpScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
	}
	
	public void display()
	{
		screenManager.openScreen( "help");
	}
}
