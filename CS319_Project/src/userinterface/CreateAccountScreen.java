package userinterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gamecontrol.GameManager;

public class CreateAccountScreen extends GameScreen 
{
	private static final long serialVersionUID = 1L;
	private ShortcutListener shortcutListener;
	private JLabel nameLabel;
	private JTextField nameField;
	private JPasswordField passField;
	private JButton confirm, back;
	
	public CreateAccountScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		gameManager.setCreateAccountScreen( this);
		
		nameLabel = new JLabel( "Enter username: ");
		nameLabel.setBounds( 200, 200, 150, 30);
		nameLabel.setFont( new Font( "Times", Font.BOLD, 16));
		
		nameField = new JTextField( 10);
		nameField.setBounds( 400, 200, 100, 30);
		passField = new JPasswordField( 10);
		passField.setBounds( 400, 270, 100, 30);
		
		confirm = new JButton( "Confirm");
		confirm.setBounds( 400, 330, 100, 30);
		confirm.addActionListener( this);
		back = new JButton( "<");
		back.setBounds( 50, 50, 50, 50);
		back.addActionListener( this);
		
		add( nameLabel);
		add( nameField);
		add( passField);
		add( confirm);
		add( back);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == confirm)
		{
			String name, password;
			
			name = nameField.getText();
			password = passField.getText();
			
			gameManager.addPlayer( name, password);
		}
		else if (e.getSource() == back)
			screenManager.openScreen( "login");
	}
}
