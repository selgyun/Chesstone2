package pieces;

import java.util.ArrayList;

import chess.Board_1;
import chess.ChessPieceSprite;
import chess.ChessPieceSprite.ChessPieceSpriteType;

public class Pawn extends Piece{
	
	boolean isMoved;
	public Pawn(int col){
		color = col;
		name = PAWN;
		isMoved = false;
		
		if(color == BLACK || color == WHITE) 	team = 1;
		else 									team = 2;
		switch(color)
		{
		case(BLACK):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_PAWN);
			break;
		case(RED):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_PAWN);
			break;
		case(GREEN):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_PAWN);
			break;
		case(WHITE):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_PAWN);
			break;
		} 
	}
	
	public void moved() {
		isMoved = true;
	}

	public ArrayList<Position> getMovement(Board_1 board_1, Position now) {
		int x = now.getX();
		int y = now.getY();
		int dir = 0;
		
		//different in 4 people
		final int MAX = 8;
		
		if(color == BLACK)      dir = -1;
		else if(color == WHITE) dir = +1;
		
		ArrayList<Position> go = new ArrayList<Position>();
		
		if(board_1.getPiece(x + 1, y + dir)!= null)	
			go.add(new Position(x + 1, y + dir));
		if(board_1.getPiece(x - 1, y + dir) != null)
			go.add(new Position(x - 1, y + dir));
		
		for(int i = 0; i < 2; i++)
		{
			if(board_1.getPiece(x,  y + dir) == null)
				go.add(new Position(x, y + dir));
			
			if(isMoved == true) break;
			dir *= 2;
		}
		
		for(int i = 0; i < go.size(); i++)
		{
			int goX, goY;
			goX = go.get(i).getX();
			goY = go.get(i).getY();
			
			if(goX < 0 || goX >= MAX || goY < 0 || goY >= MAX)
				go.remove(i);
			
			if(board_1.getPiece(goX, goY).team == this.team)
				go.remove(i);
		}
		
		return go;
	}


}
