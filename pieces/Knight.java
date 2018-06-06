package pieces;

import java.util.ArrayList;

import chess.Board_Master;
import chess.ChessPieceSprite;
import chess.ChessPieceSprite.ChessPieceSpriteType;

public class Knight extends Piece{
	
	public Knight(int col){
		color = col;
		name = KNIGHT;
		nameS = "Knight";
		if(players == 1) team = color;
		
		else {
			if(color == BLACK || color == WHITE) 	team = 1;
			else 									team = 2;
		}
		
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

	public ArrayList<Position> getMovement(Board_Master board, Position now) {
		int x = now.getX();
		int y = now.getY();
		
		ArrayList<Position> go = new ArrayList<Position>();
		
		go.add(new Position(x + 1, y + 2));
		go.add(new Position(x + 1, y - 2));
		go.add(new Position(x - 1, y + 2));
		go.add(new Position(x - 1, y - 2));
		
		go.add(new Position(x + 2, y + 1));
		go.add(new Position(x + 2, y - 1));
		go.add(new Position(x - 2, y + 1));
		go.add(new Position(x - 2, y - 1));
		
	
		
		for(int i = 0; i < go.size(); i++)
		{
			int goX, goY;
			goX = go.get(i).getX();
			goY = go.get(i).getY();
			
			if(!Position.inRange(goX, goY))
			{
				go.remove(i);
				i--;
				continue;
			}
			
			if(board.getPiece(goX, goY).team == this.team)
			{
				go.remove(i);
				i--;
			}
		}
	
		return go;
	}
}
