package pieces;

import java.util.ArrayList;

import chess.Board_1;

public class MT extends Piece {
	public MT(){
	    color = 0;
		team = 0; //TEAM 1 : Black, White || TEAM 2 : Red, Green
		name = 0;
		img = null;
	}
	public ArrayList<Position> getMovement(Board_1 board_1, Position now){
		return null;
	}
}
