package model;

import java.io.Serializable;

public class WeaponsRecord implements Serializable  {

	private String weaponId;
	private String weaponMei;
	private int hanbai;
	private int attack_power;


	public WeaponsRecord() {}

	public WeaponsRecord(String weaponId, String weaponMei, int hanbai, int attack_power) {
		this.weaponId = weaponId;
		this.weaponMei = weaponMei;
		this.hanbai =  hanbai;
		this.attack_power = attack_power;
	}

	public String getWeaponId() {
		return weaponId;
	}

	public String getWeaponMei() {
		return weaponMei;
	}

	public int getHanbai() {
		return hanbai;
	}

	public int getAttack_power() {
		return attack_power;
	}

	public void setWeaponId(String weaponId) {
		this.weaponId = weaponId;
	}

	public void setWeaponMei(String weaponMei) {
		this.weaponMei = weaponMei;
	}

	public void setHanbai(int hanbai) {
		this.hanbai = hanbai;
	}

	public void setAttack_power(int attack_power) {
		this.attack_power = attack_power;
	}
}
