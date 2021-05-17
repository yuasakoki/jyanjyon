package model;

import java.io.Serializable;

public class PropRecord implements Serializable {

	private String propId;
	private String  propMei;
	private int hanbai;
	private int recovery;


	public PropRecord() {}

	public PropRecord(String propId, String propMei, int hanbai, int recovery) {
		this.propId = propId;
		this.propMei = propMei;
		this.hanbai =  hanbai;
		this.recovery = recovery;
	}

	public String getPropId() {
		return propId;
	}

	public String getPropMei() {
		return propMei;
	}

	public int getHanbai() {
		return hanbai;
	}

	public int getRecovery() {
		return recovery;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public void setPropMei(String propMei) {
		this.propMei = propMei;
	}

	public void setHanbai(int hanbai) {
		this.hanbai = hanbai;
	}

	public void setRecovery(int recovery) {
		this.recovery = recovery;
	}

}
