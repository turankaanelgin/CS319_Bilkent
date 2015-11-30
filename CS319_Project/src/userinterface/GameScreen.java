package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import gamecontrol.GameManager;

public class GameScreen extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	protected ScreenManager screenManager;
	protected GameManager gameManager;
	
	public GameScreen( ScreenManager screenManager, GameManager gameManager)
	{
		this.screenManager = screenManager;
		this.gameManager = gameManager;
		setPreferredSize( new Dimension( 1000, 1000));
		setBackground( Color.LIGHT_GRAY);
		setLayout( null);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
