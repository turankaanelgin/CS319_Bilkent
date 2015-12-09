package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class HelpScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;

	public HelpScreen( ScreenManager screenManager)
	{
		super( screenManager);
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Help!");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(308, 35, 128, 68);
		add(lblNewLabel);
		
		JTextPane txtpnItIsStraightforward = new JTextPane();
		txtpnItIsStraightforward.setBackground(SystemColor.control);
		txtpnItIsStraightforward.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnItIsStraightforward.setText("It is straightforward to play and enjoyable. In the game, there is a ball sequence moving in a maze, which is composed of different colors of balls and players\u2019 purpose is to destroy the sequence by making combinations of balls with same colors. For this purpose, they control a shooter which throws the balls from the middle of the screen. If they are not able to destroy the sequence on time, it will go through a hole and they lose. Every successful trial helps players to finish the level faster and their scores will be recorded according to the times they finish the level. Sometimes a coin appears in the corner of the screen and it is a gift for players for purchasing some special items from the store of the game. at the beginning of the game, players encounter a menu which directs them to create their accounts with their usernames and passwords and their names can appear in the high score table of the game. The aim is to play all levels successfully and complete them as much as fast.");
		txtpnItIsStraightforward.setBounds(73, 134, 648, 176);
		add(txtpnItIsStraightforward);
		
		JTextPane txtpnHowToPlay = new JTextPane();
		txtpnHowToPlay.setBackground(SystemColor.control);
		txtpnHowToPlay.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHowToPlay.setText("How To Play");
		txtpnHowToPlay.setBounds(73, 321, 107, 20);
		add(txtpnHowToPlay);
		
		JTextPane txtpnPlayersUseThe = new JTextPane();
		txtpnPlayersUseThe.setBackground(SystemColor.control);
		txtpnPlayersUseThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnPlayersUseThe.setText("Players use the mouse to play the game. Mouse is dedicated to rotate the shooter and throw the balls.\r\n\r\nWhen a coin appears in the screen, players have to click on it to get it.\r\n\r\nKeyboard is also assigned for some specific purposes such as some shortcuts.");
		txtpnPlayersUseThe.setBounds(73, 352, 648, 99);
		add(txtpnPlayersUseThe);
	}
	
	public void display()
	{
		screenManager.openScreen( "help");
	}
}
