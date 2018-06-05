package chess;

import java.util.ArrayList;
import java.util.Arrays;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.MT;
import pieces.Pawn;
import pieces.Piece;
import pieces.Position;
import pieces.Queen;
import pieces.Rook;

public class Board_1 extends Board_Master implements ConstDef {

	// 20180531 RedJen Initialized

	final Piece[][] initialBoard = {
			{ new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK), new King(BLACK),
					new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK) },
			{ new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK),
					new Pawn(BLACK), new Pawn(BLACK) },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE),
					new Pawn(WHITE), new Pawn(WHITE) },
			{ new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE), new King(WHITE),
					new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE) }, };

	public Board_1() {
		this.board = initialBoard;
		curPiece = null;
		skipTurn = 2;
		turn = WHITE;
		boardSize= 8;
	}

	// return null if (x, y) is out of range
	// if there is "i" players

}