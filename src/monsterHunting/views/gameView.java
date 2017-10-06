package monsterHunting.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import monsterHunting.common.Monster;
import monsterHunting.common.Player;
import monsterHunting.common.Spells;

import javax.swing.JTextPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class gameView extends JFrame {

	private JPanel contentPane;
	private JTextArea txtAreaMain;
	private JButton btnFight;
	private JButton btnPotion;
	private JButton btnSpawnMonster;
	private Player playerOne;
	private Monster monsterOne;
	private Random rnd;
	private JScrollPane scrollPane;
	private JLabel lblMonsterHp;
	private JLabel lblMonsterTyp;
	private JLabel lblPlayerLevel;
	private JLabel lblPlayerPotions;
	private JLabel lblPlayerHealth;
	private JComboBox<String> cboxSpells;
	private JLabel lblPlayerMana;
	private JButton btnCastSpell;
	private JLabel lblPlayerImage;
	private JLabel lblMonsterImage;
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
					txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "There is no monster, you have to find someone to battle!");
				}
				
			}
		});
		
		btnCastSpell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				castingSpell();
			}
		});

	}
///////////////////////////////////////////////////////////////////////////
////////////This contains all the code for creating and initializing components	
//////////////////////////////////////////////////////////////////////////
	private void initComponets() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		btnFight = new JButton("Fight");
		
		GridBagConstraints gbc_btnFight = new GridBagConstraints();
		gbc_btnFight.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFight.insets = new Insets(0, 0, 5, 5);
		gbc_btnFight.gridx = 0;
		gbc_btnFight.gridy = 0;
		contentPane.add(btnFight, gbc_btnFight);
		
		lblPlayerLevel = new JLabel("New label");
		GridBagConstraints gbc_lblPlayerLevel = new GridBagConstraints();
		gbc_lblPlayerLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerLevel.gridx = 2;
		gbc_lblPlayerLevel.gridy = 0;
		contentPane.add(lblPlayerLevel, gbc_lblPlayerLevel);
		
		lblMonsterTyp = new JLabel("New label");
		GridBagConstraints gbc_lblMonsterTyp = new GridBagConstraints();
		gbc_lblMonsterTyp.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonsterTyp.gridx = 7;
		gbc_lblMonsterTyp.gridy = 0;
		contentPane.add(lblMonsterTyp, gbc_lblMonsterTyp);
		
		btnPotion = new JButton("Potion");
		
		GridBagConstraints gbc_btnPotion = new GridBagConstraints();
		gbc_btnPotion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPotion.insets = new Insets(0, 0, 5, 5);
		gbc_btnPotion.gridx = 0;
		gbc_btnPotion.gridy = 1;
		contentPane.add(btnPotion, gbc_btnPotion);
		
		lblPlayerHealth = new JLabel("New label");
		GridBagConstraints gbc_lblPlayerHealth = new GridBagConstraints();
		gbc_lblPlayerHealth.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerHealth.gridx = 2;
		gbc_lblPlayerHealth.gridy = 1;
		contentPane.add(lblPlayerHealth, gbc_lblPlayerHealth);
		
		lblMonsterHp = new JLabel("New label");
		GridBagConstraints gbc_lblMonsterHp = new GridBagConstraints();
		gbc_lblMonsterHp.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonsterHp.gridx = 7;
		gbc_lblMonsterHp.gridy = 1;
		contentPane.add(lblMonsterHp, gbc_lblMonsterHp);
		
		btnSpawnMonster = new JButton("SpawnMonster");
		
		GridBagConstraints gbc_btnSpawnMonster = new GridBagConstraints();
		gbc_btnSpawnMonster.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSpawnMonster.insets = new Insets(0, 0, 5, 5);
		gbc_btnSpawnMonster.gridx = 0;
		gbc_btnSpawnMonster.gridy = 2;
		contentPane.add(btnSpawnMonster, gbc_btnSpawnMonster);
		
		lblPlayerMana = new JLabel("New label");
		GridBagConstraints gbc_lblPlayerMana = new GridBagConstraints();
		gbc_lblPlayerMana.anchor = GridBagConstraints.WEST;
		gbc_lblPlayerMana.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerMana.gridx = 2;
		gbc_lblPlayerMana.gridy = 2;
		contentPane.add(lblPlayerMana, gbc_lblPlayerMana);
		
		cboxSpells = new JComboBox<String>();
		GridBagConstraints gbc_cboxSpells = new GridBagConstraints();
		gbc_cboxSpells.insets = new Insets(0, 0, 5, 5);
		gbc_cboxSpells.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxSpells.gridx = 0;
		gbc_cboxSpells.gridy = 3;
		contentPane.add(cboxSpells, gbc_cboxSpells);
		
		lblPlayerPotions = new JLabel("New label");
		GridBagConstraints gbc_lblPlayerPotions = new GridBagConstraints();
		gbc_lblPlayerPotions.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerPotions.gridx = 2;
		gbc_lblPlayerPotions.gridy = 3;
		contentPane.add(lblPlayerPotions, gbc_lblPlayerPotions);
		
		btnCastSpell = new JButton("Cast Spell");
		
				GridBagConstraints gbc_btnCastSpell = new GridBagConstraints();
				gbc_btnCastSpell.insets = new Insets(0, 0, 5, 5);
				gbc_btnCastSpell.gridx = 0;
				gbc_btnCastSpell.gridy = 4;
				contentPane.add(btnCastSpell, gbc_btnCastSpell);
		
		lblPlayerImage = new JLabel("");
		GridBagConstraints gbc_lblPlayerImage = new GridBagConstraints();
		gbc_lblPlayerImage.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlayerImage.gridx = 0;
		gbc_lblPlayerImage.gridy = 5;
		contentPane.add(lblPlayerImage, gbc_lblPlayerImage);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		txtAreaMain = new JTextArea();
		scrollPane.setViewportView(txtAreaMain);
		
		lblMonsterImage = new JLabel("");
		GridBagConstraints gbc_lblMonsterImage = new GridBagConstraints();
		gbc_lblMonsterImage.insets = new Insets(0, 0, 0, 5);
		gbc_lblMonsterImage.gridx = 7;
		gbc_lblMonsterImage.gridy = 5;
		contentPane.add(lblMonsterImage, gbc_lblMonsterImage);
	}
	
	public void gameStart() {
		createPlayer();	
		createMonster();
		generateSpellList();
		//Image image = new ImageIcon(this.getClass().getResource("")).getImage();
		//ImageIcon icon = new ImageIcon(image);
		//lblMonster.setIcon(icon);
	}
	
	public void createMonster() {
		rnd = new Random();
		int chance = rnd.nextInt(10);
		String imagePath = "";
		monsterOne = new Monster();
		if(chance <= 4)			
		{
			monsterOne.setMonsterTyp("Zombie");
			monsterOne.setExpGiven(10);
			monsterOne.setMaxHealth(20 + 10 * playerOne.getPlayerLevel());
			monsterOne.setCurrentHealth(monsterOne.getMaxHealth());
			monsterOne.setStr(10 + playerOne.getPlayerLevel() * 2);
			monsterOne.setDodgeChance(1);
			imagePath = "C:\\Users\\IBM Jimmy Saari\\eclipse-workspace\\MonsterHunting\\src\\monsterHunting\\resources\\zombieFemale.png";
		}
		else if(chance >= 6)
		{
			monsterOne.setMonsterTyp("Bat");
			monsterOne.setMaxHealth(10 + 10 * playerOne.getPlayerLevel());
			monsterOne.setExpGiven(5);
			monsterOne.setCurrentHealth(monsterOne.getMaxHealth());
			monsterOne.setStr(7 + playerOne.getPlayerLevel() * 2);
			monsterOne.setDodgeChance(5);
			imagePath = "C:\\Users\\IBM Jimmy Saari\\eclipse-workspace\\MonsterHunting\\src\\monsterHunting\\resources\\bat.png";
		}
		else
		{
			monsterOne.setMonsterTyp("Ogre");
			monsterOne.setMaxHealth(40 + 10 * playerOne.getPlayerLevel());
			monsterOne.setExpGiven(30);
			monsterOne.setCurrentHealth(monsterOne.getMaxHealth());
			monsterOne.setStr(15 + playerOne.getPlayerLevel() * 2);
			monsterOne.setDodgeChance(0);
			imagePath = "C:\\Users\\IBM Jimmy Saari\\eclipse-workspace\\MonsterHunting\\src\\monsterHunting\\resources\\ogre.jpg";
		}
		
		lblMonsterTyp.setText("MonsterType: " + monsterOne.getMonsterTyp());
		lblMonsterHp.setText("HP: " + Integer.toString(monsterOne.getCurrentHealth()));
		txtAreaMain.setText("A " + monsterOne.getMonsterTyp() + " appeard! Be carefull hero!");
		monsterImage(imagePath);
		
	}
	
	public void createPlayer() {
		playerOne = new Player();
		playerOne.setPlayerLevel(0);
		playerOne.setCurrentHealth(playerOne.getStartHealth());
		playerOne.setNrPotions(1);
		playerOne.setStr(10 + playerOne.getPlayerLevel() * 2);
		playerOne.setMaxHealth(playerOne.getStartHealth());
		playerOne.setCurrentMana(playerOne.getMaxMana());
		
		Spells fireball = new Spells();
		fireball.setName("fireball");
		fireball.setMaxDamage(25);
		fireball.setMinDamage(8);
		fireball.setManaCost(7);
		Spells thunder = new Spells();
		thunder.setName("thunder");
		thunder.setMaxDamage(18);
		thunder.setMinDamage(12);
		thunder.setManaCost(4);
		
		HashMap<String, Spells> spellMap = new HashMap<String, Spells>();
		spellMap.put(fireball.getName(), fireball);
		spellMap.put(thunder.getName(), thunder);
		playerOne.setSpellMap(spellMap);
		
		lblPlayerLevel.setText("Player level: " + playerOne.getPlayerLevel());
		lblPlayerHealth.setText("Health: " + playerOne.getCurrentHealth() + "/" + playerOne.getMaxHealth());
		lblPlayerPotions.setText("Potions: " + playerOne.getNrPotions());
		lblPlayerMana.setText("Mana: " + playerOne.getCurrentMana() + "/" + playerOne.getMaxMana());
		playerImage();
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
			
			if(pAttack < monsterOne.getDodgeChance()) 
			{
				
				txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "You stumbled and missed your attack!");
			}
			else 
			{
				monsterOne.setCurrentHealth(monsterOne.getCurrentHealth() - pAttack);
				if(monsterOne.getCurrentHealth() <= 0)
				{
					txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "You attack and did " + pAttack + " damage and killed the monster and gained ");
					battleEnd();
					lblMonsterHp.setText("You won! The monster is dead!");
				}
				else
				{
					txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "You attack and did " + pAttack + " damage and the monster have " + monsterOne.getCurrentHealth() + " health left!");
					
					lblMonsterHp.setText("HP: " + monsterOne.getCurrentHealth() + "/" + monsterOne.getMaxHealth());
				}
				
			}
			if(monsterOne.getCurrentHealth() >= 1)
			{
				mAttack = rnd.nextInt(monsterOne.getStr());
				if(mAttack == 0)
				{
					txtAreaMain.setText(txtAreaMain.getText() + "\n" + "The monster tripped on its tail and missed you!");
				}
				else
				{
					playerOne.setCurrentHealth(playerOne.getCurrentHealth() - mAttack);
					
					if(playerOne.getCurrentHealth() <= 0)
					{
						txtAreaMain.setText(txtAreaMain.getText() + "\n" + "The monster attacked you for " + mAttack + " damage and killed you!");
						battleEnd();
						lblPlayerHealth.setText("Health: " + "0" + "/" + playerOne.getMaxHealth());
					}
					else
					{
						txtAreaMain.setText(txtAreaMain.getText() + "\n" + "The monster attacked you for " + mAttack + " damage and you have " + playerOne.getCurrentHealth() + " health left!");
						lblPlayerHealth.setText("Health: " + playerOne.getCurrentHealth() + "/" + playerOne.getMaxHealth());
					}
				}
			}
		}
	}

	private void battleEnd() {
		int loot;
		if(playerOne.getCurrentHealth() <= 0)
		{
			txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "///////////////////////// \n" + "///////You died GAME OVER \n" + "/////////////////////////");
		}
		else
		{
			txtAreaMain.setText(txtAreaMain.getText() +  monsterOne.getExpGiven() + " Exp!");
			loot = rnd.nextInt(20);
			if(loot <= 5)
			{
				playerOne.setNrPotions(playerOne.getNrPotions() + 1);
				txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "The monster dropped a Health Potion, you now have " + playerOne.getNrPotions() + " Health Potions.");
				lblPlayerPotions.setText("Potions: " + playerOne.getNrPotions());
			}
			
			playerOne.setCurrentExp(playerOne.getCurrentExp() + monsterOne.getExpGiven());
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
			txtAreaMain.setText(txtAreaMain.getText() + "\n--------------------------------------------\n You leveld up and are now level " + playerOne.getPlayerLevel());
			lblPlayerHealth.setText("Health: " + playerOne.getCurrentHealth() + "/" + playerOne.getMaxHealth());
			lblPlayerLevel.setText("Player Level: " + playerOne.getPlayerLevel());
			
		}
	}
	
	private void usePotion() {
		int minHealing = (int) Math.ceil(playerOne.getMaxHealth() * 0.3);
		
		int maxHealing = (int) Math.ceil((playerOne.getMaxHealth() * 0.8));
		
		int restoredHealth;
		if(playerOne.getNrPotions() <= 0)
		{
			txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "You have no potions left! Try slay some monsters to find more!");
		}
		else
		{
			restoredHealth = rnd.nextInt(maxHealing - minHealing) + minHealing;
			playerOne.setNrPotions(playerOne.getNrPotions() - 1);
			lblPlayerPotions.setText("Potions: " + playerOne.getNrPotions());
			if((playerOne.getCurrentHealth() + restoredHealth) > playerOne.getMaxHealth())
			{
				txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "You used a potion to heal for " + (playerOne.getMaxHealth() - playerOne.getCurrentHealth()) + " to full health and have " + playerOne.getNrPotions() + " potions left.");
				playerOne.setCurrentHealth(playerOne.getMaxHealth());
			}
			else
			{
				playerOne.setCurrentHealth(playerOne.getCurrentHealth() + restoredHealth);
				txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "You used a potion to heal for " + restoredHealth + " and have healed to " + playerOne.getCurrentHealth() + " and have " + playerOne.getNrPotions() + " potions left!");
			}
			lblPlayerHealth.setText("Health: " + playerOne.getCurrentHealth() + "/" + playerOne.getMaxHealth());
			
			
		}
		
	}
	
	private void generateSpellList()
	{
		for(Map.Entry<String, Spells> select : playerOne.getSpellMap().entrySet())
		{
			
			cboxSpells.addItem(select.getValue().toString());
		}
	}
	
	private void castingSpell()
	{
		
		if(monsterOne.getCurrentHealth() <= 0)
		{
			battleEnd();
		}
		else
		{			
			if(playerOne.getCurrentMana() >= playerOne.getSpellMap().get(cboxSpells.getSelectedItem().toString()).getManaCost())
			{
				String spellName = playerOne.getSpellMap().get(cboxSpells.getSelectedItem().toString()).getName().toUpperCase();
				String resistedSpell = " ate your ";
				int resist = rnd.nextInt(10);
				int maxDamage = playerOne.getSpellMap().get(cboxSpells.getSelectedItem().toString()).getMaxDamage();
				int minDamage = playerOne.getSpellMap().get(cboxSpells.getSelectedItem().toString()).getMinDamage();		
				int damage = rnd.nextInt(maxDamage - minDamage) + minDamage;
				playerOne.setCurrentMana(playerOne.getCurrentMana() - playerOne.getSpellMap().get(cboxSpells.getSelectedItem().toString()).getManaCost());
				
				if(resist <= 1)
				{
					damage = (int)Math.ceil(damage * 0.2);
					resistedSpell = " resisted your ";
				}
				
				monsterOne.setCurrentHealth(monsterOne.getCurrentHealth() - damage);
				if(monsterOne.getCurrentHealth() <= 0)
				{
					txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "The " + monsterOne.getMonsterTyp() + resistedSpell + spellName + " and took " + damage + " damage and died, you gained ");
					lblMonsterHp.setText("You killed the monster and won!");
					battleEnd();
				}
				else
				{
					txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "The " + monsterOne.getMonsterTyp() + resistedSpell + spellName + " and took " + damage + " damage!");
					lblMonsterHp.setText("HP: " + monsterOne.getCurrentHealth() + "/" + monsterOne.getMaxHealth());
				}
				
				lblPlayerMana.setText("Mana: " + playerOne.getCurrentMana() + "/" + playerOne.getMaxMana());
			}
			else
			{
				txtAreaMain.setText(txtAreaMain.getText() + "\n\n" + "You don't have enough mana Hero!");
			}
		}		
	}
	
	private void monsterImage(String path)
	{
		ImageIcon icon = new ImageIcon(path);
		
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon p = new ImageIcon(newimg);
		lblMonsterImage.setIcon(p);
	}
	
	private void playerImage()
	{
		ImageIcon icon = new ImageIcon("C:\\Users\\IBM Jimmy Saari\\eclipse-workspace\\MonsterHunting\\src\\monsterHunting\\resources\\knight.png");
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon p = new ImageIcon(newimg);
		lblPlayerImage.setIcon(p);
	}
	
}
