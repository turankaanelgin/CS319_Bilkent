package gamecontrol;

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
	
	public void updateSequence( Ball current, BallSequence sequence, int index)
	{
		levelManager.stopSequence();
		sequence.addToBalls( new Ball( current.getColor(), current.getType(), 
									   Ball.getSpecificDirection( current)), index + 1);
		
		while (true)
		{
			int cnt = 0;
			ArrayList<Integer> indices = new ArrayList<Integer>();
			Ball b = sequence.get( index);
				
			for (int i = index - 1; i >= 0; i--)
			{
				if (sequence.get( i).getColor().equals( b.getColor()))
				{
					cnt++;
					indices.add( i);
				}
				else
					break;
			}
				
			for (int i = index; i < sequence.getSize(); i++)
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
					
				for (int i = 0; i < indices.size(); i++)
					sequence.removeBall( indices.get( i));
				index = indices.get( indices.size() - 1);
				levelManager.playSound(Ball.BallType.PLAIN);
			}
			else
				break;
			
			levelManager.getLevelScreen().repaint();
		}
		if(hit && current.getType() == Ball.BallType.BACK)
		{
			levelManager.rewind();
			levelManager.playSound(Ball.BallType.BACK);
			hit = false;
		}
		
		else if(hit && current.getType() == Ball.BallType.BOMB)
		{
			levelManager.explode();
			levelManager.playSound(Ball.BallType.BOMB);
			hit = false;
		}
		levelManager.getLevelScreen().repaint();
		levelManager.startSequence();
	}
}
