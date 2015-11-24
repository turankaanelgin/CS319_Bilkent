package userinterface;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ScreenManager screenManager;
	
	public GameFrame()
	{
		screenManager = new ScreenManager( this);
		setTitle( "Crazy Shooter");
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		setSize( 1000, 1000);
		setVisible( true);
	}
}
