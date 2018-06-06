package chess;

import java.util.ArrayList;

import pieces.Piece;
import pieces.Position;

public class Checker implements ConstDef {

	// kindkiz ¿€º∫
	Board_Master board;

	public Checker(Board_Master board) {
		this.board = board;
	}

	public boolean isChecked() {
		// find king position
		int KingX = 0, KingY = 0;

		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				if (board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == board.getTurn()) {
					KingX = X;
					KingY = Y;
				}
			}
		}

		// is king underAttecked?
		return board.getCatchable(board.getNextTurn(), KingX, KingY) || board.getCatchable(board.getPrevTurn(), KingX, KingY);
	}

	public boolean isCheckMate() {
		if (!isChecked()) {
			return false;
		}
		// find king position
		int KingX = 0, KingY = 0;
		Position KingPos = null;

		LABEL: for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				if (board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == board.getTurn()) {
					KingX = X;
					KingY = Y;
					KingPos = new Position(X, Y);
					break LABEL;
				}
			}
		}

		// king cannot move
		ArrayList<Position> KingMovement = board.getPiece(KingPos).getMovement(board, KingPos);
		for (int i = 0; i < KingMovement.size(); i++) {
			if (board.getCatchable(board.getNextTurn(), KingMovement.get(i).getX(), KingMovement.get(i).getY())) {
			}
			else {
				return false;
			}
		}

		// there is no legal move

		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				Piece nowPiece = board.getPiece(X, Y);
				if(nowPiece.getColor() == board.getTurn()) {
					Position nowPos = new Position(X, Y);
					ArrayList<Position> nowPieceMove = nowPiece.getMovement(board, nowPos);
					for(int i= 0; i<nowPieceMove.size(); i++) {
						if(!board.isIllegalMove(nowPos, nowPieceMove.get(i))){
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isStaleMate() {
		if (isChecked()) {
			return false;
		}
		// no legal move
		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				Piece nowPiece = board.getPiece(X, Y);
				if(nowPiece.getColor() == board.getTurn()) {
					Position nowPos = new Position(X, Y);
					ArrayList<Position> nowPieceMove = nowPiece.getMovement(board, nowPos);
					for(int i= 0; i<nowPieceMove.size(); i++) {
						if(!board.isIllegalMove(nowPos, nowPieceMove.get(i))){
							return false;
						}
					}
				}
			}
		}
		return true;
	}

}
