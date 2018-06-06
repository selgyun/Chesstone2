package pieces;

import java.util.ArrayList;

import chess.Board_Master;
import chess.ChessPieceSprite;
import chess.ChessPieceSprite.ChessPieceSpriteType;

public class Queen extends Piece {

	public Queen(int col) {
		color = col;
		name = QUEEN;
		nameS = "Queen";
		if(players == 1) team = color;
		
		else {
			if(color == BLACK || color == WHITE) 	team = 1;
			else 									team = 2;
		}

		switch (color) {
		case (BLACK):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_QUEEN);
			break;
		case (RED):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_QUEEN);
			break;
		case (GREEN):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_QUEEN);
			break;
		case (WHITE):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_QUEEN);
			break;
		}
	}

	public ArrayList<Position> getMovement(Board_Master board, Position now) {
		int x = now.getX();
		int y = now.getY();
		
		ArrayList<Position> go = new ArrayList<Position>();

		for(int i = 1; Position.inRange(x + i, y); i++)
		{
			int goX = x + i;		
			int goY = y;
			
			if(board.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));
				break;
			}
		}
		
		for(int i = 1; Position.inRange(x - i, y); i++)
		{
			int goX = x - i;		
			int goY = y;
			
			if(board.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = 1; Position.inRange(x, y + i); i++)
		{
			int goX = x;		
			int goY = y + i;
			
			if(board.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = 1; Position.inRange(x, y - i); i++)
		{
			int goX = x;		
			int goY = y - i;
			
			
			if(board.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = 1, j = 1; Position.inRange(x + i, y + j); i++, j++)
		{
			int goX = x + i;
			int goY = y + j;
			
			if(board.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = 1, j = -1; Position.inRange(x + i, y + j); i++, j--)
		{
			int goX = x + i;
			int goY = y + j;
			
			if(board.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = -1, j = 1; Position.inRange(x + i, y + j); i--, j++)
		{
			int goX = x + i;
			int goY = y + j;
			
			if(board.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}
		
		for(int i = -1, j = -1; Position.inRange(x + i, y + j); i--, j--)
		{
			int goX = x + i;
			int goY = y + j;
			
			if(board.getPiece(goX, goY).name == 0)
				go.add(new Position(goX, goY));
			
			else
			{
				if(board.getPiece(goX, goY).team != team)
					go.add(new Position(goX, goY));			
				break;
			}
		}

		return go;
	}

}
