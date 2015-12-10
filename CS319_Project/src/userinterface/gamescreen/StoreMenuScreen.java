package userinterface.gamescreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import gamecontrol.GameManager;
import gamecontrol.playercontrol.Player;
import gamecontrol.playercontrol.StoreItem;
import userinterface.ScreenManager;

public class StoreMenuScreen extends GameScreen implements Observer
{
	private static final long serialVersionUID = 1L;
	private JButton purchaseBomb, purchaseBack, purchaseFreeze, purchaseLive, back;
	private JLabel balanceLabel;

	public StoreMenuScreen( ScreenManager screenManager, GameManager gameManager)
	{
		super( screenManager, gameManager);
		
		purchaseBomb = new JButton( "Purchase");
		purchaseBomb.addActionListener( this);
		purchaseBomb.setForeground( new Color( 0, 102, 255));
		purchaseBomb.setBounds( 231, 86, 89, 23);
		add( purchaseBomb);
		
		purchaseBack = new JButton( "Purchase");
		purchaseBack.addActionListener( this);
		purchaseBack.setForeground( new Color( 0, 102, 255));
		purchaseBack.setBounds( 231, 194, 89, 23);
		add( purchaseBack);
		
		purchaseFreeze = new JButton( "Purchase");
		purchaseFreeze.addActionListener( this);
		purchaseFreeze.setForeground( new Color( 0, 102, 255));
		purchaseFreeze.setBounds( 231, 297, 89, 23);
		add( purchaseFreeze);
		
		purchaseLive = new JButton( "Purchase");
		purchaseLive.addActionListener( this);
		purchaseLive.setForeground( new Color( 0, 102, 255));
		purchaseLive.setBounds( 231, 390, 89, 23);
		add( purchaseLive);
		
		JLabel bombBall = new JLabel( "Bomb Ball");
		bombBall.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		bombBall.setBounds( 417, 86, 62, 14);
		add( bombBall);
		
		JLabel lblBackBall = new JLabel( "Back Ball");
		lblBackBall.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		lblBackBall.setBounds( 417, 194, 77, 14);
		add( lblBackBall);
		
		JLabel lblFreezeBall = new JLabel( "Freeze Ball");
		lblFreezeBall.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		lblFreezeBall.setBounds( 419, 297, 101, 14);
		add( lblFreezeBall);
		
		JLabel lblLife = new JLabel( "Life");
		lblLife.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		lblLife.setBounds( 417, 390, 46, 14);
		add( lblLife);
		
		JLabel lblNewLabel_1 = new JLabel( StoreItem.BOMB_BALL.getPrice() + "");
		lblNewLabel_1.setFont(new Font( "Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds( 556, 86, 46, 14);
		add( lblNewLabel_1);
		
		JLabel label_3 = new JLabel( StoreItem.BACK_BALL.getPrice() + "");
		label_3.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		label_3.setBounds( 556, 194, 46, 14);
		add( label_3);
		
		JLabel label_4 = new JLabel( StoreItem.FREEZE_BALL.getPrice() + "");
		label_4.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		label_4.setBounds( 556, 297, 46, 14);
		add( label_4);
		
		JLabel label_5 = new JLabel( StoreItem.LIVE.getPrice() + "");
		label_5.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		label_5.setBounds( 556, 390, 46, 14);
		add( label_5);
		
		back = new JButton( "Go to Main Menu");
		back.addActionListener( this);
		back.setBounds( 25, 519, 167, 37);
		add( back);
		
		JLabel lblNewLabel_2 = new JLabel( "Your Coin:");
		lblNewLabel_2.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds( 577, 33, 77, 14);
		add( lblNewLabel_2);
		
		balanceLabel = new JLabel();
		balanceLabel.setFont( new Font( "Tahoma", Font.PLAIN, 14));
		balanceLabel.setBounds( 646, 33, 62, 14);
		add( balanceLabel);
		
		JLabel lblStore = new JLabel( "Store");
		lblStore.setForeground( new Color( 153, 0, 102));
		lblStore.setFont( new Font( "Tahoma", Font.BOLD, 30));
		lblStore.setBounds( 338, 14, 101, 37);
		add( lblStore);
		
		JLabel lblNewLabel_5 = new JLabel( "bomb");
		lblNewLabel_5.setIcon( new ImageIcon( "bombball.png"));
		lblNewLabel_5.setBounds( 58, 58, 111, 70);
		add( lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel( "back");
		lblNewLabel_4.setIcon( new ImageIcon( "backball.png"));
		lblNewLabel_4.setBounds( 58, 168, 111, 70);
		add( lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel( "freeze");
		lblNewLabel_6.setIcon( new ImageIcon( "freezeball.png"));
		lblNewLabel_6.setBounds( 58, 269, 111, 67);
		add( lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel( "life");
		lblNewLabel_7.setIcon( new ImageIcon( "life.png"));
		lblNewLabel_7.setBounds( 58, 376, 111, 67);
		add( lblNewLabel_7);
	}
	
	private void displayNotEnoughMoney()
	{
		System.out.println( "Not enough money");
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		if (e.getSource() == purchaseBomb)
		{
			if (!gameManager.addPlayerItem( StoreItem.BOMB_BALL))
				displayNotEnoughMoney();
		}
		else if (e.getSource() == purchaseBack)
		{
			if (!gameManager.addPlayerItem( StoreItem.BACK_BALL))
				displayNotEnoughMoney();
		}
		else if (e.getSource() == purchaseFreeze)
		{
			if (!gameManager.addPlayerItem( StoreItem.FREEZE_BALL))
				displayNotEnoughMoney();
		}
		else if (e.getSource() == purchaseLive)
		{
			if (!gameManager.addPlayerItem( StoreItem.LIVE))
				displayNotEnoughMoney();
		}
		else if (e.getSource() == back)
			screenManager.openScreen( "main menu");
	}

	@Override
	public void update( Observable o, Object arg1)
	{
		balanceLabel.setText( ((Player) o).getBalance() + "");
	}
}
