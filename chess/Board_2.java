package chess;

import pieces.*;

public class Board_2 extends Board_Master implements ConstDef {
	// 20180531 RedJen Initialized
	final Piece[][] initialBoard = {
		{ new MT(), new MT(), new MT(), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new MT(), new MT(), new MT(), },
		{ new MT(), new MT(), new MT(), new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK), new King(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK), new MT(), new MT(), new MT() },
		{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT()},
		{ new Rook(RED), new Pawn(RED), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new Pawn(GREEN), new Rook(GREEN)},
		{ new Knight(RED), new Pawn(RED), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new Pawn(GREEN), new Knight(GREEN) },
		{ new Bishop(RED), new Pawn(RED), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new Pawn(GREEN), new Bishop(GREEN) },
		{ new Queen(RED), new Pawn(RED), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new Pawn(GREEN), new Queen(GREEN) },
		{ new King(RED), new Pawn(RED), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new Pawn(GREEN), new King(GREEN) },
		{ new Bishop(RED), new Pawn(RED), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new Pawn(GREEN), new Bishop(GREEN) },
		{ new Knight(RED), new Pawn(RED), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new Pawn(GREEN), new Knight(GREEN) },
		{ new Rook(RED), new Pawn(RED), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new Pawn(GREEN), new Rook(GREEN)},
		{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT()},
		{ new MT(), new MT() ,new MT(), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new MT(), new MT(), new MT()},
		{ new MT(), new MT(), new MT(), new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE), new King(WHITE), new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE), new MT(), new MT(), new MT()}
		};
	
	public Board_2() {
		Piece.players = 2;
		skipTurn = 1;
		board = initialBoard;
		curPiece = null;
	}
}