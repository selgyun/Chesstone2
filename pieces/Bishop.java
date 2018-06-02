package pieces;

import java.util.ArrayList;

import chess.Board_1;
import chess.ChessPieceSprite;
import chess.ChessPieceSprite.ChessPieceSpriteType;

public class Bishop extends Piece {

	public Bishop(int col){
		color = col;
		name = BISHOP;
		
		if(players == 1) team = color;
		
		else {
			if(color == BLACK || color == WHITE) 	team = 1;
			else 									team = 2;
		}
		
		switch(color)
		{
		case(BLACK):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_BISHOP);
			break;
		case(RED):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_BISHOP);
			break;
		case(GREEN):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_BISHOP);
			break;
		case(WHITE):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_BISHOP);
			break;
		}
	}
	
	public ArrayList<Position> getMovement(Board_1 board_1, Position now) {
		
		
		int x = now.getX();
		int y = now.getY();
		
		ArrayList<Position> go = new ArrayList<Position>();
		
		for(int i = 1, j = 1; Position.inRange(x + i, y + j); i++, j++)
		{
			int goX = x + i;
			int goY = y + j;
			
			if(board_1.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board_1.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = 1, j = -1; Position.inRange(x + i, y + j); i++, j--)
		{
			int goX = x + i;
			int goY = y + j;
			
			if(board_1.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board_1.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = -1, j = 1; Position.inRange(x + i, y + j); i--, j++)
		{
			int goX = x + i;
			int goY = y + j;
			
			if(board_1.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board_1.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = -1, j = -1; Position.inRange(x + i, y + j); i--, j--)
		{
			int goX = x + i;
			int goY = y + j;
			
			if(board_1.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board_1.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}

		return go;
	}

}
