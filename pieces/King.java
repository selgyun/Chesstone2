package pieces;

import java.util.ArrayList;

import chess.Board_Master;
import chess.ChessPieceSprite;
import chess.ChessPieceSprite.ChessPieceSpriteType;

public class King extends Piece {
	
	public King(int col){
		color = col;
		name = KING;
		
		if(players == 1) team = color;
		
		else {
			if(color == BLACK || color == WHITE) 	team = 1;
			else 									team = 2;
		}
		
		switch(color)
		{
		case(BLACK):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_KING);
			break;
		case(RED):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_KING);
			break;
		case(GREEN):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_KING);
			break;
		case(WHITE):
			img = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_KING);
			break;
		}  
	}

	public ArrayList<Position> getMovement(Board_Master board, Position now) {
		int x = now.getX();
		int y = now.getY();
		
		ArrayList<Position> go = new ArrayList<Position>();
		
		go.add(new Position(x + 1, y + 1));
		go.add(new Position(x + 1, y));
		go.add(new Position(x + 1, y - 1));
		
		go.add(new Position(x, y + 1));
		go.add(new Position(x, y - 1));
		
		go.add(new Position(x - 1, y + 1));
		go.add(new Position(x - 1, y));
		go.add(new Position(x - 1, y - 1));
		
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
		
		if(!checking)
		{
			checking = true;
			for(int i = 0; i < go.size(); i++)
			{
				int goX, goY;
				goX = go.get(i).getX();
				goY = go.get(i).getY();

				if(board.isIllegalMove(new Position(x, y), new Position(goX, goY)))
				{
					go.remove(i);
					i--;
				}
			}
			checking = false;
		}
	
		return go;
	}
}
