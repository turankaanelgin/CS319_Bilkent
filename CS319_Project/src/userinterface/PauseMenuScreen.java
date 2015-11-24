package userinterface;

public class PauseMenuScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;

	public PauseMenuScreen( ScreenManager screenManager)
	{
		super( screenManager);
	}
	
	public void display()
	{
		screenManager.openScreen( "pause");
	}
}
