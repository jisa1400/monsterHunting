package monsterHunting.common;

import java.util.Random;

public class Potion {
	String potionType;
	int maxRestored;
	int minRestored;
	
	public Player useHealingPotion (Player currentPlayer)
	{
		if(currentPlayer.getHealingPotions() <= 0)
		{
			currentPlayer.setTxtArea("You have no more HealingPotions! Slay monsters to find one!");
		}
		else
		{
			Random rnd = new Random();
			
			int maxRestored = (int) Math.ceil(currentPlayer.getMaxHealth() * 0.8);
			int minRestored = (int) Math.ceil(currentPlayer.getMaxHealth() * 0.3);
			int restoredAmount = rnd.nextInt(maxRestored - minRestored) + minRestored;
			
			currentPlayer.setHealingPotions(currentPlayer.getHealingPotions() - 1);
			
			if(currentPlayer.getCurrentHealth() + restoredAmount > currentPlayer.getMaxHealth())
			{
				currentPlayer.setTxtArea("\n\n" + "You used a potion to heal for " + (currentPlayer.getMaxHealth() - currentPlayer.getCurrentHealth()) + " to full health and have " + currentPlayer.getHealingPotions() + " potions left.");
				currentPlayer.setCurrentHealth(currentPlayer.getMaxHealth());
			}
			else
			{
				currentPlayer.setCurrentHealth(currentPlayer.getCurrentHealth() + restoredAmount);
				currentPlayer.setTxtArea("\n\n" + "You used a potion to heal for " + restoredAmount + " and have healed to " + currentPlayer.getCurrentHealth() + " and have " + currentPlayer.getHealingPotions() + " potions left!");
			}
			
		}
		return currentPlayer;
	}
}
