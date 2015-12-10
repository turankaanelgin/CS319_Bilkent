package userinterface;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public GameFrame()
	{
		@SuppressWarnings("unused")
		ScreenManager screenManager = new ScreenManager( this);
		setTitle( "Crazy Shooter");
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		setSize( 1000, 1000);
		setResizable( false);
		setLocationRelativeTo( null);
	}
}
