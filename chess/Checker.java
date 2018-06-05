package chess;

import pieces.Position;

public class Checker implements ConstDef {

	// kindkiz ¿€º∫

	public boolean isChecked(Board_1 board) {
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

		// done
		// is king underAttecked?
		if (board.getCatchable(board.getNextTurn(), KingX, KingY)) {
			return true;
		}

		return false;
	}

	public boolean isChecked(Board_2 board) {
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

		// done
		if (board.getCatchable(board.getNextTurn(), KingX, KingY)) {
			return true;
		}

		return false;
	}

	public boolean isCheckMate(Board_1 board) {
		if (!isChecked(board)) {
			return false;
		}
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
		// done
		// king cannot move
		if (board.getPiece(KingX, KingY).getMovement(board, new Position(KingX, KingY)).size() > 0) {
			return false;
		}
		// there is no legal move
		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				if (board.getCatchable(board.getTurn(), X, Y))
					return false;
			}
		}
		return true;
	}


	public boolean isCheckMate(Board_2 board) {
		if (!isChecked(board)) {
			return false;
		}
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
		// done
		if (board.getPiece(KingX, KingY).getMovement(board, new Position(KingX, KingY)).size() > 0) {
			return false;
		}
		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				if (board.getCatchable(board.getTurn(), X, Y))
					return false;
			}
		}
		return true;
	}

	public boolean isStaleMate(Board_1 board) {
		if (isChecked(board)) {
			return false;
		}
		//no legal move
		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				if ((board.getCatchable(board.getTurn(), X, Y)))
					return false;
			}
		}
		return true;
	}

	public boolean isStaleMate(Board_2 board) {
		if (isChecked(board)) {
			return false;
		}
		for (int X = 0; X < board.boardSize; X++) {
			for (int Y = 0; Y < board.boardSize; Y++) {
				if ((board.getCatchable(board.getTurn(), X, Y)))
					return false;
			}
		}
		return true;
	}
}
