package chess;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Position;
import pieces.Queen;
import pieces.Rook;

public class Board_2 implements ConstDef{
	// 20180531 RedJen Initialized

	private Piece[][] board;
	
	final Piece[][] initialBoard = {
			{ null, null, null, new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE), new King(WHITE),
					new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE), null, null, null},
			{ null, null ,null, new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE),
					new Pawn(WHITE), new Pawn(WHITE), null, null, null},
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null}, 
			{ new Rook(RED), new Pawn(RED), null, null, null, null, null, null, null, null, null, null, new Pawn(GREEN), new Rook(GREEN)},
			{ new Knight(RED), new Pawn(RED), null, null, null, null, null, null, null, null, null, null, new Pawn(GREEN), new Knight(GREEN) },
			{ new Bishop(RED), new Pawn(RED), null, null, null, null, null, null, null, null, null, null, new Pawn(GREEN), new Bishop(GREEN) },
			{ new Queen(RED), new Pawn(RED), null, null, null, null, null, null, null, null, null, null, new Pawn(GREEN), new Queen(GREEN) },
			{ new King(RED), new Pawn(RED), null, null, null, null, null, null, null, null, null, null, new Pawn(GREEN), new King(GREEN) },
			{ new Bishop(RED), new Pawn(RED), null, null, null, null, null, null, null, null, null, null, new Pawn(GREEN), new Bishop(GREEN) },
			{ new Knight(RED), new Pawn(RED), null, null, null, null, null, null, null, null, null, null, new Pawn(GREEN), new Knight(GREEN) },
			{ new Rook(RED), new Pawn(RED), null, null, null, null, null, null, null, null, null, null, new Pawn(GREEN), new Rook(GREEN)},
			{ new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK),
					new Pawn(BLACK), new Pawn(BLACK) },
			{ new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK), new King(BLACK),
					new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK) } };
	
	public int turn;
	public boolean p1_catchable[][] = new boolean[14][14];
	public boolean p2_catchable[][] = new boolean[14][14];

	void Move(Position PiecePosition, Position dest) {
		board[dest.getX()][dest.getY()] = board[PiecePosition.getX()][PiecePosition.getY()];
		board[PiecePosition.getX()][PiecePosition.getY()] = null;
	}

	Piece getPiece(int PieceX, int PieceY) {
		return board[PieceX][PieceY];
	}
}
