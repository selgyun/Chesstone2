package pieces;

import java.util.ArrayList;

import chess.Board_1;
import chess.ChessPieceSprite;
import chess.ChessPieceSprite.ChessPieceSpriteType;

public class Rook extends Piece {

	public Rook(int col){
		color = col;
		name = ROOK;
		
		if(color == BLACK || color == WHITE) 	team = 1;
		else 									team = 2;
		
		switch(color)
		{
		case(BLACK):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_LOOK);
			break;
		case(RED):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_LOOK);
			break;
		case(GREEN):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_LOOK);
			break;
		case(WHITE):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_LOOK);
			break;
		}
	}

	public ArrayList<Position> getMovement(Board_1 board_1, Position now) {
		int x = now.getX();
		int y = now.getY();
			
		//different in 4 people
		final int MAX = 8;
		
		ArrayList<Position> go = new ArrayList<Position>();
		
		for(int i = 1; x + i < MAX; i++)
		{
			int goX = x + i;		
			int goY = y;
			
			if(board_1.getPiece(goX, goY) == null)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board_1.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = 1; x - i >= 0; i++)
		{
			int goX = x - i;		
			int goY = y;
			
			if(board_1.getPiece(goX, goY) == null)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board_1.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = 1; y + i < MAX; i++)
		{
			int goX = x;		
			int goY = y + i;
			
			if(board_1.getPiece(goX, goY) == null)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board_1.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = 1; y - i >= 0; i++)
		{
			int goX = x;		
			int goY = y - i;
			
			if(board_1.getPiece(goX, goY) == null)
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
