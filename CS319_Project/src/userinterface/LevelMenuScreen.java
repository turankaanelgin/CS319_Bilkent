package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LevelMenuScreen extends GameScreen implements Observer
{

	public LevelMenuScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setLevelMenuScreen( this);
		
		setLayout(null);
		
		JButton btnNewButton = new JButton("Go to Main Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton){
					screenManager.openScreen("main menu");
				}
			}
		});
		btnNewButton.setBounds(32, 516, 136, 31);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Level 1");
		btnNewButton_1.setBounds(66, 198, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Level 2");
		btnNewButton_2.setBounds(203, 198, 89, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Level 3");
		btnNewButton_3.setBounds(337, 198, 89, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Level 4");
		btnNewButton_4.setBounds(474, 198, 89, 23);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Level 5");
		btnNewButton_5.setBounds(613, 198, 89, 23);
		add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Level 6");
		btnNewButton_6.setBounds(66, 292, 89, 23);
		add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Level 7");
		btnNewButton_7.setBounds(203, 292, 89, 23);
		add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Level 8");
		btnNewButton_8.setBounds(337, 292, 89, 23);
		add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Level 9");
		btnNewButton_9.setBounds(474, 292, 89, 23);
		add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Level 10");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_10.setBounds(613, 292, 89, 23);
		add(btnNewButton_10);
		
		JLabel lblChooseALevel = new JLabel("Choose a Level");
		lblChooseALevel.setForeground(new Color(255, 102, 0));
		lblChooseALevel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblChooseALevel.setBounds(284, 53, 218, 52);
		add(lblChooseALevel);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		
	}

}

