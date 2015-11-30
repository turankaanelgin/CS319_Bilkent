package gamecontrol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import gameentity.Ball;

public class AccountManager
{
	private GameManager gameManager;
	private int lastId;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private XStream xStream;
	
	public AccountManager( GameManager gameManager)
	{
		this.gameManager = gameManager;
		lastId = 0;
		players = new ArrayList<Player>();
		currentPlayer = null;
		xStream = new XStream( new StaxDriver());
	}
	
	public int getLastId()
	{
		return lastId;
	}
	
	public boolean hasItem( Ball.BallType type)
	{
		return currentPlayer.hasItem( type);
	}
	
	public void addPlayer( String userName, String password)
	{
		Player player = new Player( ++lastId, userName, password);
		players.add( lastId - 1, player);
		gameManager.addPlayerProxy( lastId, password);
	}
	
	public void removePlayer( int id)
	{
		players.set( id - 1, null);
	}
	
	public void addPlayerItem( StoreItem item)
	{
		currentPlayer.addItem( item);
	}
	
	public void addPlayerCheckpoint( int levelNo)
	{
		currentPlayer.setCheckpoint( levelNo);
	}
	
	public void addPlayerObserver( Observer o)
	{
		currentPlayer.addObserver( o);
	}
	
	public void addPlayerScore( int levelNo, int score)
	{
		currentPlayer.updateScore( levelNo, score);
	}
	
	public void saveGame()
	{
		String xmlStr = xStream.toXML( players);
		
		try
		{
			BufferedWriter out = new BufferedWriter( new FileWriter( "accounts.prj"));
			out.write( xmlStr);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadPlayers()
	{
		File file = new File( "accounts.prj");
		String xmlStr = "";
		
		try
		{
			@SuppressWarnings("resource")
			Scanner scan = new Scanner( file);
			xmlStr = scan.nextLine();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		if (xmlStr != "")
			players = (ArrayList<Player>) xStream.fromXML( xmlStr);
	}
	
	public void loadCurrentPlayer( int id)
	{
		currentPlayer = players.get( id - 1);
	}
}
