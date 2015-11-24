package userinterface;

import java.util.Observable;
import java.util.Observer;

import gamecontrol.GameManager;

public class LevelMenuScreen extends GameScreen implements Observer
{

	public LevelMenuScreen( ScreenManager screenManager)
	{
		super( screenManager);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		
	}

}
