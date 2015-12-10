package gamecontrol.levelcontrol;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelLosePanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private LevelManager levelManager;
	private JFrame frame;
	private JLabel liveLabel;
	private JButton playAgain, checkpointButton, mainButton, levelButton, quitButton;
	private int checkpoint, levelNo;
	
	public LevelLosePanel( LevelManager levelManager, int live, int checkpoint, int levelNo, JFrame frame)
	{
		this.levelManager = levelManager;
		this.checkpoint = checkpoint;
		this.levelNo = levelNo;
		this.frame = frame;
		
		if (checkpoint == 0)
		{
			playAgain = new JButton( "Play Again");
			playAgain.addActionListener( this);
			playAgain.setBounds( 50, 100, 150, 30);
			add( playAgain);
			return;
		}
		
		liveLabel = new JLabel( live + " live(s) left...");
		liveLabel.setBounds( 50, 50, 150, 20);
		add( liveLabel);
		
		if (live == 0)
		{
			checkpointButton = new JButton( "Go to Level " + checkpoint);
			checkpointButton.addActionListener( this);
			checkpointButton.setBounds( 50, 100, 150, 30);
			add( checkpointButton);
		}
		else
		{
			playAgain = new JButton( "Play Again");
			playAgain.addActionListener( this);
			playAgain.setBounds( 50, 100, 150, 30);
			add( playAgain);
		}
		
		mainButton = new JButton( "Go to Main Menu");
		mainButton.addActionListener( this);
		mainButton.setBounds( 50, 150, 150, 30);
		add( mainButton);
		
		levelButton = new JButton( "Go to Level Menu");
		levelButton.addActionListener( this);
		levelButton.setBounds( 50, 200, 150, 30);
		add( levelButton);
		
		quitButton = new JButton( "Quit Game");
		quitButton.addActionListener( this);
		quitButton.setBounds( 50, 250, 150, 30);
		add( quitButton);
		
		setLayout( null);
		setPreferredSize( new Dimension( 300, 400));
	}

	@Override
	public void actionPerformed( ActionEvent e)
	{
		frame.setVisible( false);
		if (e.getSource() == checkpointButton)
			levelManager.startLevel( checkpoint);
		else if (e.getSource() == playAgain)
			levelManager.startLevel( levelNo);
		else if (e.getSource() == mainButton)
			levelManager.goToMainMenu();
		else if (e.getSource() == levelButton)
			levelManager.goToLevelMenu();
		else if (e.getSource() == quitButton)
			levelManager.quitGame();
	}
}
