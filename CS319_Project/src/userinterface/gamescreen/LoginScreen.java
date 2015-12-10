package userinterface.gamescreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import gamecontrol.GameManager;
import userinterface.ScreenManager;

public class LoginScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	private JLabel title, idLabel, passLabel;
	private JTextField idField, passField;
	private JButton createAccount, deleteAccount, loginAsGuest, login;
	
	public LoginScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		title = new JLabel( "CRAZY SHOOTER");
		idLabel = new JLabel( "Enter your id: ");
		passLabel = new JLabel( "Enter your password: ");
		idField = new JTextField( 20);
		passField = new JPasswordField( 20);
		createAccount = new JButton( "Create account");
		deleteAccount = new JButton( "Delete account");
		login = new JButton( "Login");
		loginAsGuest = new JButton( "Login as guest");
		
		title.setBounds( 350, 100, 300, 50);
		title.setFont( new Font( "Times", Font.BOLD, 30));
		idLabel.setBounds( 300, 300, 150, 30);
		idLabel.setFont( new Font( "Times", Font.BOLD, 20));
		passLabel.setBounds( 300, 400, 230, 30);
		passLabel.setFont( new Font( "Times", Font.BOLD, 20));
		idField.setBounds( 550, 300, 150, 30);
		passField.setBounds( 550, 400, 150, 30);
		login.setBounds( 300, 600, 150, 30);
		loginAsGuest.setBounds( 500, 600, 150, 30);
		createAccount.setBounds( 300, 700, 150, 30);
		deleteAccount.setBounds( 500, 700, 150, 30);
		
		add( title);
		add( idLabel);
		add( passLabel);
		add( idField);
		add( passField);
		add( login);
		add( loginAsGuest);
		add( createAccount);
		add( deleteAccount);
		
		login.addActionListener( this);
		loginAsGuest.addActionListener( this);
		createAccount.addActionListener( this);
		deleteAccount.addActionListener( this);
		
		addShortcut();
	}
	
	private void displayInvalidInput()
	{
		JFrame invalidFrame= new JFrame();
        invalidFrame.setSize( 500, 300);
        invalidFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invalidFrame.getContentPane().setLayout(null);
        invalidFrame.setLocationRelativeTo( null);
        
        JLabel invalidLabel = new JLabel("Invalid Input");
        invalidLabel.setHorizontalAlignment(SwingConstants.CENTER);
        invalidLabel.setForeground(new Color(255, 0, 0));
        invalidLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        invalidLabel.setBounds(80, 50, 220, 50);
        invalidFrame.getContentPane().add(invalidLabel);
        invalidFrame.setVisible(true);
	}
	
	private void displayInvalidPlayer()
	{
		JFrame invalidPlayerFrame= new JFrame();
        invalidPlayerFrame.setSize( 500, 300);
        invalidPlayerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invalidPlayerFrame.getContentPane().setLayout(null);
        invalidPlayerFrame.setLocationRelativeTo( null);
        
        JLabel invalidPlayerLabel = new JLabel("Invalid Player");
        invalidPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        invalidPlayerLabel.setForeground(new Color(255, 0, 0));
        invalidPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        invalidPlayerLabel.setBounds(80, 50, 220, 50);
        invalidPlayerFrame.getContentPane().add(invalidPlayerLabel);
        invalidPlayerFrame.setVisible(true);
	}
	
	private void addShortcut()
	{
		String loginKey = "login";
		Action login = new AbstractAction( loginKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				login();
			}
		};
		getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
				KeyStroke.getKeyStroke( KeyEvent.VK_ENTER, 0), loginKey);
		getActionMap().put( loginKey, login);
	}
	
	private void login()
	{
		int id;
		
		try
		{
			id = Integer.parseInt( idField.getText());
		}
		catch (NumberFormatException e1)
		{
			displayInvalidInput();
			return;
		}
		
		String password = passField.getText();
			
		if (gameManager.loadPlayer( id, password))
		{
			screenManager.openScreen( "main menu");
			initialize();
		}
		else
			displayInvalidPlayer();
	}
	
	private void loginAsGuest()
	{
		screenManager.openScreen( "level");
		gameManager.startLevel( 1, screenManager.getLevelScreen(), true);
		initialize();
	}
	
	private void initialize()
	{
		idField.setText( "");
		passField.setText( "");
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == login)
			login();
		else if (e.getSource() == loginAsGuest)
			loginAsGuest();
		else if (e.getSource() == createAccount)
		{
			screenManager.openScreen( "create account");
			initialize();
		}
		else if (e.getSource() == deleteAccount)
		{
			screenManager.openScreen( "delete account");
			initialize();
		}
	}
}
