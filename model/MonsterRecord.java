package model;

public class MonsterRecord {
	private int no;
	private String name;
	private int maxHp;
	private int hp;
	private int mp;
	private int power;
	private int defense;
	private int fp;
	private int gold;
	private int experience;

	public MonsterRecord() {}

	public MonsterRecord(int no, String name, int maxHp, int hp,
			int mp, int power, int defense, int fp, int gold, int experience) {
		this.no = no;
		this.name = name;
		this.maxHp = maxHp;
		this.hp =  hp;
		this.mp = mp;
		this.power = power;
		this.defense = defense;
		this.fp =  fp;
		this.gold = gold;
		this.experience = experience;

	}

	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getMp() {
		return mp;
	}

	public int getPower() {
		return power;
	}

	public int getDefense() {
		return defense;
	}

	public int getFp() {
		return fp;
	}

	public int getGold() {
		return gold;
	}

	public int getExperience() {
		return experience;
	}

	public void setNo(int bossNo) {
		this.no = bossNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setFp(int fp) {
		this.fp = fp;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
}