package chess;

import pieces.MT;
import pieces.Pawn;
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
	
	void Move(Position PiecePosition, Position dest) {
		if (getPiece(PiecePosition) instanceof Pawn) {
			((Pawn) getPiece(PiecePosition)).moved();
		}
		board[dest.getX()][dest.getY()] = board[PiecePosition.getX()][PiecePosition.getY()];
		board[PiecePosition.getX()][PiecePosition.getY()] = new MT();
		curPiece = null;
		nextTurn();
		System.out.println(board[PiecePosition.getX()][PiecePosition.getY()] + " , " + board[dest.getX()][dest.getY()]);
	}

	public boolean isIllegalMove(Position PiecePosition, Position dest) {
		Piece temp = board[dest.getX()][dest.getY()];
		board[dest.getX()][dest.getY()] = board[PiecePosition.getX()][PiecePosition.getY()];
		board[PiecePosition.getX()][PiecePosition.getY()] = new MT();
		Checker checker = new Checker();
		if (checker.isChecked((Board_1) this, turn)) {
			board[PiecePosition.getX()][PiecePosition.getY()] = board[dest.getX()][dest.getY()];
			board[dest.getX()][dest.getY()] = temp;
			return true;
		}
		else {
			board[PiecePosition.getX()][PiecePosition.getY()] = board[dest.getX()][dest.getY()];
			board[dest.getX()][dest.getY()] = temp;
			return false;
		}
	}
	
}
