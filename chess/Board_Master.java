package chess;

import java.util.ArrayList;

import pieces.MT;
import pieces.Pawn;
import pieces.Piece;
import pieces.Position;

public class Board_Master implements ConstDef {
	public int boardSize;
	
	protected int turn;
	protected int skipTurn;

	protected Piece[][] board;
	public Piece curPiece;
	public Position curPiecePos;

	public ArrayList<Piece> deadPieces;
	
	public int getTurn() {
		return turn;
	}
	
	public int getPrevTurn() {
		if(turn-skipTurn<0)
			return turn-skipTurn+4;
		else 
			return turn+skipTurn;
	}
	
	public int getNextTurn() {
		if(turn+skipTurn>4)
			return turn+skipTurn-4;
		else 
			return turn+skipTurn;
	}
	
	public void nextTurn() {
		turn += skipTurn;
		if (turn > 4)
			turn = WHITE;
		
		if(turn == 1)
			System.out.println("WHITE turn");
		else 
			System.out.println("BLACK turn");
	}

	public String getStringTurn()
	{
		if(this.turn == 1) return "WHITE TeamÀÇ Â÷·Ê!";
		else if(this.turn == 2) return "RED TeamÀÇ Â÷·Ê!";
		else if(this.turn == 3) return "BLACK TeamÀÇ Â÷·Ê!";
		else return "GREEN TeamÀÇ Â÷·Ê!";
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

	boolean getCatchable(int color, int x, int y) {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (getPiece(i, j).getColor() == color) {
					if (getPiece(i, j).getMovement(this, new Position(i, j)).contains(new Position(x, y))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	void Move(Position PiecePosition, Position dest) {
		if (getPiece(PiecePosition) instanceof Pawn) {
			((Pawn) getPiece(PiecePosition)).moved();
		}
		if (getPiece(PiecePosition).getColor() == getNextTurn() || getPiece(PiecePosition).getColor() == getPrevTurn()) {
			deadPieces.add(getPiece(dest));
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
		if (checker.isChecked((Board_1) this)) {
			board[PiecePosition.getX()][PiecePosition.getY()] = board[dest.getX()][dest.getY()];
			board[dest.getX()][dest.getY()] = temp;
			return true;
		} else {
			board[PiecePosition.getX()][PiecePosition.getY()] = board[dest.getX()][dest.getY()];
			board[dest.getX()][dest.getY()] = temp;
			return false;
		}
	}
}
