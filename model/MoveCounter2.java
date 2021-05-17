package model;

import java.io.Serializable;

public class MoveCounter2 implements Serializable{
	private int move;
	private int floor = 1;


	public int getMove() {return move;	}
	public void setMove(int move) {this.move = move;	}

	public int getFloor() {return floor;	}
	public void setFloor(int floor) {this.floor = floor;	}
	}
