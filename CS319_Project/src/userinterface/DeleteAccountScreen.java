package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DeleteAccountScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;

	public DeleteAccountScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setDeleteAccountScreen( this);
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		TextField textField = new TextField();
		textField.setBounds(302, 159, 170, 22);
		add(textField);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(302, 207, 170, 22);
		add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(302, 256, 170, 22);
		add(textField_2);
		
		JLabel lblNewLabel = new JLabel("Enter your user name here");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(302, 139, 211, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter your password here");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(302, 187, 187, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter your password again");
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(302, 235, 187, 14);
		add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Delete Account");
		btnNewButton.setBounds(325, 318, 129, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go to login page");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_1){
					screenManager.openScreen("login");
				}
			}
		});
		btnNewButton_1.setBounds(26, 521, 119, 34);
		add(btnNewButton_1);
	}
}
