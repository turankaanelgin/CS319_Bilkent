package userinterface.gamescreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import gamecontrol.GameManager;
import userinterface.ScreenManager;

public class MainMenuScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	private JButton start, store, highScore, back, quit;
	
	public MainMenuScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		start = new JButton( "Start Game");
		start.addActionListener( this);
		start.setFont( new Font( "Tahoma", Font.PLAIN, 13));
		start.setBounds( 350, 200, 170, 30);
		add( start);
		
		store = new JButton( "Store");
		store.addActionListener( this);
		store.setFont( new Font( "Tahoma", Font.PLAIN, 13));
		store.setBounds( 350, 250, 170, 30);
		add( store);
		
		highScore = new JButton( "View High Scores");
		highScore.addActionListener( this);
		highScore.setFont( new Font( "Tahoma", Font.PLAIN, 13));
		highScore.setBounds( 350, 300, 170, 30);
		add( highScore);
		
		back = new JButton( "Go to Login Page");
		back.addActionListener( this);
		back.setFont( new Font( "Tahoma", Font.PLAIN, 13));
		back.setBounds( 350, 350, 170, 30);
		add( back);
		
		quit = new JButton( "Quit Game");
		quit.addActionListener( this);
		quit.setFont( new Font( "Tahoma", Font.PLAIN, 13));
		quit.setBounds( 350, 400, 170, 30);
		add( quit);
		
		JLabel lblMainMenu = new JLabel( "Main Menu");
		lblMainMenu.setForeground( new Color( 139, 0, 0));
		lblMainMenu.setFont( new Font( "Tahoma", Font.BOLD, 40));
		lblMainMenu.setBounds( 320, 100, 230, 60);
		add( lblMainMenu);
	}
	
	public void display()
	{
		screenManager.openScreen( "main menu");
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == start)
			screenManager.openScreen( "level menu");
		else if (e.getSource() == store)
			screenManager.openScreen( "store menu");
		else if (e.getSource() == highScore)
			screenManager.openScreen( "high score");
		else if (e.getSource() == back)
			screenManager.openScreen( "login");
		else if (e.getSource() == quit)
		{
			gameManager.saveGame();
			System.exit( 0);
		}
	}
}
