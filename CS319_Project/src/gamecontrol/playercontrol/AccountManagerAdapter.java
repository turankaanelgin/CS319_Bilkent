package gamecontrol.playercontrol;

import java.util.Iterator;
import java.util.Map.Entry;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import gamecontrol.GameManager;

public class AccountManagerAdapter extends AccountManager
{
	public AccountManagerAdapter( GameManager gameManager)
	{
		super( gameManager);
	}
	
	@Override
	public void changePlayerPassword( String password)
	{
		currentPlayer.setPassword( encryptPassword( password));
	}
	
	@Override
	public int addPlayer( String username, String password)
	{
		return super.addPlayer( username, encryptPassword( password));
	}
	
	@Override
	public void loadPlayers()
	{
		super.loadPlayers();
		
		Iterator<Entry<Integer, Player>> it = players.entrySet().iterator();
		while (it.hasNext())
		{
			Entry<Integer, Player> entry = it.next();
			Player value = entry.getValue();
			
			gameManager.addPlayerProxy( value.getId(), decryptPassword( value.getPassword()));
		}
		gameManager.setScoreNo();
		gameManager.setTopTen( topTen);
		gameManager.addPlayerObservers();
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
