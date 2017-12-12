package monsterHunting.common;

public class Monster {
	int maxHealth = 10;
	int currentHealth;
	int str;
	int expGiven;
	int dodgeChance;
	public int getDodgeChance() {
		return dodgeChance;
	}
	public void setDodgeChance(int dodgeChance) {
		this.dodgeChance = dodgeChance;
	}
	public int getExpGiven() {
		return expGiven;
	}
	public void setExpGiven(int expGiven) {
		this.expGiven = expGiven;
	}
	String monsterTyp;
	
	public String getMonsterTyp() {
		return monsterTyp;
	}
	public void setMonsterTyp(String monsterTyp) {
		this.monsterTyp = monsterTyp;
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
	
}
