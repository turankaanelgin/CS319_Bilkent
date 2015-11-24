package gameentity;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BallShooter extends ScreenElement
{
	private Ball current;
	private double angle;
	private BufferedImage image;
	private boolean shooting;
	
	public BallShooter()
	{
		current = null;
		angle = 0;
		shooting = false;
		
		try
		{
			image = ImageIO.read( new File( "bilkent.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Ball getCurrent()
	{
		return current;
	}
	
	public boolean getShooting()
	{
		return shooting;
	}
	
	public void switchShooting()
	{
		shooting = !shooting;
	}
	
	public void prepareBall()
	{
		current = new Ball();
		setBallType();
	}
	
	public void rotate( int angle)
	{
		// to be implemented
	}
	
	public void changeBallType( Ball.BallType type)
	{
		current.setType( type);
	}
	
	public void shoot()
	{
		double slope = Math.tan( angle);
		Point currPoint = current.point;
		
		if (0 <= angle && angle < 90 || 270 < angle && angle < 360)
		{
			current.setPoint( new Point( currPoint.x + Ball.SIZE, 
							  currPoint.y + (int) (Ball.SIZE * slope)));
		}
		else if (90 < angle && angle < 270)
		{
			current.setPoint( new Point( currPoint.x - Ball.SIZE, 
					  		  currPoint.y + (int) (Ball.SIZE * slope)));
		}
		else if (angle == 90 || angle == 270)
		{
			current.setPoint( new Point( currPoint.x,
							  currPoint.y + (int) (Ball.SIZE * slope)));
		}
		shooting = true;
	}
	
	private void setBallType()
	{
		int random1 = (int) (Math.random() * 4);
		
		if (random1 == 0)
		{
			int random2 = (int) (Math.random() * 3);
			
			switch (random2)
			{
				case 0:
					current.setType( Ball.BallType.BOMB);
					break;
				case 1:
					current.setType( Ball.BallType.BACK);
					break;
				case 2:
					current.setType( Ball.BallType.FREEZE);
					break;
			}
		}
	}
	
	@Override
	public void draw( Graphics g)
	{
		g.drawImage( image, point.x, point.y, null);
	}
}
