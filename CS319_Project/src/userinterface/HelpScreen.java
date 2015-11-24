package userinterface;

public class HelpScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;

	public HelpScreen( ScreenManager screenManager)
	{
		super( screenManager);
	}
	
	public void display()
	{
		screenManager.openScreen( "help");
	}
}
