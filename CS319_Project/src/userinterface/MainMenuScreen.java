
package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenuScreen extends GameScreen
{

	public MainMenuScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setMainMenuScreen( this);
		
		
		setLayout(null);
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton){
					screenManager.openScreen( "level menu");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(321, 193, 127, 32);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Store");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_1){
					screenManager.openScreen("store menu");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(321, 248, 127, 32);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View High Score");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_2){
					screenManager.openScreen("high score");
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBounds(321, 306, 127, 37);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Go to Login Page");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_3){
					screenManager.openScreen("login");
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_3.setBounds(36, 506, 152, 32);
		add(btnNewButton_3);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setForeground(new Color(139, 0, 0));
		lblMainMenu.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblMainMenu.setBounds(276, 95, 229, 56);
		add(lblMainMenu);
	}

}

