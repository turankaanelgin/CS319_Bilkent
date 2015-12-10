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

public class DeleteAccountScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	private JLabel idLabel, passLabel;
	private JTextField idField, passField;
	private JButton confirm, back;
	
	public DeleteAccountScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		idField = new JTextField();
		idField.setBounds( 350, 230, 170, 30);
		add( idField);
		
		passField = new JPasswordField();
		passField.setBounds( 350, 330, 170, 30);
		add( passField);
		
		idLabel = new JLabel( "Enter your id here");
		idLabel.setForeground( new Color( 0, 0, 255));
		idLabel.setFont( new Font( "Tahoma", Font.PLAIN, 15));
		idLabel.setBounds( 350, 200, 170, 20);
		add( idLabel);
		
		passLabel = new JLabel( "Enter your password here");
		passLabel.setForeground( new Color(0, 0, 255));
		passLabel.setFont( new Font( "Tahoma", Font.PLAIN, 15));
		passLabel.setBounds( 350, 300, 170, 20);
		add( passLabel);
		
		confirm = new JButton( "Delete Account");
		confirm.addActionListener( this);
		confirm.setBounds( 350, 400, 170, 30);
		add( confirm);
		
		back = new JButton( "Go to login page");
		back.addActionListener( this);
		back.setBounds( 350, 460, 170, 30);
		add( back);
		
		addShortcut();
	}

	
	private void deleteAccount()
	{
		int id;
		
		try
		{
			id = Integer.parseInt( idField.getText());
		}
		catch (NumberFormatException e)
		{
			displayInvalidInput();
			return;
		}
		
		String password = passField.getText();
		
		if (password.equals( ""))
		{
			displayInvalidInput();
			return;
		}
		
		if (!gameManager.removePlayer( id, password))
			displayInvalidAccount();
		else
			displaySuccess();
	}
	
	private void displaySuccess()
	{
		JFrame	successFrame= new JFrame();
		successFrame.setSize( 500, 200);
		successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		successFrame.getContentPane().setLayout(null);
		successFrame.setLocationRelativeTo( null);
        
        JLabel successLabel = new JLabel("Account is removed");
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);
        successLabel.setForeground(new Color(255, 0, 0));
        successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        successLabel.setBounds(80, 50, 220, 50);
        
        successFrame.getContentPane().add(successLabel);
        successFrame.setVisible(true);
	}
	
	private void displayInvalidAccount()
	{
		JFrame invalidAccFrame= new JFrame();
        invalidAccFrame.setBounds(100, 100, 400, 200);
        invalidAccFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invalidAccFrame.getContentPane().setLayout(null);
        
        JLabel invalidAccLabel = new JLabel("Invalid Account");
        invalidAccLabel.setHorizontalAlignment(SwingConstants.CENTER);
        invalidAccLabel.setForeground(new Color(255, 0, 0));
        invalidAccLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        invalidAccLabel.setBounds(80, 50, 220, 50);
        invalidAccFrame.getContentPane().add(invalidAccLabel);
        invalidAccFrame.setVisible(true);
	}
	
	private void displayInvalidInput()
	{
        JFrame invalidFrame= new JFrame();
        invalidFrame.setBounds(100, 100, 400, 200);
        invalidFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invalidFrame.getContentPane().setLayout(null);
        
        JLabel invalidLabel = new JLabel("Invalid input");
        invalidLabel.setHorizontalAlignment(SwingConstants.CENTER);
        invalidLabel.setForeground(new Color(255, 0, 0));
        invalidLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        invalidLabel.setBounds(80, 50, 220, 50);
        invalidFrame.getContentPane().add(invalidLabel);
        
        invalidFrame.setVisible(true);
	}
	
	private void addShortcut()
	{
		String deleteAccountKey = "delete";
		Action deleteAccount = new AbstractAction( deleteAccountKey) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				deleteAccount();
			}
		};
		getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW).put( 
					 KeyStroke.getKeyStroke( KeyEvent.VK_ENTER, 0), deleteAccountKey);
		getActionMap().put( deleteAccountKey, deleteAccount);
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == confirm)
			deleteAccount();
		else if (e.getSource() == back)
		{
			idField.setText( "");
			passField.setText( "");
			screenManager.openScreen( "login");
		}
	}
}
