package gamecontrol.levelcontrol;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerManager 
{
	private LevelManager levelManager;
	private Timer timer;
	private Timer sequenceTimer;
	private Timer coinTimer;
	private Timer freezeTimer;
	private int coinTime;
	private int freezeTime;
	
	public TimerManager( LevelManager levelManager, LevelTimerListener levelTimerListener, 
						 LevelTimerListener.SequenceTimerListener sequenceTimerListener, 
						 int millis)
	{
		this.levelManager = levelManager;
		timer = new Timer( 1, levelTimerListener);
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
	
	public void startFreezeTimer()
	{
		freezeTimer = new Timer( 1000, new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e)
			{
				freezeTime++;
				freezeTime = freezeTime % 5;
				if (freezeTime == 0)
					levelManager.stopFreezeTimer();
			}
		});
		freezeTimer.start();
	}
	
	public void stopFreezeTimer()
	{
		if (freezeTimer != null)
			freezeTimer.stop();
	}
	
	public void startCoinTimer()
	{
		coinTimer = new Timer( 1000, new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e)
			{
				coinTime++;
				coinTime = coinTime % 5;
				if (coinTime == 0)
					levelManager.removeCoin();
			}
		});
		coinTimer.start();
	}
	
	public void stopCoinTimer()
	{
		if (coinTimer != null)
			coinTimer.stop();
	}
	
	public void startSequence()
	{
		sequenceTimer.start();
	}
	
	public void stopSequence()
	{
		sequenceTimer.stop();
	}
}
