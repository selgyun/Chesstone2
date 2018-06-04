package chess;

import pieces.Piece;
import pieces.Position;

public class Board_Master implements ConstDef {
	protected int turn;
	protected int skipTurn;
	
	protected Piece[][] board;
	public Piece curPiece;
	public Position curPiecePos;
	
	public int getTurn() {
		return turn;
	}

	public void nextTurn() {
		turn += skipTurn;
		if (turn > 4)
			turn = WHITE;
		System.out.println(turn + " turn");
	}
	
	public Piece getPiece(int PieceX, int PieceY) {
		if (Piece.players == 1 && !Position.inRange(PieceX, PieceY)) {
			return null;
		}
		return board[PieceX][PieceY];
	}

	public Piece getPiece(Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		if (Piece.players == 1) {
			if (!Position.inRange(x, y))
				return null;
		}
		return board[x][y];
	}
	
}
