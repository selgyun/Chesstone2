package chess;

import pieces.Piece;
import pieces.Position;

public class Board_Master implements ConstDef {
	protected int turn;
	protected int player;

	protected Piece[][] board;
	public Piece curPiece;
	public Position curPiecePos;
	
	public int getTurn() {
		return turn;
	}

	public void nextTurn() {
		turn += player;
		if (turn > 4)
			turn = WHITE;
	}
	

	void Move(Position PiecePosition, Position dest) {
		board[dest.getX()][dest.getY()] = board[PiecePosition.getX()][PiecePosition.getY()];
		board[PiecePosition.getX()][PiecePosition.getY()] = null;
	}

	Piece getPiece(int PieceX, int PieceY) {
		return board[PieceX][PieceY];
	}
	
}
