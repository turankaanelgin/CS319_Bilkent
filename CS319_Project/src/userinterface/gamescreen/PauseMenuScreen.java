package userinterface.gamescreen;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import gamecontrol.GameManager;
import userinterface.ScreenManager;

public class PauseMenuScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	private JButton resume, back, level, login, quit;
	private JCheckBox toggleSound;

	public PauseMenuScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		resume = new JButton( "Resume Game");
		resume.addActionListener( this);
		resume.setBounds( 350, 200, 200, 30);
		add( resume);
		
		back = new JButton( "Return to Main Menu");
		back.addActionListener( this);
		back.setBounds( 350, 250, 200, 30);
		
		level = new JButton( "Return to Level Menu");
		level.addActionListener( this);
		level.setBounds( 350, 300, 200, 30);
			
		login = new JButton( "Return to Login Screen");
		login.addActionListener( this);
		login.setBounds( 350, 350, 200, 30);
		add( login);
			
		toggleSound = new JCheckBox( "Sound On/Off");
		toggleSound.setSelected( true);
		toggleSound.addActionListener( this);
		toggleSound.setBounds( 350, 400, 200, 30);
		add( toggleSound);
		
		quit = new JButton( "Quit Game");
		quit.addActionListener( this);
		quit.setBounds( 350, 450, 200, 30);
		add( quit);
		
		addShorcut();
	}
	
	public void display()
	{
		screenManager.openScreen( "pause menu");
	}
	
	public void setAsGuest()
	{
		remove( back);
		remove( level);
	}
	
	public void setAsUser()
	{
		add( back);
		add( level);
	}
	
	private void addShorcut()
	{
		String resumeKey = "resume";
		AbstractAction resume = new AbstractAction( resumeKey)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed( ActionEvent arg0)
			{
				resume();
			}
		};
		getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
					 KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0), resumeKey);
		getActionMap().put( resumeKey, resume);
	}
	
	private void resume()
	{
		screenManager.openScreen( "level");
		gameManager.resume();
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == resume)
			resume();
		else if (e.getSource() == back)
			screenManager.openScreen( "main menu");
		else if (e.getSource() == level)
			screenManager.openScreen( "level menu");
		else if (e.getSource() == login)
			screenManager.openScreen( "login");
		else if (e.getSource() == toggleSound)
			gameManager.toggleSound();
		else if (e.getSource() == quit)
		{
			gameManager.saveGame();
			System.exit( 0);
		}
	}
}
