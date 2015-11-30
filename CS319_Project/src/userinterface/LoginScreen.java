package userinterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gamecontrol.GameManager;

public class LoginScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	private JLabel title, idLabel, passLabel;
	private JTextField idField, passField;
	private JButton createAccount, deleteAccount, loginAsGuest, login;
	private JButton ok;
	
	public LoginScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		idLabel = new JLabel( "Enter your id: ");
		passLabel = new JLabel( "Enter your password: ");
		idField = new JTextField( 20);
		passField = new JPasswordField( 20);
		createAccount = new JButton( "Create account");
		deleteAccount = new JButton( "Delete account");
		login = new JButton( "Login");
		ok = new JButton();
		
		idLabel.setBounds( 300, 300, 150, 30);
		idLabel.setFont( new Font( "Times", Font.BOLD, 20));
		passLabel.setBounds( 300, 400, 230, 30);
		passLabel.setFont( new Font( "Times", Font.BOLD, 20));
		idField.setBounds( 550, 300, 150, 30);
		passField.setBounds( 550, 400, 150, 30);
		createAccount.setBounds( 200, 600, 150, 30);
		login.setBounds( 200, 700, 150, 30);
		ok.setBounds( 200, 800, 50, 30);
		
		add( idLabel);
		add( passLabel);
		add( idField);
		add( passField);
		add( createAccount);
		add( login);
		add( ok);
		
		createAccount.addActionListener( this);
		login.addActionListener( this);
		ok.addActionListener( this);
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == createAccount)
			screenManager.openScreen( "create account");
		else if (e.getSource() == login)
		{
			int id = Integer.parseInt( idField.getText());
			String password = passField.getText();
			
			if (gameManager.checkPlayer( id, password))
				screenManager.openScreen( "main menu");
		}
		else if (e.getSource() == ok)
			screenManager.openScreen( "main menu");
	}
}
