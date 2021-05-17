package model;

import java.io.Serializable;

public class MoveCounter1 implements Serializable{
	private int leftMove;
	private int upMove;
	private int rightMove;
	private int downMove;
	private int move;


	public int getLeftMove(){return leftMove;}
	public void setLeftMove(int leftMove){this.leftMove = leftMove;}

	public int getUpMove(){return upMove;}
	public void setUpMove(int upMove){this.upMove = upMove;}

	public int getRightMove(){return rightMove;}
	public void setRightMove(int rightMove){this.rightMove = rightMove;}

	public int getDownMove() {	return downMove;}
	public void setDownMove(int downMove) {this.downMove = downMove;}

	public int getMove() {return move;	}
	public void setMove(int move) {this.move = move;	}

	}
