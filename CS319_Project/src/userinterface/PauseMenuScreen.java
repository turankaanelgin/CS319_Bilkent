package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PauseMenuScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;

	public PauseMenuScreen( ScreenManager screenManager)
	{
		super( screenManager);
		
		setLayout(null);
		
		JButton btnNewButton = new JButton("Resume");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(296, 188, 128, 54);
		add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Go to Main Menu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()== btnNewButton_2)
					screenManager.openScreen("main menu");
			}
		});
		btnNewButton_2.setBounds(296, 282, 128, 46);
		add(btnNewButton_2);
		
		JLabel lblPause = new JLabel("Pause");
		lblPause.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblPause.setForeground(Color.RED);
		lblPause.setBounds(293, 76, 155, 51);
		add(lblPause);
		
		JCheckBox chckbxSoundOn = new JCheckBox("Sound On");
		chckbxSoundOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		chckbxSoundOn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSoundOn.setBounds(296, 365, 128, 23);
		add(chckbxSoundOn);
		
		JCheckBox chckbxSoundOff = new JCheckBox("Sound Off");
		chckbxSoundOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chckbxSoundOff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSoundOff.setBounds(296, 409, 128, 23);
		add(chckbxSoundOff);
	}
	
	public void display()
	{
		screenManager.openScreen( "pause");
	}
}
