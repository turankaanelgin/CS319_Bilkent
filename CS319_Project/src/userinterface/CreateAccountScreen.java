
package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CreateAccountScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	private ShortcutListener shortcutListener;
	private JLabel nameLabel;
	
	public CreateAccountScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setCreateAccountScreen( this);
		
		/*nameLabel = new JLabel( "Enter username: ");
		nameLabel.setBounds( 200, 200, 150, 30);
		nameLabel.setFont( new Font( "Times", Font.BOLD, 16));
		add( nameLabel);*/
		
		
		//setBounds(150, 150, 780, 700);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		TextField textField = new TextField();
		textField.setBounds(287, 141, 170, 22);
		add(textField);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(287, 200, 170, 22);
		add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(287, 256, 170, 22);
		add(textField_2);
		
		JLabel lblNewLabel = new JLabel("Enter your user name here");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(270, 121, 211, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter your password here");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(287, 180, 187, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter your password again");
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(287, 236, 187, 14);
		add(lblNewLabel_2);

		
		JButton btnNewButton = new JButton("Create new account");
		btnNewButton.setBounds(305, 324, 129, 23);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame invalidPlayerFrame= new JFrame();
				invalidPlayerFrame.setBounds(100, 100, 400, 200);
				invalidPlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				invalidPlayerFrame.getContentPane().setLayout(null);
				
				JLabel invalidPlayerLabel = new JLabel("Invalid Player");
				invalidPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
				invalidPlayerLabel.setForeground(new Color(255, 0, 0));
				invalidPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				invalidPlayerLabel.setBounds(100, 50, 220, 50);
				invalidPlayerFrame.getContentPane().add(invalidPlayerLabel);
				
				invalidPlayerFrame.setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Go to login page");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_1){
					screenManager.openScreen("login");
				}
			}
		});
		btnNewButton_1.setBounds(30, 509, 157, 31);
		add(btnNewButton_1);
		
	}
	
	public void returnToLoginScreen()
	{
		
	}
}
