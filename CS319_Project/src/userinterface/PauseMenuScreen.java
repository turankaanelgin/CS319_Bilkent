package userinterface;

import gamecontrol.GameManager;

public class PauseMenuScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;

	public PauseMenuScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
	}
	
	public void display()
	{
		screenManager.openScreen( "pause");
	}
}
