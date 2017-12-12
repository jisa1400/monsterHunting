package monsterHunting.common;

public class Spells {
	String name;
	int maxDamage;
	int manaCost;
	int minDamage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
}
