package gameentity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gamecontrol.playercontrol.Player;
import gamecontrol.playercontrol.StoreItem;

public class LevelScreen extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	private Maze maze;
	private Hole hole;
	private BallSequence sequence;
	private BallShooter shooter;
	private Coin coin;
	private AffineTransform at;
	private JLabel title, nameLabel, balanceLabel; 
	private JLabel bombCount, backCount, freezeCount, liveCount;
	
	public LevelScreen()
	{
		title = new JLabel();
		title.setBounds( 450, 20, 200, 30);
		title.setFont( new Font( "Helvetica", Font.PLAIN, 24));
		add( title);
		
		nameLabel = new JLabel();
		add( nameLabel);
		
		balanceLabel = new JLabel();
		balanceLabel.setBounds( 800, 50, 100, 20);
		add( balanceLabel);
		
		liveCount = new JLabel();
		liveCount.setBounds( 50, 50, 100, 20);
		add( liveCount);
		
		bombCount = new JLabel();
		bombCount.setBounds( 150, 50, 100, 20);
		add( bombCount);
		
		backCount = new JLabel();
		backCount.setBounds( 250, 50, 100, 20);
		add( backCount);
		
		freezeCount = new JLabel();
		freezeCount.setBounds( 350, 50, 100, 20);
		add( freezeCount);
		
		setLayout( null);
		setBackground( Color.LIGHT_GRAY);
		setPreferredSize( new Dimension( 1000, 1000));
	}
	
	@Override
	public void paintComponent( Graphics g)
	{
		super.paintComponent( g);
		
		if (maze != null && hole != null && 
			sequence != null && shooter != null)
		{
			maze.draw( g);
			hole.draw( g);
			sequence.draw( g);
			shooter.draw( g, at);
		}
		
		if (coin != null)
			coin.draw( g);
	}
	
	public void setAsUser()
	{
		add( nameLabel);
		add( balanceLabel);
		add( liveCount);
		add( bombCount);
		add( backCount);
		add( freezeCount);
	}
	
	public void setAsGuest()
	{
		remove( nameLabel);
		remove( balanceLabel);
		remove( liveCount);
		remove( bombCount);
		remove( backCount);
		remove( freezeCount);
	}
	
	public void setLevelNo( int levelNo)
	{
		title.setText( "LEVEL " + levelNo);
	}
	
	public void setAffineTransform( AffineTransform at)
	{
		this.at = at;
	}
	
	public Maze getMaze()
	{
		return maze;
	}
	
	public void setMaze( Maze maze)
	{
		this.maze = maze;
	}
	
	public Hole getHole()
	{
		return hole;
	}
	
	public void setHole( Hole hole)
	{
		this.hole = hole;
	}
	
	public BallSequence getSequence()
	{
		return sequence;
	}
	
	public void setSequence( BallSequence sequence)
	{
		this.sequence = sequence;
	}
	
	public BallShooter getShooter()
	{
		return shooter;
	}
	
	public void setShooter( BallShooter shooter)
	{
		this.shooter = shooter;
	}
	
	public Coin getCoin()
	{
		return coin;
	}
	
	public void setCoin( Coin coin)
	{
		this.coin = coin;
	}

	@Override
	public void update( Observable o, Object arg1)
	{
		Player p = (Player) o;
		
		nameLabel.setBounds( 900, 20, 10 * p.getUsername().length(), 30);
		nameLabel.setText( p.getUsername());
		balanceLabel.setText( "Balance: " + p.getBalance());
		bombCount.setText( "Bomb ball x" + p.getItemCount( StoreItem.BOMB_BALL));
		backCount.setText( "Back ball x" + p.getItemCount( StoreItem.BACK_BALL));
		freezeCount.setText( "Freeze ball x" + p.getItemCount( StoreItem.FREEZE_BALL));
		liveCount.setText( "Live x" + p.getItemCount( StoreItem.LIVE));
	}
}
