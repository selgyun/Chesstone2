package chess;

import pieces.Piece;
import pieces.Position;

public class Board_2 {
	// 20180531 RedJen Initialized

	public Piece[][] board = new Piece[8][8];
	public int turn;
	public boolean p1_catchable[][] = new boolean[8][8];
	public boolean p2_catchable[][] = new boolean[8][8];

	void Move(Position PiecePosition, Position dest) {
		board[dest.getX()][dest.getY()] = board[PiecePosition.getX()][PiecePosition.getY()];
		board[PiecePosition.getX()][PiecePosition.getY()] = null;
	}

	Piece getPiece(int PieceX, int PieceY) {
		return board[PieceX][PieceY];
	}
}
