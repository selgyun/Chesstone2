package chess;

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
		// done
		// is king underAttecked?
		if (board.getCatchable(board.getNextTurn(), KingX, KingY)) {
			return true;
		}

		return false;
	}

	public boolean isCheckMate() {
		if (!isChecked()) {
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
		for (int X = -1; X < 2 && (KingX+X < board.boardSize && 0 <= KingX+X); X++) {
			for (int Y = -1; Y < 2 && (KingY+Y < board.boardSize && 0 <= KingY+Y); Y++) {
				if(X != 0 && Y != 0) {
					if(board.getPiece(KingX+X,KingY+Y) == null) {
						if(( !board.getCatchable(board.getNextTurn(), KingX+X, KingY+Y) || !board.getCatchable(board.getPrevTurn(), KingX+X, KingY+Y))) {
							return false;
						}
					}else if ((board.getPiece(KingX+X, KingY+Y).getColor() == board.getPrevTurn() || board.getPiece(KingX+X, KingY+Y).getColor() == board.getNextTurn())) {
						return false;
					}
				}
			}
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


	public boolean isStaleMate() {
		if (isChecked()) {
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

}
