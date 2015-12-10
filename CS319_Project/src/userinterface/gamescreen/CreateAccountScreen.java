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

public class CreateAccountScreen extends GameScreen 
{
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel, passLabel, passLabel2;
	private JTextField nameField, passField, passField2;
	private JButton confirm, back;
	
	public CreateAccountScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		nameField = new JTextField();
		nameField.setBounds( 350, 330, 170, 30);
		add( nameField);
		
		passField = new JPasswordField();
		passField.setBounds( 350, 430, 170, 30);
		add( passField);
		
		passField2 = new JPasswordField();
		passField2.setBounds( 350, 530, 170, 30);
		add( passField2);
		
		nameLabel = new JLabel( "Enter your user name here");
		nameLabel.setForeground( new Color( 0, 0, 255));
		nameLabel.setFont( new Font( "Tahoma", Font.PLAIN, 15));
		nameLabel.setBounds( 350, 300, 210, 20);
		add( nameLabel);
		
		passLabel = new JLabel( "Enter your password here");
		passLabel.setForeground( new Color( 0, 0, 255));
		passLabel.setFont( new Font( "Tahoma", Font.PLAIN, 15));
		passLabel.setBounds( 350, 400, 210, 20);
		add( passLabel);
		
		passLabel2 = new JLabel("Enter your password again");
		passLabel2.setForeground( new Color(0, 0, 255));
		passLabel2.setFont( new Font( "Tahoma", Font.PLAIN, 15));
		passLabel2.setBounds( 350, 500, 210, 20);
		add( passLabel2);
		
		confirm = new JButton( "Create new account");
		confirm.setBounds( 350, 600, 160, 30);
		confirm.addActionListener( this);
		add( confirm);
		
		back = new JButton( "Go to login page");
		back.addActionListener( this);
		back.setBounds(350, 650, 160, 30);
		add( back);
		
		addShortcut();
	}
	
	private void addShortcut()
	{
		String createKey = "create";
		Action createAccount = new AbstractAction( createKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				createAccount();
			}
		};
		getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
				KeyStroke.getKeyStroke( KeyEvent.VK_ENTER, 0), createKey);
		getActionMap().put( createKey, createAccount);
	}
	
	private void displayInvalidInput()
	{
		JFrame invalidFrame= new JFrame();
        invalidFrame.setSize( 500, 200);
        invalidFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE);
        invalidFrame.getContentPane().setLayout( null);      
        invalidFrame.setLocationRelativeTo( null);
          
        JLabel invalidLabel = new JLabel( "Invalid Input");
        invalidLabel.setHorizontalAlignment( SwingConstants.CENTER);                        
        invalidLabel.setForeground( new Color( 255, 0, 0));
        invalidLabel.setFont( new Font( "Tahoma", Font.PLAIN, 20));
        invalidLabel.setBounds( 80, 50, 220, 50);
        invalidFrame.getContentPane().add( invalidLabel);
        invalidFrame.setVisible( true);
	}
	
	private void displayPasswordNotMatch()
	{
		JFrame invalidPassFrame= new JFrame();
        invalidPassFrame.setSize( 500, 200);
        invalidPassFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE);
        invalidPassFrame.getContentPane().setLayout( null); 
        invalidPassFrame.setLocationRelativeTo( null);
        
        JLabel invalidPassLabel = new JLabel( "Passwords Do Not Match");                
        invalidPassLabel.setHorizontalAlignment( SwingConstants.CENTER);                        
        invalidPassLabel.setForeground( new Color( 255, 0, 0));
        invalidPassLabel.setFont( new Font( "Tahoma", Font.PLAIN, 20));
        invalidPassLabel.setBounds( 80, 50, 220, 50);
        invalidPassFrame.getContentPane().add( invalidPassLabel);           
        invalidPassFrame.setVisible( true);
	}
	
	private void displayConfirmFrame( int id)
	{
		JFrame invalidPassFrame= new JFrame( "Success");
        invalidPassFrame.setSize( 500, 200);
        invalidPassFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE);
        invalidPassFrame.getContentPane().setLayout( null); 
        invalidPassFrame.setLocationRelativeTo( null);
        
        JLabel invalidPassLabel = new JLabel( "ID: " + id);                
        invalidPassLabel.setHorizontalAlignment( SwingConstants.CENTER);                        
        invalidPassLabel.setForeground( new Color( 255, 0, 0));
        invalidPassLabel.setFont( new Font( "Tahoma", Font.PLAIN, 20));
        invalidPassLabel.setBounds( 80, 50, 220, 50);
        invalidPassFrame.getContentPane().add( invalidPassLabel);           
        invalidPassFrame.setVisible( true);
	}
	
	private void createAccount()
	{
		String name = nameField.getText();
		String password = passField.getText();
		String password2 = passField2.getText();
		
		if (name.equals( "") || password.equals( "") || password2.equals( ""))
		{
			displayInvalidInput();
			return;
		}
		
		if (!password.equals( password2))
		{
			displayPasswordNotMatch();
			return;
		}
		
		int id = gameManager.addPlayer( name, password);
		displayConfirmFrame( id);
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == confirm)
			createAccount();
		else if (e.getSource() == back)
		{
			nameField.setText( "");
			passField.setText( "");
			passField2.setText( "");
			screenManager.openScreen( "login");
		}
	}
}
