package gamecontrol;
import javax.swing.Timer;

public class TimerManager 
{
	private Timer timer;
	private Timer coinTimer;
	
	public TimerManager( LevelManager levelManager, int millis)
	{
		timer = new Timer( millis, levelManager);
	}
	
	public void startTimer()
	{
		timer.start();
	}
	
	public void stopTimer()
	{
		timer.stop();
	}
	
	public void freezeTimer( int delay)
	{
		timer.setDelay( delay);
	}
	
	public boolean getFrozen()
	{
		return timer.isRunning();
	}
}
