package gamecontrol;

import java.awt.Color;
import gameentity.Ball;
import gameentity.Ball.BallType;
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
            // leftIndex and rightIndex will be used for deleting a range from sequence.
            int leftIndex = -1;
            int rightIndex = -1;
            
            Color currentColor = current.getColor();
            BallType currentType = current.getType();
          
            // If the thrown ball does not hit the Sequence, it will be hanged in the maze and remain stable until sequence catch the ball.
            if(!(sequence.contains(current.getPoint())))
            {
                return true;
            }
            // If thrown ball hit the sequence, sequence will be updated.
            if(sequence.contains(current.getPoint()))
            {
                // In sequence, identifies the ball which is hit by current, its color and its index in sequence.
                int targetIndex = sequence.getIndexOfTargetBall(current.getPoint());
                Ball target = sequence.getTargetBall(current.getPoint());
                Color targetColor = target.getColor();
                
                //If current hits the target from left part
                if(target.containsLeftPart(current.getPoint()))
                {
                    // Add current to sequence
                    sequence.addToBalls(targetIndex, current);
                    
                    // If current and target have the same color
                    if(currentColor.equals(targetColor))
                    {
                        leftIndex = targetIndex - 1;    // initially leftIndex is currentIndex
                        rightIndex = targetIndex;   // initially rightIndex is targetIndex
                        
                        while(((sequence.getBall(leftIndex - 1)).getColor()).equals(targetColor))
                                leftIndex--;
                        
                        while(((sequence.getBall(rightIndex + 1)).getColor()).equals(targetColor))
                                rightIndex++;
                        
                        // Remove the balls with same color in sequence >= 3
                        if(rightIndex - leftIndex >= 3)
                        {
                            sequence.removeBalls(leftIndex, rightIndex);
                        } 
                    }
                    
                    // If current and target have not the same color
                    if(!(currentColor).equals(targetColor))
                    {
                        leftIndex = targetIndex - 1;    // initially leftIndex is currentIndex
                        rightIndex = targetIndex - 1;   // initially rightIndex is currentIndex
                        
                        while(((sequence.getBall(leftIndex - 1)).getColor()).equals(targetColor))
                                leftIndex--;
                        
                        if(rightIndex - leftIndex >= 3)
                        {
                            sequence.removeBalls(leftIndex, rightIndex);
                        }
                    }
                }
                
                // If current hits the target from right part
                if(target.containsRightPart(current.getPoint()))
                {
                    // Add current to sequence
                    sequence.addToBalls(targetIndex + 1, current);
                    
                    // If current and target have the same color
                    if(currentColor.equals(targetColor))
                    {
                        leftIndex = targetIndex;    // initially leftIndex is targetIndex
                        rightIndex = targetIndex + 1;   // initially rightIndex is currentIndex
                        
                        while(((sequence.getBall(leftIndex - 1)).getColor()).equals(targetColor))
                                leftIndex--;
                        
                        while(((sequence.getBall(rightIndex + 1)).getColor()).equals(targetColor))
                                rightIndex++;
                        
                        // Remove the balls with same color in sequence >= 3
                        if(rightIndex - leftIndex >= 3)
                        {
                            sequence.removeBalls(leftIndex, rightIndex);
                        } 
                    }
                    
                    // If current and target have not the same color
                    if(!(currentColor).equals(targetColor))
                    {
                        leftIndex = targetIndex + 1;    // initially leftIndex is currentIndex
                        rightIndex = targetIndex + 1;   // initially rightIndex is currentIndex
                        
                        while(((sequence.getBall(rightIndex + 1)).getColor()).equals(targetColor))
                            rightIndex++;
                        
                        if(rightIndex - leftIndex >= 3)
                        {
                            sequence.removeBalls(leftIndex, rightIndex);
                        }
                    }
                }
                
            }
		return true;
	}
}
