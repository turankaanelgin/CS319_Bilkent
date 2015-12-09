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

public class HighScoreScreen extends GameScreen implements Observer
{

	public HighScoreScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setHighScoreScreen(this);
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("High Scores");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(292, 55, 293, 94);
		add(lblNewLabel);
		
		JButton btnGoToMain = new JButton("Go to Main Menu");
		btnGoToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnGoToMain){
					screenManager.openScreen("main menu");
				}
			}
		});
		btnGoToMain.setBounds(29, 510, 140, 38);
		add(btnGoToMain);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		
	}

}
