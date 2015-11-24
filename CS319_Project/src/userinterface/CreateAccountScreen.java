package userinterface;

import java.awt.Font;

import javax.swing.JLabel;

public class CreateAccountScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;
	private ShortcutListener shortcutListener;
	private JLabel nameLabel;
	
	public CreateAccountScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setCreateAccountScreen( this);
		
		nameLabel = new JLabel( "Enter username: ");
		nameLabel.setBounds( 200, 200, 150, 30);
		nameLabel.setFont( new Font( "Times", Font.BOLD, 16));
		add( nameLabel);
	}
	
	public void returnToLoginScreen()
	{
		
	}
}
