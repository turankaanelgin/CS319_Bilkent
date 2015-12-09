package userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameActionListener implements ActionListener, ItemListener
{
	private LoginScreen loginScreen;
	private GameScreen currentScreen;
	
	public void setLoginScreen( LoginScreen loginScreen)
	{
		this.loginScreen = loginScreen;
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}

