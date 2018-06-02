package pieces;

import java.util.ArrayList;

import chess.Board_1;
import chess.ChessPieceSprite;
import chess.ChessPieceSprite.ChessPieceSpriteType;

public class Knight extends Piece{
	
	public Knight(int col){
		color = col;
		name = KNIGHT;
		
		if(color == BLACK || color == WHITE) 	team = 1;
		else 									team = 2;
		switch(color)
		{
		case(BLACK):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_KNIGHT);
			break;
		case(RED):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_KNIGHT);
			break;
		case(GREEN):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_KNIGHT);
			break;
		case(WHITE):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_KNIGHT);
			break;
		}    
	}

	public ArrayList<Position> getMovement(Board_1 board_1, Position now) {
		int x = now.getX();
		int y = now.getY();
		
		//different in 4 people
		final int MAX = 8;
		
		ArrayList<Position> go = new ArrayList<Position>();
		
		go.add(new Position(x + 1, y + 2));
		go.add(new Position(x + 1, y - 2));
		go.add(new Position(x - 1, y + 2));
		go.add(new Position(x - 1, y - 2));
		go.add(new Position(x + 1, y + 2));
		
		go.add(new Position(x + 2, y + 1));
		go.add(new Position(x + 2, y - 1));
		go.add(new Position(x - 2, y + 1));
		go.add(new Position(x - 2, y - 1));
		
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
