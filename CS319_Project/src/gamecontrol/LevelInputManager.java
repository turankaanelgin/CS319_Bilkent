package gamecontrol;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameentity.Ball;

public class LevelInputManager extends MouseAdapter implements KeyListener
{
	private LevelManager levelManager;
	
	public LevelInputManager( LevelManager levelManager)
	{
		this.levelManager = levelManager;
	}
	
	@Override
	public void mousePressed( MouseEvent event)
	{
		if (!levelManager.isShooting())
			levelManager.throwBall( event.getPoint());
	}
	
	@Override
	public void mouseMoved( MouseEvent event)
	{ 
		if (!levelManager.isShooting())
		{
			float xDist = event.getX() - 450;
			float yDist = event.getY() - 450;
			double angle = Math.toDegrees( Math.atan2( yDist, xDist));
			levelManager.rotateShooter( angle);
		}
	}

	@Override
	public void keyPressed( KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			levelManager.pause();
		else if (e.getKeyCode() == KeyEvent.VK_H)
			levelManager.help();
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			levelManager.changeBallType( Ball.BallType.BACK);
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			levelManager.changeBallType( Ball.BallType.BOMB);
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			levelManager.changeBallType( Ball.BallType.FREEZE);
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			levelManager.undoChangeBallType();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
