package userinterface.gamescreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

import gamecontrol.GameManager;
import gamecontrol.playercontrol.Player;
import userinterface.ScreenManager;

public class HighScoreScreen extends GameScreen implements Observer
{
	private static final long serialVersionUID = 1L;
	private JButton back;
	private JTable scoreTable;
	private JLabel observedScore, observedRank;
	private int totalNo;

	public HighScoreScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		JLabel title = new JLabel( "High Scores");
		title.setFont( new Font( "Tahoma", Font.BOLD, 28));
		title.setForeground( new Color( 255, 0, 0));
		title.setBounds( 350, 55, 293, 94);
		add( title);
		
		back = new JButton( "Go to Main Menu");
		back.addActionListener( this);
		back.setBounds( 30, 600, 140, 38);
		add( back);
		
		scoreTable = new JTable( 10, 2);
		scoreTable.setBounds( 280, 200, 300, 100);
		scoreTable.setSize( 300, 160);
		scoreTable.setBackground( Color.GREEN);
		scoreTable.setEnabled( false);
		add( scoreTable);
		
		for (int i = 0; i < scoreTable.getRowCount(); i++)
		{
			scoreTable.setValueAt( "-", i, 0);
			scoreTable.setValueAt( "-", i, 1);
		}
		
		observedScore = new JLabel();
		observedScore.setBounds( 300, 400, 100, 20);
		add( observedScore);
		
		observedRank = new JLabel();
		observedRank.setBounds( 450, 400, 200, 20);
		add( observedRank);
	}
	
	public void setScoreTable( Player[] topTen)
	{
		for (int i = 0; i < totalNo; i++)
		{
			if (topTen[i] == null)
				break;
			else
			{
				scoreTable.setValueAt( topTen[i].getUsername(), i, 0);
				scoreTable.setValueAt( topTen[i].getOverallScore(), i, 1);
			}
		}
		
		for (int i = totalNo; i < scoreTable.getRowCount(); i++)
		{
			scoreTable.setValueAt( "-", i, 0);
			scoreTable.setValueAt( "-", i, 1);
		}
	}
	
	public void setTotalNo( int totalNo)
	{
		this.totalNo = totalNo;
	}

	@Override
	public void update( Observable o, Object arg1)
	{
		Player p = (Player) o;
		observedScore.setText( "Your score: " + p.getOverallScore());
		observedRank.setText( "Your rank: " + p.getRank() + " / " + totalNo);
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == back)
			screenManager.openScreen( "main menu");
	}
}
