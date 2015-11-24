package gameentity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Maze extends ScreenElement
{
	public static final int X = 100, Y = 100;
	public static final int[] BORDERS = {810, 810, 120, 120, 690, 690, 240, 240, 570, 570};
	
	public Maze()
	{
		point = new Point( X, Y);
	}
	
	@Override
	public void draw( Graphics g)
	{
		int size = Ball.SIZE;
		int[] outerX = {point.x, point.x, point.x + BORDERS[1], point.x + BORDERS[1],
						point.x + BORDERS[3], point.x + BORDERS[3], point.x + BORDERS[5],
						point.x + BORDERS[5], point.x + BORDERS[7], point.x + BORDERS[7],
						point.x + BORDERS[9]};
		int[] outerY = {point.y, point.y + BORDERS[0], point.y + BORDERS[0],
						point.y + BORDERS[2], point.y + BORDERS[2], point.y + BORDERS[4],
						point.y + BORDERS[4], point.y + BORDERS[6], point.y + BORDERS[6],
						point.y + BORDERS[8], point.y + BORDERS[8]};
		int[] innerX = {outerX[0] + size, outerX[1] + size, outerX[2] - size, outerX[3] - size,
						outerX[4] + size, outerX[5] + size, outerX[6] - size, outerX[7] - size,
						outerX[8] + size, outerX[9] + size, outerX[10]};
		int[] innerY = {outerY[0], outerY[1] - size, outerY[2] - size, outerY[3] + size,
						outerY[4] + size, outerY[5] - size, outerY[6] - size, outerY[7] + size,
						outerY[8] + size, outerY[9] - size, outerY[10] - size};
		
		g.setColor( Color.BLACK);
		g.drawPolyline( outerX, outerY, outerX.length);
		g.drawPolyline( innerX, innerY, innerX.length);
	}
}
