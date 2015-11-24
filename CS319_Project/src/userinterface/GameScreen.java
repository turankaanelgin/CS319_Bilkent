package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import gamecontrol.GameManager;

public class GameScreen extends JPanel
{
	private static final long serialVersionUID = 1L;
	protected ScreenManager screenManager;
	protected GameManager gameManager;
	protected GameActionListener gameActionListener;
	
	public GameScreen( ScreenManager screenManager)
	{
		this.screenManager = screenManager;
		gameManager = new GameManager();
		gameActionListener = new GameActionListener();
		
		setPreferredSize( new Dimension( 1000, 1000));
		setBackground( Color.LIGHT_GRAY);
		setLayout( null);
	}
}
