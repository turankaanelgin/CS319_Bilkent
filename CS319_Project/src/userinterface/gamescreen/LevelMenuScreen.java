package userinterface.gamescreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;

import gamecontrol.GameManager;
import gamecontrol.playercontrol.Player;
import userinterface.ScreenManager;

public class LevelMenuScreen extends GameScreen implements Observer
{
	private static final long serialVersionUID = 1L;
	
	private JButton[] levels;
	private JLabel[] scores;
	private JButton back;
	
	public LevelMenuScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		levels = new JButton[10];
		scores = new JLabel[10];

		int y = 100;
		for (int i = 0; i < levels.length; i++)
		{
			levels[i] = new JButton( "Level " + (i + 1));
			levels[i].addActionListener( this);
			levels[i].setBounds( 350, y += 50, 150, 30);
			levels[i].setEnabled( false);
			add( levels[i]);
			
			scores[i] = new JLabel();
			scores[i].setBounds( 510, y, 150, 30);
			add( scores[i]);
		}
		
		JLabel lblChooseALevel = new JLabel( "Choose a Level");
		lblChooseALevel.setForeground( new Color( 255, 102, 0));
		lblChooseALevel.setFont( new Font( "Tahoma", Font.BOLD, 26));
		lblChooseALevel.setBounds( 330, 53, 218, 52);
		add( lblChooseALevel);
		
		back = new JButton( "Go to Main Menu");
		back.setBounds( 30, 600, 140, 38);
		back.addActionListener( this);
		add( back);
	}
	
	public void display()
	{
		screenManager.openScreen( "level menu");
	}

	@Override
	public void update( Observable o, Object arg)
	{
		for (int i = 0; i < scores.length; i++)
			scores[i].setText( "Score: " + ((Player) o).getScore( i));
		int checkpoint = ((Player) o).getCheckpoint();
		for (int i = 0; i < checkpoint; i++)
			if (i < levels.length)
				levels[i].setEnabled( true);
		for (int i = checkpoint; i < levels.length; i++)
			levels[i].setEnabled( false);
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == back)
			screenManager.openScreen( "main menu");
		else
		{
			for (int i = 0; i < levels.length; i++)
			{
				if (e.getSource() == levels[i])
				{
					screenManager.openScreen( "level");
					gameManager.startLevel( i + 1, screenManager.getLevelScreen(), false);	
				}
			}
		}
	}
}
