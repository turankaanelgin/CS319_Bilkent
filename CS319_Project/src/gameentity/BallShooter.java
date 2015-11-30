package gameentity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BallShooter extends ScreenElement
{
	private Ball current;
	private double angle;
	private BufferedImage image;
	private boolean shooting;
	private boolean hasBall;
	private Point shootPoint;
	
	public BallShooter()
	{
		point = new Point( 450, 400);
		current = null;
		angle = 0;
		shooting = false;
		hasBall = false;
		shootPoint = null;
		
		try
		{
			image = ImageIO.read( new File( "bilkent.png"));
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
	
	public boolean isShooting()
	{
		return shooting;
	}
	
	public void switchShooting()
	{
		shooting = !shooting;
	}
	
	public boolean hasBall()
	{
		return hasBall;
	}
	
	public void switchHasBall()
	{
		hasBall = !hasBall;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public Point getShootPoint()
	{
		return shootPoint;
	}
	
	public void prepareBall()
	{
		current = new Ball( new Point( point.x + image.getWidth() / 2 - 10, 
									   point.y + image.getHeight() / 2 - 10));
		setBallType();
	}
	
	public AffineTransform rotate( double angle)
	{
		AffineTransform at = AffineTransform.getTranslateInstance( point.x, point.y);
		at.translate( image.getHeight() / 2, image.getWidth() / 2);
		at.rotate( Math.toRadians( angle));
		at.translate( -image.getWidth() / 2, -image.getHeight() / 2);
		this.angle = angle;
		return at;
	}
	
	public void changeBallType( Ball.BallType type)
	{
		current.setType( type);
	}
	
	public void shoot( Point shootPoint)
	{
		this.shootPoint = shootPoint;
		Point currPoint = current.point;
		double slope = (double) (shootPoint.y - currPoint.y - Ball.SIZE / 2) / (shootPoint.x - currPoint.x - Ball.SIZE / 2);
		double shootAngle = Math.toDegrees( Math.atan( slope));
		
		if (Double.isNaN( shootAngle) && (-180 <= angle && angle <= -80 || 170 <= angle && angle <= 180))
		{
			current.setPoint( new Point( currPoint.x - Ball.SIZE, currPoint.y - Ball.SIZE));
			shooting = true;
		}
		else if (Double.isNaN( shootAngle) && 60 <= angle && angle <= 100) 
		{
			current.setPoint( new Point( currPoint.x, currPoint.y + Ball.SIZE));
			shooting = true;
		}
		else if (Double.isNaN( shootAngle) && 100 <= angle && angle <= 170) 
		{
			current.setPoint( new Point( currPoint.x - Ball.SIZE, currPoint.y + Ball.SIZE));
			shooting = true;
		}
		else if (Double.isNaN( shootAngle) && -90 <= angle && angle <= 0) 
		{
			current.setPoint( new Point( currPoint.x, currPoint.y - Ball.SIZE));
			shooting = true;
		}
		else if ((int) Math.abs( shootAngle) <= 1 && 170 <= angle && angle <= 180)
		{
			current.setPoint( new Point( currPoint.x - Ball.SIZE, currPoint.y));
			shooting = true;
		}
		else if (0 <= shootAngle && shootAngle <= 90 && (-180 <= angle && angle <= -80 || 170 <= angle && angle <= 180))
		{
			current.setPoint( new Point( currPoint.x - Ball.SIZE, currPoint.y - (int) (Ball.SIZE * slope)));
			shooting = true;
		}
		else if (-90 <= shootAngle && shootAngle <= 0 && 85 <= angle && angle <= 175)
		{
			current.setPoint( new Point( currPoint.x - Ball.SIZE, currPoint.y - (int) (Ball.SIZE * slope)));
			shooting = true;
		} 
		else if (70 <= angle && angle <= 90)
		{
			current.setPoint( new Point( currPoint.x, currPoint.y + Ball.SIZE));
			shooting = true;
		}
		else if (0 <= shootAngle && shootAngle <= 90 && 0 <= angle && angle <= 70)
		{
			current.setPoint( new Point( currPoint.x + Ball.SIZE, currPoint.y + (int) (Ball.SIZE * slope)));
			shooting = true;
		}
		else if (-90 <= shootAngle && shootAngle <= 0 && -60 <= angle && angle <= 10)
		{
			current.setPoint( new Point( currPoint.x + Ball.SIZE, currPoint.y + (int) (Ball.SIZE * slope)));
			shooting = true;
		}
		else if (-100 <= shootAngle && shootAngle <= 90 && -85 <= angle && angle <= -60)
		{
			current.setPoint( new Point( currPoint.x, currPoint.y - Ball.SIZE));
			shooting = true;
		}
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
	
	public void draw( Graphics g, AffineTransform at)
	{
		if (at != null)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage( image, at, null);
			if (current != null)
				current.draw( g);
		}
		else
			draw( g);	
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawImage( image, point.x, point.y, null);
		if (current != null)
			current.draw( g);
	}
}
