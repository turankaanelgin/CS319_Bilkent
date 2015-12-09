package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginScreen extends GameScreen implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JLabel title, idLabel, passLabel;
	private JTextField idField, passField;
	private JButton createAccount, deleteAccount, loginAsGuest;
	
	public LoginScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setLoginScreen( this);
		
		/*idLabel = new JLabel( "Enter your id: ");
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
		
		createAccount.addActionListener( this);*/
		
		//frame = new JFrame();
		setBounds(100, 100, 690, 566);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.setBounds(397, 22, 121, 23);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton){
					screenManager.openScreen( "create account");
				}
			}
		}
		);
		add(btnNewButton);
		setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(169, 16, 1, 1);
		add(layeredPane);
		add(btnNewButton);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnDeleteAccount){
					screenManager.openScreen( "delete account");
				}
			}
		});
		btnDeleteAccount.setBounds(547, 22, 117, 23);
		add(btnDeleteAccount);
		
		TextField textField = new TextField();
		textField.setBounds(199, 208, 194, 22);
		add(textField);
		
		JLabel lblCrazyShooter = new JLabel("Crazy Shooter");
		lblCrazyShooter.setForeground(new Color(255, 0, 0));
		lblCrazyShooter.setFont(new Font("Times New Roman", Font.PLAIN, 47));
		lblCrazyShooter.setBounds(186, 108, 281, 52);
		add(lblCrazyShooter);
		
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(199, 257, 194, 22);
		add(textField_1);
		
		JLabel lblEnterYourId = new JLabel("Enter your id here");
		lblEnterYourId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterYourId.setBounds(199, 188, 139, 14);
		add(lblEnterYourId);
		
		JLabel lblEnterYourPassword = new JLabel("Enter your password here");
		lblEnterYourPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterYourPassword.setBounds(199, 237, 194, 14);
		add(lblEnterYourPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnLogin){
					screenManager.openScreen( "main menu");
				}
			}
		});
		btnLogin.setBounds(249, 327, 89, 23);
		add(btnLogin);
		
		JButton btnLoginAsGuest = new JButton("Login as Guest");
		btnLoginAsGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnLoginAsGuest){
					screenManager.openScreen( "main menu");
				}
			}
		});


		btnLoginAsGuest.setBounds(249, 423, 121, 23);
		add(btnLoginAsGuest);
		//frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == createAccount)
			screenManager.openScreen( "create account");
	}
}

