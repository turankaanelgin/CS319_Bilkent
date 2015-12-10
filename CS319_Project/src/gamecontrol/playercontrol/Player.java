package gamecontrol.playercontrol;

import java.util.Observable;
import java.util.Observer;

import gameentity.Ball;

public class Player extends Observable implements Comparable<Player>
{
	private int id;
	private String username;
	private String password;
	private int balance;
	private int[] noOfItems;
	private int[] scores;
	private int checkpoint;
	private int rank;
	private static int lastId = 0;
	
	public Player( int id, String username, String password)
	{
		this.setId( id);
		this.setUsername( username);
		this.setPassword( password);
		balance = 0;
		noOfItems = new int[4];
		noOfItems[3] = 3;
		scores = new int[10];
		setCheckpoint( 1);
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}
 
	public void setUsername( String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password)
	{
		this.password = password;
	}

	public int getBalance()
	{
		return balance;
	}

	public int getCheckpoint()
	{
		return checkpoint;
	}

	public void setCheckpoint( int checkpoint)
	{
		this.checkpoint = checkpoint;
		setObservers();
	}
	
	public int getRank()
	{
		return rank;
	}
	
	public void setRank( int rank)
	{
		this.rank = rank;
		setObservers();
	}

	public void addCoin( int value)
	{
		balance += value;
		setObservers();
	}
	
	public void removeCoin( int value)
	{
		balance -= value;
		setObservers();
	}
	
	public int getItemCount( StoreItem item)
	{
		return noOfItems[item.getIndex()];
	}
	
	public void addItem( StoreItem item)
	{
		noOfItems[item.getIndex()]++;
		removeCoin( item.getPrice());
		setObservers();
	}
	
	public void removeItem( StoreItem item)
	{
		if (noOfItems[item.getIndex()] > 0)
		{
			noOfItems[item.getIndex()]--;
			if (noOfItems[StoreItem.LIVE.getIndex()] == 0)
				checkpoint -= checkpoint % 3;
		}
		setObservers();
	}
	
	public void updateScore( int levelNo, int score)
	{
		if (scores[levelNo - 1] < score)
			scores[levelNo - 1] = score;
		setObservers();
	}
	
	public int getScore( int levelNo)
	{
		return scores[levelNo];
	}
	
	public int getOverallScore()
	{
		int overall = 0;
		
		for (int i = 0; i < scores.length; i++)
			overall += scores[i];
		return overall;
	}
	
	public StoreItem hasItem( Ball.BallType type)
	{
		StoreItem item = null;
		
		if (type == Ball.BallType.BOMB)
			item = StoreItem.BOMB_BALL;
		else if (type == Ball.BallType.BACK)
			item = StoreItem.BACK_BALL;
		else if (type == Ball.BallType.FREEZE)
			item = StoreItem.FREEZE_BALL;
		
		if (noOfItems[item.getIndex()] > 0)
			return item;
		return null;
	}
	
	public void setObservers()
	{
		setChanged();
		notifyObservers();
	}
	
	public static int getLastId()
	{
		return lastId;
	}
	
	public static int incrementLastId()
	{
		return ++lastId;
	}
	
	public static void setLastId( int id)
	{
		lastId = id;
	}
	
	@Override
	public void addObserver( Observer o)
	{
		super.addObserver( o);
		setObservers();
	}

	@Override
	public int compareTo(Player p)
	{
		int score = getOverallScore();
		int pScore = p.getOverallScore();
		
		if (score > pScore)
			return 1;
		
		if (score == pScore)
			return 0;
		return -1;
	}
}
