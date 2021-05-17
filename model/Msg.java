package model;

import java.io.Serializable;

public class Msg implements Serializable {
	private String levelMsg;
	private String hpMsg;
	private String mpMsg;
	private String powerMsg;
	private String defenseMsg;
	private String specialMsg;

	public Msg() {}
	public Msg(String levelMsg, String hpMsg,String mpMsg,
			String powerMsg,String defenseMsg,String specialMsg) {
		this.setLevelMsg(levelMsg);
		this.setHpMsg(hpMsg);
		this.setMpMsg(mpMsg);
		this.setPowerMsg(powerMsg);
		this.setDefenseMsg(defenseMsg);
		this.setSpecialMsg(specialMsg);

	}
	public String getLevelMsg() {
		return levelMsg;
	}
	public void setLevelMsg(String levelMsg) {
		this.levelMsg = levelMsg;
	}
	public String getHpMsg() {
		return hpMsg;
	}
	public void setHpMsg(String hpMsg) {
		this.hpMsg = hpMsg;
	}
	public String getMpMsg() {
		return mpMsg;
	}
	public void setMpMsg(String mpMsg) {
		this.mpMsg = mpMsg;
	}
	public String getPowerMsg() {
		return powerMsg;
	}
	public void setPowerMsg(String powerMsg) {
		this.powerMsg = powerMsg;
	}
	public String getDefenseMsg() {
		return defenseMsg;
	}
	public void setDefenseMsg(String defenseMsg) {
		this.defenseMsg = defenseMsg;
	}
	public String getSpecialMsg() {
		return specialMsg;
	}
	public void setSpecialMsg(String specialMsg) {
		this.specialMsg = specialMsg;
	}

}
