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

	public boolean isChecked(int color) {
		// find king position
		int KingX = 0, KingY = 0;

		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				if (board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == color) {
					KingX = X;
					KingY = Y;
				}
			}
		}

		// is king underAttecked?
		if (board.getPiece(KingX, KingY).players != 1)
			return (board.getCatchable(color % 4 + 1, KingX, KingY)
					|| board.getCatchable((color + 2) % 4 + 1, KingX, KingY));
		else
			return (board.getCatchable((color + 2) % 4, KingX, KingY));

	}

	public boolean isCheckMate(int color) {
		if (!isChecked(color)) {
			return false;
		}
		// find king position
		int KingX = 0, KingY = 0;
		Position KingPos = null;

		LABEL: for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				if (board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == color) {
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
			if (!board.isIllegalMove(KingPos, KingMovement.get(i))) {
				return false;
			}
		}

		// there is no legal move

		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				Piece nowPiece = board.getPiece(X, Y);
				if (board.getPiece(X, Y).players == 1) {
					if (nowPiece.getColor() == color) {
						Position nowPos = new Position(X, Y);
						ArrayList<Position> nowPieceMove = nowPiece.getMovement(board, nowPos);
						for (int i = 0; i < nowPieceMove.size(); i++) {
							if (!board.isIllegalMove(nowPos, nowPieceMove.get(i))) {
								return false;
							}
						}
					}
				}else {
					if (nowPiece.getColor() == color || nowPiece.getColor() == (color+2) % 4) {
						Position nowPos = new Position(X, Y);
						ArrayList<Position> nowPieceMove = nowPiece.getMovement(board, nowPos);
						for (int i = 0; i < nowPieceMove.size(); i++) {
							if (!board.isIllegalMove(nowPos, nowPieceMove.get(i))) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isStaleMate(int color) {
		// no legal move
		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				Piece nowPiece = board.getPiece(X, Y);
				if (nowPiece.getColor() == color) {
					if (Piece.players == 1) {
						if (isChecked(color))
							return false;
					} else {
						if (isChecked(color) || isChecked((color + 2) % 4))
							return false;
					}
					Position nowPos = new Position(X, Y);
					ArrayList<Position> nowPieceMove = nowPiece.getMovement(board, nowPos);
					for (int i = 0; i < nowPieceMove.size(); i++) {
						if (!board.isIllegalMove(nowPos, nowPieceMove.get(i))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

}
