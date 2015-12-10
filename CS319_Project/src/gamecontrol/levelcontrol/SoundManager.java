package gamecontrol.levelcontrol;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameentity.Ball;
import gameentity.Ball.BallType;

public class SoundManager
{
	private SourceDataLine sourceLine;
	private boolean sound;

	public SoundManager()
	{
		sourceLine = null;
		sound = true;
	}
	
	public void playSound( final BallType type)
	{
		if (sound)
		{
			Runnable r = new Runnable()
			{
				@Override
				public void run()
				{
					createSound( type);	
				}
			};
			new Thread( r).start();
		}
	}
	
	public void toggleSound()
	{
		sound = !sound;
	}
	
	public void createSound( BallType type)
	{
		File soundFile = null;
		if (type == Ball.BallType.PLAIN)
			soundFile = new File( "sounds/hit.wav");
		else if (type == Ball.BallType.BACK)
			soundFile = new File( "sounds/rewind.wav");
		else if (type == Ball.BallType.BOMB)
			soundFile = new File( "sounds/bomb.wav");
		else if (type == Ball.BallType.FREEZE)
			soundFile = new File( "sounds/freeze.wav");
		else 
			soundFile = new File( "sounds/coin.wav");
		AudioInputStream audioStream = null;
		
		try
		{
			audioStream = AudioSystem.getAudioInputStream( soundFile);
		}
		catch (UnsupportedAudioFileException | IOException e) {}
		
		AudioFormat audioFormat = audioStream.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		
        try 
        {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } 
        catch (LineUnavailableException e) 
        {
            e.printStackTrace();
            System.exit(1);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();
        
        int nBytesRead = 0;
        byte[] abData = new byte[128000];
        
        while (nBytesRead != -1) 
        {
            try 
            {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) 
            {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
	}
	
	public void finishSound()
	{
		if (sourceLine != null)
			sourceLine.stop();
	}
}
