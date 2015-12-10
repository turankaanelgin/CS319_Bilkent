package gamecontrol.levelcontrol;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import gameentity.Ball;
import gameentity.LevelScreen;

public class LevelInputManager extends MouseAdapter 
{
	private LevelManager levelManager;
	
	public LevelInputManager( final LevelManager levelManager)
	{
		this.levelManager = levelManager;
	}
	
	public void addLevelShortcuts()
	{
		LevelScreen levelScreen = levelManager.getLevelScreen();
		
		String helpKey = "help";
		Action help = new AbstractAction( helpKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				levelManager.help();
			}
		};
		levelScreen.getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
								 KeyStroke.getKeyStroke( KeyEvent.VK_H, 0), helpKey);
		levelScreen.getActionMap().put( helpKey, help);
		
		String pauseKey = "pause";
		Action pause = new AbstractAction( pauseKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				levelManager.pause();
			}
		};
		levelScreen.getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
								 KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0), pauseKey);
		levelScreen.getActionMap().put( pauseKey, pause);
		
		String freezeKey = "freeze";
		Action freeze = new AbstractAction( freezeKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				levelManager.changeBallType( Ball.BallType.FREEZE);
			}
		};
		levelScreen.getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
								 KeyStroke.getKeyStroke( KeyEvent.VK_UP, 0), freezeKey);
		levelScreen.getActionMap().put( freezeKey, freeze);
		
		String backKey = "back";
		Action back = new AbstractAction( backKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				levelManager.changeBallType( Ball.BallType.BACK);
			}
		};
		levelScreen.getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
								 KeyStroke.getKeyStroke( KeyEvent.VK_LEFT, 0), backKey);
		levelScreen.getActionMap().put( backKey, back);
		
		String bombKey = "bomb";
		Action bomb = new AbstractAction( bombKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				levelManager.changeBallType( Ball.BallType.BOMB);
			}
		};
		levelScreen.getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
								 KeyStroke.getKeyStroke( KeyEvent.VK_RIGHT, 0), bombKey);
		levelScreen.getActionMap().put( bombKey, bomb);
		
		String undoKey = "undo";
		Action undo = new AbstractAction( undoKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				levelManager.undoChangeBallType();
			}
		};
		levelScreen.getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
								 KeyStroke.getKeyStroke( KeyEvent.VK_DOWN, 0), undoKey);
		levelScreen.getActionMap().put( undoKey, undo);
	}
	
	@Override
	public void mousePressed( MouseEvent event)
	{
		if (!levelManager.isShooting())
			levelManager.throwBall( event.getPoint());
		levelManager.checkCoin( event.getPoint());
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
}
