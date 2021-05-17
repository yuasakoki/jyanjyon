package model;

import java.io.Serializable;

public class Hero implements Serializable{
	private String name;
	private int level;
	private int hp;
	private int maxHp;
	private int mp;
	private int maxMp;
	private int power;
	private int defense;
	private int special_power;
	private int fp;
	private int gold;
	private int exp;
	private int experience;
	private int weaponPower;
	private int herb;
	private int superherb;
	private int ultrasuperherb;


	public Hero() {}
	public Hero(String name, int level, int hp, int maxHp, int mp, int maxMp, int power, int defense, int special_power,
			int fp, int gold, int exp, int experience,int weaponPower,int herb,int superherb,int ultrasuperherb) {
		this.setName(name);
		this.setLevel(level);
		this.setHp(hp);
		this.setMaxHp(maxHp);
		this.setMp(mp);
		this.setMaxMp(maxMp);
		this.setPower(power);
		this.setDefense(defense);
		this.setSpecial_power(special_power);
		this.setFp(fp);
		this.setGold(gold);
		this.setExp(exp);
		this.setExperience(experience);

	}
	public Hero(String name, int maxHp, int hp, int power, int defense) {
		this.setName(name);
		this.setHp(hp);
		this.setMaxHp(maxHp);
		this.setPower(power);
		this.setDefense(defense);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getPower() {
//		return power;
		return power + weaponPower;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getSpecial_power() {
		return special_power + weaponPower;
	}
	public void setSpecial_power(int special_power) {
		this.special_power = special_power;
	}
	public int getWeaponPower() {
		return weaponPower;
	}
	public void setWeaponPower(int weaponPower) {
		this.weaponPower = weaponPower;
	}
	public int getFp() {
		return fp;
	}
	public void setFp(int fp) {
		this.fp = fp;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getLevel() {
		return level;
	}

	public int getExp() {
		return exp;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public int getMaxMp() {
		return maxMp;
	}
	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}
	public int getHerb() {
		return herb;
	}
	public int getSuperherb() {
		return superherb;
	}
	public int getUltrasuperherb() {
		return ultrasuperherb;
	}
	public void setHerb(int herb) {
		this.herb = herb;
	}
	public void setSuperherb(int superherb) {
		this.superherb = superherb;
	}
	public void setUltrasuperherb(int ultrasuperherb) {
		this.ultrasuperherb = ultrasuperherb;
	}

}
