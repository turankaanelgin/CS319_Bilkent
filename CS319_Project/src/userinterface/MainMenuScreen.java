package userinterface;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import gamecontrol.GameManager;

public class MainMenuScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	private JButton start;
	
	public MainMenuScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		start = new JButton( "START");
		start.setBounds( 200, 200, 100, 50);
		start.addActionListener( this);
		
		add( start);
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == start)
		{
			screenManager.openScreen( "level");
			gameManager.startLevel( 1, screenManager.getLevelScreen());
		}
	}
}
