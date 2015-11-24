package userinterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends GameScreen implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JLabel title, idLabel, passLabel;
	private JTextField idField, passField;
	private JButton createAccount, deleteAccount, loginAsGuest;
	
	public LoginScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setLoginScreen( this);
		
		idLabel = new JLabel( "Enter your id: ");
		passLabel = new JLabel( "Enter your password: ");
		idField = new JTextField( 20);
		passField = new JPasswordField( 20);
		createAccount = new JButton( "Create account");
		deleteAccount = new JButton( "Delete account");
		
		idLabel.setBounds( 300, 300, 150, 30);
		idLabel.setFont( new Font( "Times", Font.BOLD, 20));
		passLabel.setBounds( 300, 400, 230, 30);
		passLabel.setFont( new Font( "Times", Font.BOLD, 20));
		idField.setBounds( 550, 300, 150, 30);
		passField.setBounds( 550, 400, 150, 30);
		createAccount.setBounds( 200, 600, 150, 30);
		
		add( idLabel);
		add( passLabel);
		add( idField);
		add( passField);
		add( createAccount);
		
		createAccount.addActionListener( this);
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == createAccount)
			screenManager.openScreen( "create account");
	}
}
