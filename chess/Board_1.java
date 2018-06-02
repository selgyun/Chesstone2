package chess;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Position;
import pieces.Queen;
import pieces.Rook;

public class Board_1 implements ConstDef{

	// 20180531 RedJen Initialized

	public Piece[][] board = new Piece[8][8];
	public int turn;
	public boolean p1_catchable[][] = new boolean[8][8];
	public boolean p2_catchable[][] = new boolean[8][8];

	Board_1() {
		Piece[][] board = {
				{ new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE), new King(WHITE), new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE) },
				{ new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE) },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK) },
				{ new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK), new King(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK) }
		};
	}
	
	void Move(Position PiecePosition, Position dest) {
		board[dest.getX()][dest.getY()] = board[PiecePosition.getX()][PiecePosition.getY()];
		board[PiecePosition.getX()][PiecePosition.getY()] = null;
	}

	public Piece getPiece(int PieceX, int PieceY) {
		return board[PieceX][PieceY];
	}
}
