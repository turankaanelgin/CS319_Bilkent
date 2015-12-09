package gamecontrol;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.*;

import gameentity.Ball;
import gameentity.Ball.BallType;

public class SoundManager extends Applet
{
	private LevelManager levelManager;
	private final int HIT_SOUND = 0;
	private final int BOMB_SOUND = 0;
	private final int BACK_SOUND = 0;
	private final int FREEZE_SOUND = 0;
	private final int COIN_SOUND = 0;
	private boolean isOn;
	
	private AudioClip HIT, FREEZE, BOMB, BACK;
	
	public SoundManager( LevelManager levelManager)
	{
		this.levelManager = levelManager;
	}
	
	public void playSound( BallType type)
	{
		if(type == Ball.BallType.PLAIN)
		{
			HIT = getAudioClip(getCodeBase(), "game_sounds/HIT.wav");
			HIT.play();
		}
		
		else if(type == Ball.BallType.FREEZE)
		{
			FREEZE = getAudioClip(getCodeBase(), "game_sounds/FREEZE.wav");
			FREEZE.play();
		}
		
		else if(type == Ball.BallType.BOMB)
		{
			BOMB = getAudioClip(getCodeBase(), "game_sounds/BOMB.wav");
			BOMB.play();
		}
		
		else if(type == Ball.BallType.BACK)
		{
			BACK = getAudioClip(getCodeBase(), "game_sounds/BACK.wav");
			BACK.play();
		}
	}
}

