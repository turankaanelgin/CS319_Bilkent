package gamecontrol;

import gameentity.Ball.BallType;

public class SoundManager
{
	private LevelManager levelManager;
	private final int HIT_SOUND = 0;
	private final int BOMB_SOUND = 0;
	private final int BACK_SOUND = 0;
	private final int FREEZE_SOUND = 0;
	private final int COIN_SOUND = 0;
	
	public SoundManager( LevelManager levelManager)
	{
		this.levelManager = levelManager;
	}
	
	public void playSound( BallType type)
	{
		// todo
	}
}
