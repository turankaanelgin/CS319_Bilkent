package userinterface.gamescreen;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import gamecontrol.GameManager;
import userinterface.ScreenManager;

public class HelpScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	
	private JButton resume, info, shortcuts;

	public HelpScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		JLabel title = new JLabel( "Help!");
        title.setForeground( new Color( 255, 0, 0));
        title.setFont( new Font( "Tahoma", Font.BOLD, 40));
        title.setBounds( 400, 35, 128, 68);
        add( title);
        
		resume = new JButton( "Resume Game");
        resume.addActionListener( this);
        resume.setBounds( 350, 300, 200, 30);
        add( resume);
        
        info = new JButton( "Game Information");
        info.setBounds( 350, 200, 200, 30);
        info.addActionListener( this);
        add( info);
        
        shortcuts = new JButton( "Mouse & Keyboard");
        shortcuts.setBounds( 350, 250, 200, 30);
        shortcuts.addActionListener( this);
        add( shortcuts);
	}
	
	public void display()
	{
		screenManager.openScreen( "help");
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == resume)
		{
			screenManager.openScreen( "level");
			gameManager.resume();
		}	
		else if (e.getSource() == info)
		{
			File infoFile = new File( "info.html");
			
			try
			{
				Desktop.getDesktop().browse( infoFile.toURI());
			}
			catch (IOException e1){}
		}
		else if (e.getSource() == shortcuts)
		{
			File infoFile = new File( "shortcuts.html");
			
			try
			{
				Desktop.getDesktop().browse( infoFile.toURI());
			}
			catch (IOException e1){}
		}
	}
}
