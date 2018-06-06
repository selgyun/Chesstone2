package pieces;

import java.util.ArrayList;

import chess.Board_Master;
import chess.ChessPieceSprite;
import chess.ChessPieceSprite.ChessPieceSpriteType;

public class Pawn extends Piece{
	
	boolean isMoved;
	public Pawn(int col){
		color = col;
		name = PAWN;
		nameS = "Pawn";
		isMoved = false;
		
		if(players == 1) team = color;
		
		else {
			if(color == BLACK || color == WHITE) 	team = 1;
			else 									team = 2;
		}
		
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

	public ArrayList<Position> getMovement(Board_Master board, Position now) {
		int x = now.getX();
		int y = now.getY();
		int dir = 0;
		
		if(color == BLACK || color == RED)      dir = +1;
		else if(color == WHITE || color == GREEN) dir = -1;
		
		ArrayList<Position> go = new ArrayList<Position>();

		if (color == BLACK || color == WHITE) {
			if (Position.inRange(x + dir, y + 1) && board.getPiece(x + dir, y + 1).name != 0) {
				if (board.getPiece(x + dir, y + 1).team != this.team)
					go.add(new Position(x + dir, y + 1));
			}

			if (Position.inRange(x + dir, y - 1) && board.getPiece(x + dir, y - 1).name != 0) {
				if (board.getPiece(x + dir, y - 1).team != this.team)
					go.add(new Position(x + dir, y - 1));
			}

			for (int i = 0; i < 2; i++) {
				
				if(!Position.inRange(x + dir, y))	break;
				
				if (board.getPiece(x + dir, y).name == 0) {
					go.add(new Position(x + dir, y));
				} else
					break;

				if (isMoved == true)
					break;
				dir *= 2;
			}
		}
		
		else if (color == RED || color == GREEN) {
			if (Position.inRange(x + 1, y + dir) && board.getPiece(x + 1, y + dir).name != 0) {
				if (board.getPiece(x + 1, y + dir).team != this.team)
					go.add(new Position(x + 1, y + dir));
			}

			if (Position.inRange(x - 1, y + dir) && board.getPiece(x - 1, y + dir).name != 0) {
				if (board.getPiece(x - 1, y + dir).team != this.team)
					go.add(new Position(x - 1, y + dir));
			}

			for (int i = 0; i < 2; i++) {
				if (board.getPiece(x, y + dir).name == 0) {
					go.add(new Position(x, y + dir));
				} else
					break;

				if (isMoved == true)
					break;
				dir *= 2;
			}
		}
		
		

		for(int i = 0; i < go.size(); i++)
		{
			int goX, goY;
			goX = go.get(i).getX();
			goY = go.get(i).getY();

			if(!Position.inRange(goX, goY))
			{
				go.remove(i);
				i--;
			}

		}
		
	
        
		return go;
	}

}
