package gamecontrol;
import javax.swing.Timer;

public class TimerManager 
{
	private Timer timer;
	private Timer sequenceTimer;
	private Timer coinTimer;
	
	public TimerManager( LevelTimerListener levelTimerListener, 
						 LevelTimerListener.SequenceTimerListener sequenceTimerListener, 
						 int millis)
	{
		timer = new Timer( 10, levelTimerListener);
		sequenceTimer = new Timer( millis, sequenceTimerListener);
	}
	
	public void startTimer()
	{
		timer.start();
		sequenceTimer.start();
	}
	
	public void stopTimer()
	{
		timer.stop();
		sequenceTimer.stop();
	}
	
	public void startSequence()
	{
		sequenceTimer.start();
	}
	
	public void stopSequence()
	{
		sequenceTimer.stop();
	}
	
	public void freezeTimer( int delay)
	{
		sequenceTimer.setDelay( delay);
	}
	
	public boolean getFrozen()
	{
		return sequenceTimer.isRunning();
	}
}
