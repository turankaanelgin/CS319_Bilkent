package gamecontrol;

import gameentity.Ball;
import gameentity.BallSequence;

public class CollisionManager
{
	private LevelManager levelManager;
	
	public CollisionManager( LevelManager levelManager)
	{
		this.levelManager = levelManager;
	}
	
	public boolean updateSequence( Ball current, BallSequence sequence)
	{
		// todo
		return true;
	}
}
