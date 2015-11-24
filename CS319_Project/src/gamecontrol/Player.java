package gamecontrol;

import java.util.Observable;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import gameentity.Ball;

public class Player extends Observable
{
	private int id;
	private String username;
	private String password;
	private int balance;
	private int[] noOfItems;
	private int[] scores;
	private int checkpoint;
	
	public Player( int id, String username, String password)
	{
		this.setId( id);
		this.setUsername( username);
		this.setPassword( password);
		setBalance( 0);
		noOfItems = new int[4];
		scores = new int[10];
		setCheckpoint( 0);
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
		return decryptPassword( password);
	}

	public void setPassword( String password)
	{
		this.password = encryptPassword( password);
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance( int balance)
	{
		this.balance = balance;
	}

	public int getCheckpoint()
	{
		return checkpoint;
	}

	public void setCheckpoint( int checkpoint)
	{
		this.checkpoint = checkpoint;
	}
	
	public void addCoin( int value)
	{
		setBalance( getBalance() + value);
	}
	
	public void removeCoin( int value)
	{
		setBalance( getBalance() - value);
	}
	
	public void addItem( StoreItem item)
	{
		noOfItems[item.index]++;
	}
	
	public void updateScore( int levelNo, int score)
	{
		scores[levelNo - 1] = score;
		setChanged();
		notifyObservers();
	}
	
	public boolean hasItem( Ball.BallType type)
	{
		StoreItem item = null;
		
		if (type == Ball.BallType.BOMB)
			item = StoreItem.BOMB_BALL;
		else if (type == Ball.BallType.BACK)
			item = StoreItem.BACK_BALL;
		else if (type == Ball.BallType.FREEZE)
			item = StoreItem.FREEZE_BALL;
		
		if (noOfItems[item.index] > 0)
			return true;
		return false;
	}
	
	private String encryptPassword( String password)
	{
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword( "password");
		return encryptor.encrypt( password);
	}
	
	private String decryptPassword( String password)
	{
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword( "password");
		return encryptor.decrypt( password);
	}
}
