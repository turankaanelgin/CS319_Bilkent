package gamecontrol.levelcontrol;

import java.util.ArrayList;
import java.util.Collections;

import gameentity.Ball;
import gameentity.BallSequence;

public class CollisionManager
{	
	private LevelManager levelManager;
	
	public CollisionManager( LevelManager levelManager)
	{
		this.levelManager = levelManager;
	}
	
	public void updateSequence( final Ball current, BallSequence sequence, int index)
	{
		boolean hit = false;
		int rightIndex = sequence.getSize() - 1, leftIndex = 0;
		
		levelManager.stopSequence();
		sequence.addToBalls( new Ball( current.getColor(), Ball.BallType.PLAIN, 
									   Ball.getSpecificDirection( current)), index + 1);
		
		while (true)
		{
			int cnt = 0;
			ArrayList<Integer> indices = new ArrayList<Integer>();
			Ball b = sequence.get( index + 1);
				
			for (int i = index; i >= 0; i--)
			{
				if (sequence.get( i).getColor().equals( b.getColor()))
				{
					cnt++;
					indices.add( i);
				}
				else
					break;
			}
				
			for (int i = index + 1; i < sequence.getSize(); i++)
			{
				if (sequence.get( i).getColor().equals( b.getColor()))
				{
					cnt++;
					indices.add( i);
				}
				else
					break;
			}
				
			if (cnt >= 3)
			{
				Collections.sort( indices, Collections.reverseOrder());
				rightIndex = indices.get( 0) + 1;
				leftIndex = indices.get( indices.size() - 1) - 1;
				
				for (int i = 0; i < indices.size(); i++)
					sequence.removeBall( indices.get( i));
				index = indices.get( indices.size() - 1) - 1;
				hit = true;
				if (index <= -1 || index >= sequence.getSize() - 1)
					break;
			}
			else
				break;
		}
		
		if (hit)
		{
			levelManager.playSound( current.getType());
			
			if (current.getType() == Ball.BallType.FREEZE)
				levelManager.freezeTimer();
			else if (current.getType() == Ball.BallType.BACK)
				levelManager.rewindSequence();
			else if (current.getType() == Ball.BallType.BOMB)
			{
				if (rightIndex + 1 < sequence.getSize())
					sequence.removeBall( rightIndex + 1); 
				if (rightIndex < sequence.getSize())	
					sequence.removeBall( rightIndex);
				if (leftIndex >= 0)
					sequence.removeBall( leftIndex);
				if (leftIndex - 1 >= 0)
					sequence.removeBall( leftIndex - 1);
			}
		}
		hit = false;
		
		levelManager.getLevelScreen().repaint();
		levelManager.startSequence();
	}
}
