package monsterHunting.common;

import java.util.HashMap;
import java.util.List;

public class Player {
	
	int maxHealth;
	int startHealth = 40;
	int currentHealth;
	int str;
	int nrPotions;
	int playerLevel = 0;
	int currentExp;
	int expToLevel = 30;
	int maxMana = 20;
	int currentMana;
	HashMap<String, Spells> spellMap;
	
	
	public int getMaxMana() {
		return maxMana;
	}
	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	public int getCurrentMana() {
		return currentMana;
	}
	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}
	public HashMap<String, Spells> getSpellMap() {
		return spellMap;
	}
	public void setSpellMap(HashMap<String, Spells> spellMap) {
		this.spellMap = spellMap;
	}
	public int getCurrentExp() {
		return currentExp;
	}
	public void setCurrentExp(int currentExp) {
		this.currentExp = currentExp;
	}
	public int getExpToLevel() {
		return expToLevel;
	}
	
	public void setExpToLevel(int expToLevel) {
		this.expToLevel = expToLevel;
	}
	public int getPlayerLevel() {
		return playerLevel;
	}
	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getNrPotions() {
		return nrPotions;
	}
	public void setNrPotions(int nrPotions) {
		this.nrPotions = nrPotions;
	}
	public int getStartHealth() {
		return startHealth;
	}
	public void setStartHealth(int startHealth) {
		this.startHealth = startHealth;
	}
}
