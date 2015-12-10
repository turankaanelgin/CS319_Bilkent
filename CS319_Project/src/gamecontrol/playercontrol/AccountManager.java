package gamecontrol.playercontrol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observer;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import gamecontrol.GameManager;
import gameentity.Ball;

public class AccountManager
{
	protected GameManager gameManager;
	protected HashMap<Integer, Player> players;
	protected Player currentPlayer;
	protected Player[] topTen;
	
	public AccountManager( GameManager gameManager)
	{
		this.gameManager = gameManager;
		players = new HashMap<Integer, Player>();
		currentPlayer = null;
		topTen = new Player[10];
	}
	
	public StoreItem hasItem( Ball.BallType type)
	{
		if (currentPlayer != null)
			return currentPlayer.hasItem( type);
		return null;
	}
	
	/**@pre !(self.currentPlayer = null)
	 * @pre !(self.currentPlayer.getPassword() = password)
	 * @post self.currentPlayer.getPassword() = password
	 * */
	public void changePlayerPassword( String password)
	{
		currentPlayer.setPassword( password);
	}
	
	public int getPlayerCheckpoint()
	{
		return currentPlayer.getCheckpoint();
	}
	
	public int getPlayerLive()
	{
		return currentPlayer.getItemCount( StoreItem.LIVE);
	}
	
	/**@pre self.players.->includes(Player(Player.lastId, username, password)) = False
	 * @post self.players.->includes(Player(Player.lastId, username, password)) = True
	 * */
	public int addPlayer( String username, String password)
	{
		int lastId = Player.incrementLastId();
		Player player = new Player( lastId, username, password);
		players.put( lastId, player);
		gameManager.addPlayerObservers();
		setTopTen();
		player.setObservers();
		return lastId;
	}
	
	/**@pre self.players.getKeys()->includes(id) = True*/
	public void removePlayer( int id)
	{
		players.remove( id);
		gameManager.setScoreNo();
		setTopTen();
		gameManager.setTopTen( topTen);
	}
	
	public void setObservers( int id)
	{
		players.get( id).setObservers();
	}
	
	public boolean addPlayerItem( StoreItem item)
	{
		if (currentPlayer.getBalance() >= item.getPrice())
		{
			currentPlayer.addItem( item);
			return true;
		}
		return false;
	}
	
	public void undoPlayerItem( StoreItem item)
	{
		currentPlayer.addItem( item);
		currentPlayer.addCoin( item.getPrice());
	}
	
	public void usePlayerItem( StoreItem item)
	{
		currentPlayer.removeItem( item);
	}
	
	/**@pre !(self.currentPlayer = null)
	 * @pre self.currentPlayer.getCheckpoint() < levelNo 
	 * @post self.currentPlayer.getCheckpoint() = levelNo */
	public void addPlayerCheckpoint( int levelNo)
	{
		currentPlayer.setCheckpoint( levelNo);
	}
	
	/**@pre self.players.getObservers()->asSet()->includes (o) = False
	 * @post self.players.getObservers()->asSet()->includes (o) = True
	 * */
	public void addPlayerObserver( Observer o)
	{
		Iterator<Entry<Integer, Player>> it = players.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<Integer, Player> pair = (Map.Entry<Integer, Player>) it.next();
			pair.getValue().addObserver( o);
			pair.getValue().setObservers();
		}
	}
	
	private void removePlayerObservers()
	{
		Iterator<Entry<Integer, Player>> it = players.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<Integer, Player> pair = (Map.Entry<Integer, Player>) it.next();
			pair.getValue().deleteObservers();
		}
	}
	
	/**@pre !(self.currentPlayer.getScores.select(self.index = levelNo) = score)
	 * @post self.currentPlayer.getScores.select(self.index = levelNo) = score
	 * */
	public void addPlayerScore( int levelNo, int score)
	{
		currentPlayer.updateScore( levelNo, score);
		setTopTen();
		gameManager.setTopTen( topTen);
		currentPlayer.setObservers();
		gameManager.setScoreNo();
		currentPlayer.setRank( getPlayerRank());
	}
	
	public void addPlayerCoin( int value)
	{
		currentPlayer.addCoin( value);
	}
	
	public void saveGame()
	{
		removePlayerObservers();
		String xmlStr = new XStream( new StaxDriver()).toXML( players);
		
		try
		{
			BufferedWriter out = new BufferedWriter( new FileWriter( "accounts.prj"));
			out.write( xmlStr);
			out.close();
		}
		catch (IOException e){}
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
		catch (FileNotFoundException e){}
		
		if (xmlStr != "")
		{
			players = (HashMap<Integer, Player>) new XStream( new StaxDriver()).fromXML( xmlStr);
			
			int lastId = 0;
			Iterator<Entry<Integer, Player>> it = players.entrySet().iterator();
			while (it.hasNext())
			{
				Entry<Integer, Player> entry = it.next();
				int key = entry.getKey();
				
				if (key > lastId)
					lastId = key;
			}
			Player.setLastId( lastId);
			setTopTen();
		}
	}
	
	public void loadCurrentPlayer( int id)
	{
		currentPlayer = players.get( id);
		currentPlayer.setRank( getPlayerRank());
	}
	
	public Player[] getTopTen()
	{
		return topTen;
	}
	
	private void setTopTen()
	{
		ArrayList<Player> tmp = new ArrayList<Player>();
		
		Iterator<Entry<Integer, Player>> it = players.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<Integer, Player> pair = (Map.Entry<Integer, Player>) it.next();
			tmp.add( pair.getValue());
		}
		
		Collections.sort( tmp, Collections.reverseOrder());
		
		for (int i = 0; i < topTen.length && i < tmp.size(); i++)
			topTen[i] = tmp.get( i);
	}
	
	private int getPlayerRank()
	{
		ArrayList<Player> tmp = new ArrayList<Player>();
		
		Iterator<Entry<Integer, Player>> it = players.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<Integer, Player> pair = (Map.Entry<Integer, Player>) it.next();
			tmp.add( pair.getValue());
		}
		
		Collections.sort( tmp, Collections.reverseOrder());
		
		for (int i = 0; i < tmp.size(); i++)
			if (tmp.get( i) == currentPlayer)
				return i + 1;
		return 0;
	}
}
