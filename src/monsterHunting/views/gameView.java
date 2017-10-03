package monsterHunting.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import monsterHunting.common.Monster;
import monsterHunting.common.Player;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class gameView extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnFight;
	private JButton btnPotion;
	private JButton btnSpawnMonster;
	private Player playerOne;
	private Monster monsterOne;
	private Random rnd;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					gameView frame = new gameView();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gameView() {
		initComponets();
		createEvents();
		gameStart();
	}
///////////////////////////////////////////////////////////////////////////
////////////This contains all the code for creating events.	
//////////////////////////////////////////////////////////////////////////
	private void createEvents() {
		btnPotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usePotion();
			}
		});
		
		btnSpawnMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMonster();
			}
			
		});
		
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(monsterOne.getCurrentHealth() >= 1)
				{
					battleMode();
				}
				else
				{
					textArea.setText(textArea.getText() + "\n\n" + "There is no monster, you have to find someone to battle!");
				}
				
			}
		});
	}
///////////////////////////////////////////////////////////////////////////
////////////This contains all the code for creating and initializing components	
//////////////////////////////////////////////////////////////////////////
	private void initComponets() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		btnFight = new JButton("Fight");
		
		GridBagConstraints gbc_btnFight = new GridBagConstraints();
		gbc_btnFight.insets = new Insets(0, 0, 5, 5);
		gbc_btnFight.gridx = 0;
		gbc_btnFight.gridy = 0;
		contentPane.add(btnFight, gbc_btnFight);
		
		btnPotion = new JButton("Potion");
		
		GridBagConstraints gbc_btnPotion = new GridBagConstraints();
		gbc_btnPotion.insets = new Insets(0, 0, 5, 5);
		gbc_btnPotion.gridx = 0;
		gbc_btnPotion.gridy = 1;
		contentPane.add(btnPotion, gbc_btnPotion);
		
		btnSpawnMonster = new JButton("SpawnMonster");
		
		GridBagConstraints gbc_btnSpawnMonster = new GridBagConstraints();
		gbc_btnSpawnMonster.insets = new Insets(0, 0, 5, 5);
		gbc_btnSpawnMonster.gridx = 0;
		gbc_btnSpawnMonster.gridy = 2;
		contentPane.add(btnSpawnMonster, gbc_btnSpawnMonster);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 6;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	public void gameStart() {
		createPlayer();	
		createMonster();
	}
	
	public void createMonster() {
		monsterOne = new Monster();
		monsterOne.setCurrentHealth(monsterOne.getMaxHealth() + 10 * playerOne.getPlayerLevel());
		monsterOne.setStr(10 + playerOne.getPlayerLevel() * 2);
	}
	
	public void createPlayer() {
		playerOne = new Player();
		playerOne.setPlayerLevel(0);
		playerOne.setCurrentHealth(playerOne.getStartHealth());
		playerOne.setNrPotions(1);
		playerOne.setStr(10 + playerOne.getPlayerLevel() * 2);
		playerOne.setMaxHealth(playerOne.getStartHealth());
	}
	
	public void battleMode() {
		int pAttack = 0;
		int mAttack = 0;
		
		if(monsterOne.getCurrentHealth() <= 0)
		{
			battleEnd();
		}
		else
		{
			rnd = new Random();
			pAttack = rnd.nextInt(playerOne.getStr());
			
			if(pAttack == 0) 
			{
				
				textArea.setText(textArea.getText() + "\n\n" + "You stumbled and missed your attack!");
			}
			else 
			{
				monsterOne.setCurrentHealth(monsterOne.getCurrentHealth() - pAttack);
				if(monsterOne.getCurrentHealth() <= 0)
				{
					textArea.setText(textArea.getText() + "\n\n" + "You attack and did " + pAttack + " damage and killed the monster ");
					battleEnd();
				}
				else
				{
					textArea.setText(textArea.getText() + "\n\n" + "You attack and did " + pAttack + " damage and the monster have " + monsterOne.getCurrentHealth() + " health left!");
				}
				
			}
			if(monsterOne.getCurrentHealth() >= 1)
			{
				mAttack = rnd.nextInt(monsterOne.getStr());
				if(mAttack == 0)
				{
					textArea.setText(textArea.getText() + "\n" + "The monster tripped on its tail and missed you!");
				}
				else
				{
					playerOne.setCurrentHealth(playerOne.getCurrentHealth() - mAttack);
					
					if(playerOne.getCurrentHealth() <= 0)
					{
						textArea.setText(textArea.getText() + "\n" + "The monster attacked you for " + mAttack + " damage and killed you!");
						battleEnd();
					}
					else
					{
						textArea.setText(textArea.getText() + "\n" + "The monster attacked you for " + mAttack + " damage and you have " + playerOne.getCurrentHealth() + " health left!");
					}
				}
			}
		}
	}

	private void battleEnd() {
		int loot;
		if(playerOne.getCurrentHealth() <= 0)
		{
			textArea.setText(textArea.getText() + "\n\n" + "///////////////////////// \n" + "///////You died GAME OVER \n" + "/////////////////////////");
		}
		else
		{
			loot = rnd.nextInt(20);
			if(loot <= 5)
			{
				playerOne.setNrPotions(playerOne.getNrPotions() + 1);
				textArea.setText(textArea.getText() + "\n\n" + "The monster dropped a Health Potion, you now have " + playerOne.getNrPotions() + " Health Potions.");
			}
			
			playerOne.setCurrentExp(playerOne.getCurrentExp() + 10);
			levelUp();
			
		}
	}
	
	private void levelUp()
	{
		if(playerOne.getCurrentExp() >= playerOne.getExpToLevel())
		{
			playerOne.setPlayerLevel(playerOne.getPlayerLevel() + 1);
			playerOne.setStr(10 + playerOne.getPlayerLevel() * 2);
			playerOne.setMaxHealth(playerOne.getMaxHealth() + 10);
			playerOne.setCurrentHealth(playerOne.getMaxHealth());
			playerOne.setCurrentExp(0);
			textArea.setText(textArea.getText() + "\n--------------------------------------------\n You leveld up and are now level " + playerOne.getPlayerLevel());
		}
	}
	
	private void usePotion() {
		int minHealing = 8;
		int maxHealing = 20;
		int restoredHealth;
		if(playerOne.getNrPotions() <= 0)
		{
			textArea.setText(textArea.getText() + "\n\n" + "You have no potions left! Try slay some monsters to find more!");
		}
		else
		{
			restoredHealth = rnd.nextInt(maxHealing - minHealing) + minHealing;
			playerOne.setNrPotions(playerOne.getNrPotions() - 1);
			if((playerOne.getCurrentHealth() + restoredHealth) > playerOne.getMaxHealth())
			{
				textArea.setText(textArea.getText() + "\n\n" + "You used a potion to heal for " + (playerOne.getMaxHealth() - playerOne.getCurrentHealth()) + " to full health and have " + playerOne.getNrPotions() + " potions left.");
				playerOne.setCurrentHealth(playerOne.getMaxHealth());
			}
			else
			{
				playerOne.setCurrentHealth(playerOne.getCurrentHealth() + restoredHealth);
				textArea.setText(textArea.getText() + "\n\n" + "You used a potion to heal for " + restoredHealth + " and have healed to " + playerOne.getCurrentHealth() + " and have " + playerOne.getNrPotions() + " potions left!");
			}
			
			
			
		}
		
	}
}
