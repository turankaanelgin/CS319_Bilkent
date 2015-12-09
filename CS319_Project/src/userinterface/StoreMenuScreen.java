package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StoreMenuScreen extends GameScreen
{
	private static final long serialVersionUID = 1L;

	public StoreMenuScreen( ScreenManager screenManager)
	{
		super( screenManager);
		gameManager.setStoreMenuScreen(this);
		
		setLayout(null);
		
		JButton btnNewButton = new JButton("Purchase");
		btnNewButton.setForeground(new Color(0, 102, 255));
		btnNewButton.setBounds(231, 82, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Purchase");
		btnNewButton_1.setForeground(new Color(0, 102, 255));
		btnNewButton_1.setBounds(231, 190, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Purchase");
		btnNewButton_2.setForeground(new Color(0, 102, 255));
		btnNewButton_2.setBounds(231, 293, 89, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Purchase");
		btnNewButton_3.setForeground(new Color(0, 102, 255));
		btnNewButton_3.setBounds(231, 386, 89, 23);
		add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Bomb Ball");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(417, 86, 62, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("50");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(556, 86, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblBackBall = new JLabel("Back Ball");
		lblBackBall.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBackBall.setBounds(417, 194, 77, 14);
		add(lblBackBall);
		
		JLabel lblFreezeBall = new JLabel("Freeze Ball");
		lblFreezeBall.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFreezeBall.setBounds(419, 297, 101, 14);
		add(lblFreezeBall);
		
		JLabel lblLife = new JLabel("Life");
		lblLife.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLife.setBounds(417, 390, 46, 14);
		add(lblLife);
		
		JLabel label_3 = new JLabel("70");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(556, 194, 46, 14);
		add(label_3);
		
		JLabel label_4 = new JLabel("90");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(556, 297, 46, 14);
		add(label_4);
		
		JLabel label_5 = new JLabel("200");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(556, 390, 46, 14);
		add(label_5);
		
		JButton btnNewButton_4 = new JButton("Go to Main Menu");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_4){
					screenManager.openScreen("main menu");
				}
			}
		});
		btnNewButton_4.setBounds(25, 519, 167, 37);
		add(btnNewButton_4);
		
		JLabel lblNewLabel_2 = new JLabel("Your Coin:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(577, 33, 77, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(646, 33, 62, 14);
		add(lblNewLabel_3);
		
		JLabel lblStore = new JLabel("Store");
		lblStore.setForeground(new Color(153, 0, 102));
		lblStore.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblStore.setBounds(338, 14, 101, 37);
		add(lblStore);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 58, 46, 14);
		add(label);
			
		
		JLabel lblNewLabel_4 = new JLabel("back");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Beng\u00FC\\workspace\\GUIdeneme\\backball.PNG"));
		lblNewLabel_4.setBounds(58, 269, 111, 70);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("bomb");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Beng\u00FC\\workspace\\GUIdeneme\\bombball.PNG"));
		lblNewLabel_5.setBounds(58, 58, 111, 70);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("freeze");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\Beng\u00FC\\workspace\\GUIdeneme\\freezeball.PNG"));
		lblNewLabel_6.setBounds(58, 168, 111, 67);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("life");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\Beng\u00FC\\workspace\\GUIdeneme\\life.PNG"));
		lblNewLabel_7.setBounds(58, 376, 111, 67);
		add(lblNewLabel_7);
	}
}
